<?php

namespace App\Http\Controllers\Sorteo;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Rifas\Prizecate;
class PrizecateController extends Controller
{
    public function ListarPrizecate()
    {
        $prizecates = Prizecate::all();

        return response()->json(['prizecates' => $prizecates]);
    }
}
