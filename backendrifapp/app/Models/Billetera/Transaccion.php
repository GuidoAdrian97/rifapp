<?php

namespace App\Models\Billetera;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Transaccion extends Model
{
    use HasFactory;

    protected $fillable = ['saldo_consumible', 'stado_transaccion','fecha_transaccion'];
    public function cuenta()
    {
        return $this->belongsTo(Cuenta::class);
    }

    public function typeTransaccion()
    {
        return $this->belongsTo(TypeTransaccion::class);
    }
}
