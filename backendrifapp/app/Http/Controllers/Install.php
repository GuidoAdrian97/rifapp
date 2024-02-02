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
use App\Models\Billetera\Wallet;
use App\Models\Billetera\Tiposcuenta;
use App\Models\Billetera\Cuenta;
use App\Models\Referido\ReferralCode;
use App\Models\Referido\level_user_referrals;
use Google_Client;

class Install extends Controller
{
    public function Install(Request $request){
        if (Tiposcuenta::count() === 0) {
            // Crear los Type_Cuenta por defecto si no existen
            $tiposCuentaPorDefecto = [
                ['type_cuenta' => 'recargas', 'descripcion_type_cuenta' => 'Cuenta de recargas'],
                ['type_cuenta' => 'retiros', 'descripcion_type_cuenta' => 'Cuenta de retiros'],
                ['type_cuenta' => 'ganancias', 'descripcion_type_cuenta' => 'Cuenta de ganancias'],
                ['type_cuenta' => 'master_fondos', 'descripcion_type_cuenta' => 'Cuenta de fondos donde llegara todo el dinero para recargar mas cuentas '],
                ['type_cuenta' => 'master_ganancias', 'descripcion_type_cuenta' => 'Cuenta de ganancias master aqui se encontrara la ganancia del %5 de cada recarga'],
            ];
    
            Tiposcuenta::insert($tiposCuentaPorDefecto);
        }
        
        $user=  User::create([
            'name' =>'Master Cuentas Admin' ,
            'email' =>'guidoadrian97@gmail.com',
            'identificacion'=> '1311883845',
            'telefono' => '0980013638',
            'fecha_nacimiento'=> '1997-04-22',
            'password' =>Hash::make(env('PASS_CUENTA_MASTER'))
        ]);

        $wallet= $user->wallet()->create([
            'wallets_name' =>'Master_Cuentas_Admin',
            'ganancias' =>0,
            'transaccitions'=>0,
        ]);

        for ($i = 0; $i < 2; $i++) {

            $wallet->cuentas()->create([
             'cuenta' => $request->identificacion . "00" . ($i + 1),
             'tiposcuenta_id' => 4+$i,
         ]);
         }
        
        $level= level_user_referrals::create([
            'level'=>'0',
            'slug'=>'cero',
         ]);



        $user->referrals()->create([
            'referred_user_id' => $user->id,
            'level_user_referral_id' => $level->id, // Nivel inicial.
        ]);

         $user->referralcode()->create([
            "code"=>'MasterCode_1311883845'
        ]);

     }

}
