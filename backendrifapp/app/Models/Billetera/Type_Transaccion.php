<?php

namespace App\Models\Billetera;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Type_Transaccion extends Model
{
    use HasFactory;

    protected $fillable = ['type_tansaccion', 'descripcion_type_tansaccion'];
    public function transacciones()
    {
        return $this->hasMany(Transaccion::class);
    }
}
