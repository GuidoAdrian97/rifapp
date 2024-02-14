<?php

namespace App\Models\Rifas;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class RaffleNumber extends Model
{
    use HasFactory;

    protected $table = ['number','es_ganador'];

    public function rifa()
    {
        return $this->belongsTo(Raffle::class);
    }

    public function compras()
    {
        return $this->hasMany(Purchase::class);
    }
}
