<?php

namespace App\Http\Controllers\Sorteo;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Rifas\Raffle;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
class RaffleController extends Controller
{
    public function CreateRaffle(Request $request){

        $validatedData = $request->validate([
            'title' => 'required|string|max:255',
            'description' => 'required|string',
            'cantidad_boletos' => 'required|integer|min:1',
            'rango_inicial_boletos' => 'required|integer|min:1',
            'rango_final_boletos' => 'required|integer|min:',
            'costo_boleto' => 'required|numeric|min:0',
            'fecha_sorteo_rifa' => 'required|date',
            'metodo_sorteos' => 'required|string|max:255',
        ]);

        $user=Auth::user();
        
    }
    
}
