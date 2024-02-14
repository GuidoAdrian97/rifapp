<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Laravel\Socialite\Facades\Socialite;
use App\Http\Controllers\Auth\LoginController;
use App\Http\Controllers\Install;
use App\Http\Controllers\Referido\ReferidosController;
use App\Http\Controllers\Sorteo\RaffleController;
use App\Http\Controllers\Sorteo\MetodoSorteoController;
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
Route::group(['middleware'=>['auth:sanctum']],function(){

    Route::post('createraffle', [RaffleController::class,'CreateRaffle']);

    Route::get('listarmetodosorteo', [MetodoSorteoController::class,'listarmetodosorteo']);

});

//Route::post('createraffle', [RaffleController::class,'CreateRaffle'])->middleware('api');

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

