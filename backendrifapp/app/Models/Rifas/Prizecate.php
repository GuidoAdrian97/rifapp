<?php

namespace App\Models\Rifas;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Prizecate extends Model
{
    use HasFactory;
    protected $fillable = ['name_prizecates','description_prizecates','slug_prizecates'];

    public function prizes()
    {
        return $this->belongsToMany(Prize::class);
    }

    
}
