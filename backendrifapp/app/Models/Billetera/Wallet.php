<?php

namespace App\Models\Billetera;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Wallet extends Model
{
    use HasFactory;

    protected $fillable = ['wallets_name', 'ganancias', 'transaccitions', 'saldo_retirado'];
    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function cuentas()
    {
        return $this->hasMany(Cuenta::class);
    }
}
