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
use Google_Client;

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

            $user = User::updateOrCreate([
                'google_id'=> $payload['sub'],
            ], [
                'name'=>$payload['name'],
                'email'=>$payload['email'],
            ]);

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

        
    
    
       // $urlExterna = 'https://www.ejemplo.com';
    
        // Datos que deseas enviar mediante POST
      //  $datosPost = [
     //       'parametro1' => $token,
       //     'parametro2' => 'valor2',
       // ];
    
        // Guardar los datos en la sesión
      //  session(['datosPost' => $datosPost]);
    
        // Redireccionar a la página externa
      //  return Redirect::away($urlExterna);
    
        
    //s    $token= $user->createToken('token')->plainTextToken;
    
        return response()->json(['token' => $token]);
     
        // $user->token
    }

    public function Register(Request $request){

        
        $user= new User();
        
        User::create([
            'name' =>$request->name ,
            'email' =>$request->email,
            'identificacion'=> $request->identificacion,
            'telefono' => $request->telefono,
            'fecha_nacimiento'=> $request->fecha_nacimiento,
            'password' =>Hash::make($request->password)
        ]); 


        return response()->json(['menssage'=>'registro correcto']);
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
        if (($validar= User::where('identificacion', $request->identificacion)->orWhere('telefono', $request->telefono)->orWhere('email', $request->email)->get())->count() !== 0) {
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
        $response = Http::get("https://srienlinea.sri.gob.ec/movil-servicios/api/v1.0/deudas/porIdentificacion/{$request->cedula}");

        if ($response->successful()) {

            if ($response['contribuyente']['tipoIdentificacion'] === 'C') {
                $nombreComercial = $response['contribuyente']['nombreComercial'] ?? null;
                return response()->json(['nombre' => $nombreComercial]);
            }else{
                return response()->json(['message' => 'Cédula incorrecta']);
            }
        } else {
            return response()->json(['message' => 'Cédula incorrecta']);
        }
    }


    public function UpdateRegisterSocialite(Request $request){
        
        $client = new Google_Client(['client_id' => env('GOOGLE_TOKEN')]); 

       
        

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

        

        return response()->json(['menssage'=>'registro correcto','code'=>'200']);


    }
    

    




}
