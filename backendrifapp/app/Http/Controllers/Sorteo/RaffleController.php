<?php

namespace App\Http\Controllers\Sorteo;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Models\Rifas\Raffle;
use App\Models\Rifas\Prize;
use App\Models\User;
use Illuminate\Support\Facades\Auth;

use Illuminate\Http\Exceptions\HttpResponseException;
use Illuminate\Validation\ValidationException;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\File;
use Illuminate\Support\Facades\Storage;
class RaffleController extends Controller
{
    public function CreateRaffle(Request $request){

    
        $validator = Validator::make($request->all(), [
            'title' => 'required|string|max:255',
            'description' => 'required|string',
            'cantidad_boletos' => 'required|integer|min:1',
            'rango_inicial_boletos' => 'required|integer|min:1',
            'rango_final_boletos' => 'required|integer|min:' . $request->input('rango_inicial_boletos'),
            'costo_boleto' => 'required|numeric|min:0',
            'fecha_sorteo_rifa' => 'required|date|after:now',
            'metodo_sorteo_id' => [
                'required',
                'exists:metodo_sorteos,id', // Esta regla verifica si el ID existe en la tabla metodo_sorteos
            ],
        ]);

        if ($validator->fails()) {
            return response()->json(['errors' => $validator->errors(), 'code'=>'422']);
        }

        $user = Auth::user();
        $cedula = $user->identificacion;

        $rutaUsuario = public_path('images/' . $cedula);

        if (!File::exists($rutaUsuario)) {
            File::makeDirectory($rutaUsuario, 0777, true, true);
        }

        
        
        
        $rifa = new Raffle();
        $rifa->title = $request->title;
        $rifa->description = $request->description;
        $rifa->cantidad_boletos = $request->cantidad_boletos;
        $rifa->rango_inicial_boletos = $request->rango_inicial_boletos;
        $rifa->rango_final_boletos = $request->rango_final_boletos;
        $rifa->costo_boleto = $request->costo_boleto;
        $rifa->fecha_sorteo_rifa = $request->fecha_sorteo_rifa;
        $rifa->metodo_sorteo_id = $request->metodo_sorteo_id;

        $user->raffle()->save($rifa);
        $aux = $request->all();

        
        foreach ($request->premios as $premioData) {
            $premio = new Prize();
            $premio->name_prize = $premioData['name_prize'];
            $premio->descripcion_prize = $premioData['descripcion_prize'];
            $premio->calidad = $premioData['calidad'];
            $premio->categorias = $premioData['categorias'];
            $premio->posicion_prize = $premioData['posicion_prize'];
    
            $rifa->premios()->save($premio);
    
            $urlimagenes = [];
    
            foreach ($premioData['imagenes'] as $imagen) {
                // Decodificar la imagen base64 y guardarla como un archivo
                $imagenDecodificada = base64_decode(preg_replace('#^data:image/\w+;base64,#i', '', $imagen));
                $nombre = time() . '_' . $user->identificacion;
                $rutaImagen = $rutaUsuario . '/' . $nombre.'.jpg';
                $rutaImagenHEIF = $rutaUsuario . '/' . $nombre . '.heic';
            
                // // Guardar la imagen decodificada en el sistema de archivos
                file_put_contents($rutaImagen, $imagenDecodificada);
            
                // Convertir la imagen a formato HEIF si es necesario
                exec("convert $rutaImagen $rutaImagenHEIF");
            
                // if (File::exists($rutaImagenHEIF)) {
                //     $urlimagenes[] = '/images/' . $cedula . '/' . $nombre . '.heic';
                //     File::delete($rutaImagen);
                // }
            }
    
            // $premio->imagenes()->createMany($urlimagenes);
        }

        return response()->json(['mensaje' => 'Rifa creada exitosamente', 'rifa' => $rifa], 201);
    }
    
}
