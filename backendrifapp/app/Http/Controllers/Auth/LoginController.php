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

use Google_Client;

class LoginController extends Controller
{
    public function redirectToGoogle()
    {
        return Socialite::driver('google')->redirect();
    }

    public function handleGoogleCallback(Request $request)
    {

        $client = new Google_Client(['client_id' => '556193101893-aqt6binlorrpimjtlu0ku9v1c37mrd3p.apps.googleusercontent.com']); 

        $payload = $client->verifyIdToken($request->accessToken);

        if ($payload) {

            $user = User::updateOrCreate([
                'google_id'=> $payload['sub'],
            ], [
                'name'=>$payload['name'],
                'email'=>$payload['email'],
            ]);
        
            
            Auth::login($user);
            $token = $user->createToken('token')->plainTextToken;
            $tokenexpire= OauthAccessToken::where('tokenable_id',$user->id)->get()->last();
            $tokenexpire->expires_at=Carbon :: now ( )->addHour(2);
            $tokenexpire->update();
        
            $cookie = cookie('cookie_token',$token,60*1);

            return response(['token'=>$token, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200'])->withoutCookie($cookie);
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
}
