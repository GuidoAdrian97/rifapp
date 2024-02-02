<?php

namespace App\Models\Billetera;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Tiposcuenta extends Model
{
    use HasFactory;

    protected $fillable = ['type_cuenta', 'descripcion_type_cuenta'];
    public function cuentas()
    {
        return $this->hasMany(Billetera\Cuenta::class);
    }
}
