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
        
        $user = Socialite::driver('google')->user();

        // Implementa la lógica para crear o recuperar el usuario en tu base de datos
        // Luego, genera un token de acceso con Laravel Passport

        $token = $user->createToken('MyAppToken')->accessToken;

        return response()->json(['token' => $token]);
    }
}
