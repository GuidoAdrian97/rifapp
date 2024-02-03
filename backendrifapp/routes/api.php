<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Laravel\Socialite\Facades\Socialite;
use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Install;
use App\Http\Controllers\Referido\ReferidosController;
/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
//Route::post('Login',[AuthController::class, 'Login']);

Route::post('login',[LoginController::class, 'Login']);

Route::get('login/google', [LoginController::class,'redirectToGoogle'])->name('login.google');
Route::post('register', [LoginController::class,'Register']);
Route::post('verificar_user_data', [LoginController::class,'Verificar_User_identificacion']);
Route::post('validarcedula', [LoginController::class,'ValidarCedula']);
Route::post('updateregistersocialite', [LoginController::class,'UpdateRegisterSocialite']);

Route::get('isntall', [Install::class,'Install']);

Route::get('ValidateCodeReferral', [ReferidosController::class,'ValidateCodeReferral']);
Route::get('ReferidoPrincipal', [ReferidosController::class,'ReferidoPrincipal']);

Route::post('login/google/callback', [LoginController::class,'handleGoogleCallback']);