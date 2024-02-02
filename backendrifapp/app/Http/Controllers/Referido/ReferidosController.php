<?php

namespace App\Http\Controllers\Referido;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Referido\ReferralCode;
class ReferidosController extends Controller
{
    public function ValidateCodeReferral(Request $request){

        $referralCode = ReferralCode::where('code', $request->referrerCode)->first();


    if ($referralCode !== null) {
        // El código de referido es válido, ahora puedes realizar acciones adicionales

        return response()->json([
            'message' => 'Código de referido válido',
        ], 200);
    } else {
        // El código de referido no es válido
        return response()->json([
            'error' => 'Código de referido no válido',
        ], 404);
    }
    }
}
