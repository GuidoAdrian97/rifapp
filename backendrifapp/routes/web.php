<?php

use App\Http\Controllers\ProfileController;
use Illuminate\Support\Facades\Route;
use Laravel\Socialite\Facades\Socialite;
use Illuminate\Support\Facades\Auth;
use App\Models\OauthAccessToken;
use Illuminate\Support\Carbon;
use App\Models\User;
/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('/google-auth/redirect', function () {
    return Socialite::driver('google')->redirect();
});
 
Route::get('/google-auth/callback', function () {
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
    return response(['token'=>$token, 'usuario'=>$user,'menssage'=>'Login correcto','code'=>'200'])->withoutCookie($cookie);
//s    $token= $user->createToken('token')->plainTextToken;

    return response()->json(['token' => $token]);
 
    // $user->token
});

Route::get('/dashboard', function () {
    return view('dashboard');
})->middleware(['auth', 'verified'])->name('dashboard');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

require __DIR__.'/auth.php';
