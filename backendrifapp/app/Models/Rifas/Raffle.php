<?php

namespace App\Models\Rifas;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Raffle extends Model
{
    use HasFactory;

    protected $fillable = ['title','description','cantidad_boletos','rango_inicial_boletos','rango_final_boletos','costo_bololeto','fecha_sorteo_rifa','metodo_sorteo_id'];

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function metodoSorteo()
    {
        return $this->belongsTo(MetodoSorteo::class);
    }

    public function numerosRifa()
    {
        return $this->hasMany(RaffleNumber::class);
    }

    public function premios()
    {
        return $this->hasMany(Prize::class);
    }

    public function informacionAdicional()
    {
        return $this->hasOne(InformacionRifaAdicional::class);
    }
}
