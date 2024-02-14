<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Laravel\Socialite\Facades\Socialite;
use Illuminate\Support\Facades\Auth;
use App\Models\OauthAccessToken;
use Illuminate\Support\Carbon;
use App\Models\User;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Hash;
use App\Models\Rifas\MetodoSorteo;
use App\Models\Billetera\Wallet;
use App\Models\Billetera\Tiposcuenta;
use App\Models\Billetera\Cuenta;
use App\Models\Referido\ReferralCode;
use App\Models\Referido\level_user_referrals;
use App\Models\Referido\Principalreferral;
use Google_Client;

class Install extends Controller
{
    public function Install(Request $request){
        // Tiposcuenta
        Tiposcuenta::insert([
            ['type_cuenta' => 'recargas', 'descripcion_type_cuenta' => 'Cuenta de recargas'],
            ['type_cuenta' => 'retiros', 'descripcion_type_cuenta' => 'Cuenta de retiros'],
            ['type_cuenta' => 'ganancias', 'descripcion_type_cuenta' => 'Cuenta de ganancias'],
            ['type_cuenta' => 'master_fondos', 'descripcion_type_cuenta' => 'Cuenta de fondos donde llegara todo el dinero para recargar mas cuentas '],
            ['type_cuenta' => 'master_ganancias', 'descripcion_type_cuenta' => 'Cuenta de ganancias master aqui se encontrara la ganancia del %5 de cada recarga'],
        ]);
        MetodoSorteo::insert([
        ['nombre_sorteo' => 'Loteria Nacional','estado_tipo_sorteo' => 1, 'descripcion_sorteo' => 'Este sorteo solo se puede utilizar para sorteos que se realicen los dias lunes miercoles y viernes, y los responsables de los resultados de los numeros es la Loteria Nacional'],
        ['nombre_sorteo' => 'Rifalo Ya','estado_tipo_sorteo' => 1, 'descripcion_sorteo' => 'Este sorteo se realiza mediante un porceso logico, matematico seguro que genera numeros aleatorios donde NO se puede predecir los resultados.'],
        ]);

// Usuario y Wallet
$user = User::create([
    'name' => 'Master Cuentas Admin',
    'email' => 'guidoadrian97@gmail.com',
    'identificacion' => '1311883845',
    'telefono' => '0980013638',
    'fecha_nacimiento' => '1997-04-22',
    'password' => Hash::make(env('PASS_CUENTA_MASTER')),
]);

$wallet = $user->wallet()->create([
    'wallets_name' => 'Master_Cuentas_Admin',
    'ganancias' => 0,
    'transaccitions' => 0,
]);

// Cuentas
$wallet->cuentas()->createMany([
    ['cuenta' => $request->identificacion . "001", 'tiposcuenta_id' => 4],
    ['cuenta' => $request->identificacion . "002", 'tiposcuenta_id' => 5],
]);

// Nivel
$level = level_user_referrals::create([
    'level' => '0',
    'slug' => 'cero',
]);

// Referral y Código de Referido
$user->referrals()->create([
    'referred_user_id' => $user->id,
    'level_user_referral_id' => $level->id,
]);

$user->referralcode()->create([
    "code" => 'MasterCode_1311883845',
])->principalreferral()->create([
    "estado" => true,
]);
    }


}
