<?php

namespace App\Models\Billetera;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Cuenta extends Model
{
    use HasFactory;
    protected $fillable = ['cuenta', 'saldo','tiposcuenta_id'];

    public function wallet()
    {
        return $this->belongsTo(Billetera\Wallet::class);
    }

    public function typeCuenta()
    {
        return $this->belongsTo(Billetera\Tiposcuenta::class);
    }

    public function transacciones()
    {
        return $this->hasMany(Billetera\Transaccion::class);
    }
}
