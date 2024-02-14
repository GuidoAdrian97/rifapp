<?php

namespace App\Http\Controllers\Sorteo;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Rifas\MetodoSorteo;
class MetodoSorteoController extends Controller
{
    public function listarmetodosorteo(){

        $metodosSorteo = MetodoSorteo::where('estado_tipo_sorteo', 1)->get();

        if ($metodosSorteo->isEmpty()) {
            // Si la colección está vacía, retornamos un mensaje de error
            return response()->json(['error'=>'No se encontraron tipos de sorteos disponibles','code'=>'401']);
        }
    
        return response()->json(['Metodos de Sorteo'=>$metodosSorteo,'code'=>'200']);


    }
}
