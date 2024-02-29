<?php

namespace App\Models\Rifas;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Prize extends Model
{
    use HasFactory;
    protected $fillable = ['name_prize','descripcion_prize','calidad','categorias','posicion_prize'];

    public function images(){
    	return $this->morphMany(\App\Models\Image::class,'imageable');
    }

    public function raffle()
    {
        return $this->belongsTo(Raffle::class);
    }

    public function categoriaspremio()
    {
        return $this->belongsToMany(Prizecate::class);
    }

    

}
