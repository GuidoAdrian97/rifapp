<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Laravel\Socialite\Facades\Socialite;
use Illuminate\Support\Facades\Auth;
use App\Models\OauthAccessToken;
use Illuminate\Support\Carbon;
use App\Models\User;
class LoginController extends Controller
{
    public function redirectToGoogle()
    {
        return Socialite::driver('google')->redirect();
    }

    public function handleGoogleCallback()
    {
        
        $user_google = Socialite::driver('google')->stateless()->user();

        $user = User::updateOrCreate([
            'google_id'=> $user_google->id,
        ], [
            'name'=>$user_google->name,
            'email'=>$user_google->email,
        ]);
    
        
        Auth::login($user);
        $token = $user->createToken('token')->plainTextToken;
        $tokenexpire= OauthAccessToken::where('tokenable_id',$user->id)->get()->last();
        $tokenexpire->expires_at=Carbon :: now ( )->addHour(2);
        $tokenexpire->update();
    
        $cookie = cookie('cookie_token',$token,60*1);
    
    
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
    
        return response(['token'=>$token, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200'])->withoutCookie($cookie);
    //s    $token= $user->createToken('token')->plainTextToken;
    
        return response()->json(['token' => $token]);
     
        // $user->token
    }
}
