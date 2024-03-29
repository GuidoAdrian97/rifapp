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
use Spatie\Image\Manipulations;
use Spatie\Image\Image;
use Imagick;
use Illuminate\Support\Facades\URL;
class RaffleController extends Controller
{
    public function mime_content_type_from_base64($base64) {
        $data = explode(',', $base64, 2);
        if (count($data) === 2) {
            $encoding = $data[0];
            $encodedData = $data[1];
            return preg_replace('#^data:(.*?);.*$#', '$1', $encoding);
        }
        return false;
    }
    public static function mime_to_image_type($mime) {
        $mime_to_type = [
            'image/jpeg' => IMAGETYPE_JPEG,
            'image/png' => IMAGETYPE_PNG,
            'image/gif' => IMAGETYPE_GIF,
            // Agrega más tipos MIME si es necesario
        ];
    
        return $mime_to_type[$mime] ?? false;
    }
    public function CreateRaffle(Request $request){
        $validator = Validator::make($request->all(), [
            'title' => 'required|string|max:255',
            'description' => 'required|string',
            'cantidad_boletos' => 'required|integer|min:1',
            'rango_inicial_boletos' => 'required|integer|min:0',
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
        
        foreach ($request->premios as $premioData) {
            $premio = new Prize();
            $premio->name_prize = $premioData['name_prize'];
            $premio->descripcion_prize = $premioData['descripcion_prize'];
            $premio->calidad = $premioData['calidad'];
            $premio->categorias = $premioData['categorias'];
            $premio->posicion_prize = $premioData['posicion_prize'];
    
            $rifa->premios()->save($premio);
    
            $urlimagenes = [];
    
            foreach ($premioData['imagenes'] as $imagenBase64) {
                $imagenDecodificada = base64_decode(preg_replace('#^data:image/\w+;base64,#i', '', $imagenBase64));

                // Crear un nombre único para la imagen
                $nombre = time() . '_' . $user->identificacion;
            
                // Obtener la extensión del archivo
                $extension = self::mime_content_type_from_base64($imagenBase64);
                $image_type = self::mime_to_image_type($extension);
                $extension = $image_type ? image_type_to_extension($image_type, false) : 'jpeg';
            
                // Ruta para la imagen original
                $rutaImagenOriginal = $rutaUsuario . '/' . $nombre . '_original.' . $extension;
            
                // Guardar la imagen decodificada en el sistema de archivos
                file_put_contents($rutaImagenOriginal, $imagenDecodificada);
                
                // Ruta para la imagen convertida a JPG
                $rutaImagenWebP = $rutaUsuario . '/' . $nombre . '.webp';
            
                // Convertir la imagen a formato JPG
                $imagen = new Imagick($rutaImagenOriginal);
                $imagen->setImageFormat('webp');
                $imagen->writeImage($rutaImagenWebP);
            
                // Eliminar la imagen original (temporal)
                unlink($rutaImagenOriginal);
                $urlBase = URL::to('/').'/images/'. $cedula. '/' . $nombre . '.webp';
                // Agregar la URL de la imagen JPG a la lista de URLs
                $urlimagenes[]['url'] = $urlBase;
            }
            $premio->images()->createMany($urlimagenes);



        }

        return response()->json(['mensaje' => 'Rifa creada exitosamente', 'rifa' => $rifa], 201);
    }
    
    
}
