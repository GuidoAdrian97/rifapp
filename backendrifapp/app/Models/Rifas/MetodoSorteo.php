<?php

namespace App\Models\Rifas;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class MetodoSorteo extends Model
{
    use HasFactory;

    protected $fillable = ['nombre_sorteo','descripcion_sorteo','estado_tipo_sorteo'];
    
    public function raffle()
    {
        return $this->hasMany(Raffle::class);

    }


}
