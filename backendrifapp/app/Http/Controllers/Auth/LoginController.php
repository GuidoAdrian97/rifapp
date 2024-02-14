<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Laravel\Socialite\Facades\Socialite;
use Illuminate\Support\Facades\Auth;
use App\Models\OauthAccessToken;
use Illuminate\Support\Carbon;
use App\Models\User;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Hash;
use App\Models\Billetera\Wallet;
use App\Models\Billetera\Tiposcuenta;
use App\Models\Billetera\Cuenta;
use App\Models\Referido\ReferralCode;
use App\Models\Referido\level_user_referrals;
use NumberFormatter;
use Google_Client;


use Illuminate\Http\Exceptions\HttpResponseException;
use Illuminate\Validation\ValidationException;
use Illuminate\Support\Facades\Validator;

class LoginController extends Controller
{
    public function redirectToGoogle()
    {
        return Socialite::driver('google')->redirect();
    }

    public function handleGoogleCallback(Request $request)
    {

        $client = new Google_Client(['client_id' => env('GOOGLE_TOKEN')]); 


            $payload = $client->verifyIdToken($request->accessToken);
        

        if ($payload) {

        
            $user = User::firstOrNew(['google_id' => $payload['sub']]);
            $user->email = $payload['email'];

            if (!$user->exists) {
            $user = User::firstOrNew(['email' => $payload['email']]);
            $user->google_id = $payload['sub'];
            }

            $user->save();


            $value = true;
            Auth::login($user);
            $token = $user->createToken('token')->plainTextToken;
            $tokenexpire= OauthAccessToken::where('tokenable_id',$user->id)->get()->last();
            $tokenexpire->expires_at=Carbon :: now ( )->addHour(2);
            $tokenexpire->update();
        
            $cookie = cookie('cookie_token',$token,60*1);
            if($user->identificacion==null||$user->telefono==null||$user->fecha_nacimiento==null||$user->password==null){
                $value=false;
                return response(['token'=>null, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200','User'=>$value])->withoutCookie($cookie);
            };
            
            return response(['token'=>$token, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200','User'=>$value])->withoutCookie($cookie);
        }else{
                return response(['menssage'=>'datos incorrectos','code'=>'400']);
        }

        return $payload;

    
        return response()->json(['token' => $token]);

    }

    public function Register(Request $request){

        $validator = Validator::make($request->all(), [
            'name' => 'required|string|max:255',
            'email' => 'required|email|unique:users|max:255',
            'identificacion' => 'required|string|unique:users|max:13',
            'telefono' => 'required|string|unique:users|max:10',
            'fecha_nacimiento' => 'required|date',
            'password' => 'required|string|min:8|max:255',
        ]);

        if ($validator->fails()) {
            return response()->json(['errors' => $validator->errors(), 'code'=>'422']);
        }
        
        $user=  User::create([
            'name' =>$request->name ,
            'email' =>$request->email,
            'identificacion'=> $request->identificacion,
            'telefono' => $request->telefono,
            'fecha_nacimiento'=> $request->fecha_nacimiento,
            'password' =>Hash::make($request->password)
        ]);
        
        $wallet= $user->wallet()->create([
            'wallets_name' =>str_replace(' ', '-', $request->name).'_'.$request->identificacion,
            'ganancias' =>0,
            'transaccitions'=>0,
        ]);


        for ($i = 0; $i < 3; $i++) {

           $wallet->cuentas()->create([
            'cuenta' => $request->identificacion . "00" . ($i + 1),
            'tiposcuenta_id' => 1+$i,
        ]);
        }

        $referralCode = ReferralCode::where('code', $request->referrerCode)->first();

        if ($referralCode==null) {
            $referralCode = ReferralCode::where('code', 'MasterCode_1311883845')->first();
        }

        $nivel_user=$referralCode->user->referredBy->level_referral;

        if($nivel_user->level <= level_user_referrals::latest()->first()->level ){

            $level;
            $nivel = level_user_referrals::where('level', $nivel_user->level+1)->first();
            if($nivel==null){

                $formatter = new NumberFormatter('es', NumberFormatter::SPELLOUT);
                $sluglevel = $formatter->format($nivel_user->level+1);
                $level= level_user_referrals::create([
                    'level'=>$nivel_user->level+1,
                    'slug'=>$sluglevel,
                 ]);
                 
            }else{

                $level=$nivel;
            }

            $referral = $user->referrals()->create([
                'referred_user_id' => $referralCode->user_id,
                'level_user_referral_id' => $level->id, // Nivel inicial.
            ]);

        }

        $user->referralcode()->create([
            "code"=>str_replace(' ', '', $request->name).'_'.$request->identificacion
        ]);
        return response()->json(['menssage'=>'registro correcto','code'=>'200']);
    }

    public function Login(Request $request){
        
        $credentials= $request->validate([
            'email'=>['required','email'],
            'password'=>['required']    
        ]);

        
        if(Auth::attempt($credentials)){
            $user=Auth::user();
            $userAuth= Auth::user()->where('email',$request->email)->get();
            $token= $user->createToken('token')->plainTextToken;

            $tokenexpire= OauthAccessToken::where('tokenable_id',$userAuth[0]->id)->get()->last();
            $tokenexpire->expires_at=Carbon :: now ( )->addHour(2);
            $tokenexpire->update();

            $cookie = cookie('cookie_token',$token,60*1);
            $value=true;
            
            if($user->identificacion==null||$user->telefono==null||$user->fecha_nacimiento==null||$user->password==null){
                $value=false;
                return response(['token'=>null, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200','User'=>$value])->withoutCookie($cookie);
            };
            

            return response(['token'=>$token, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200','User'=>$value])->withoutCookie($cookie);
        }else{
            return response()->json(['error' => 'Unauthorized','code'=>'401']);
        }
        return response()->json(['menssage'=>'Login correcto','code'=>'200']);
    }

    public function Verificar_User_identificacion(Request $request){

       
        if (($validar= User::where('identificacion', $request->identificacion)->orWhere('telefono', $request->telefono)->orWhere('email', $request->email)->get())->count() != 0) {
            $duplicateUser = $validar->first();

            // Verificar cuál campo está duplicado
            foreach(['identificacion', 'telefono', 'email'] as $field){
                if ($duplicateUser->$field === $request->$field) {
                    $repeatedField = $field;
                }
            };
            return response(['menssage'=>'Error '.$repeatedField.' que esta intentando ingresar ya se enctra registrada']);
        }else{
            return response(['code'=>'200']);
        }
    }

    public function ValidarCedula(Request $request){

        $validator = Validator::make($request->all(), [
            'cedula' => 'required|numeric|max:255'
        ]);

        if ($validator->fails()) {
            throw new HttpResponseException(response()->json(['errors' => $validator->errors(),'code'=> '422']));
        }

        // Resto de la lógica aquí...

        // Si la validación pasa, continúa con el resto de la lógica del controlador...


        $response = Http::get("https://srienlinea.sri.gob.ec/movil-servicios/api/v1.0/deudas/porIdentificacion/{$request->cedula}");

        if ($response->successful()) {

            if ($response['contribuyente']['tipoIdentificacion'] === 'C') {
                $nombreComercial = $response['contribuyente']['nombreComercial'] ?? null;
                return response()->json(['nombre' => $nombreComercial]);
            }else{
                return response()->json(['message' => 'Cédula incorrecta','code'=> '422']);
            }
        } else {
            return response()->json(['message' => 'Cédula incorrecta','code'=> '200']);
        }
    }


    public function UpdateRegisterSocialite(Request $request){

        
        $client = new Google_Client(['client_id' => env('GOOGLE_TOKEN')]); 
        $payload = $client->verifyIdToken($request->accessToken);
        $user = User::updateOrCreate([
            'google_id'=> $payload['sub'],
        ], [
            'email'=>$payload['email'],
            'name' =>$request->name,
            'identificacion'=> $request->identificacion,
            'telefono' => $request->telefono,
            'fecha_nacimiento'=> $request->fecha_nacimiento,
            'password' =>Hash::make($request->password)
        ]);

        $wallet= $user->wallet()->create([
            'wallets_name' =>str_replace(' ', '-', $request->name).'_'.$request->identificacion,
            'ganancias' =>0,
            'transaccitions'=>0,
        ]);

        for ($i = 0; $i < 3; $i++) {

            $wallet->cuentas()->create([
             'cuenta' => $request->identificacion . "00" . ($i + 1),
             'tiposcuenta_id' => 1+$i,
         ]);
         }
 
         $referralCode = ReferralCode::where('code', $request->referrerCode)->first();
 
         if ($referralCode==null) {
             $referralCode = ReferralCode::where('code', 'MasterCode_1311883845')->first();
         }
 
         $nivel_user=$referralCode->user->referredBy->level_referral;
 
         if($nivel_user->level <= level_user_referrals::latest()->first()->level ){
 
             $level;
             $nivel = level_user_referrals::where('level', $nivel_user->level+1)->first();
             if($nivel==null){
 
                 $formatter = new NumberFormatter('es', NumberFormatter::SPELLOUT);
                 $sluglevel = $formatter->format($nivel_user->level+1);
                 $level= level_user_referrals::create([
                     'level'=>$nivel_user->level+1,
                     'slug'=>$sluglevel,
                  ]);
                  
             }else{
 
                 $level=$nivel;
             }
 
             $referral = $user->referrals()->create([
                 'referred_user_id' => $referralCode->user_id,
                 'level_user_referral_id' => $level->id, // Nivel inicial.
             ]);
 
         }
 
         $user->referralcode()->create([
             "code"=>str_replace(' ', '', $request->name).'_'.$request->identificacion
         ]);
         return response()->json(['menssage'=>'registro correcto','code'=>'200']);

    }



}
