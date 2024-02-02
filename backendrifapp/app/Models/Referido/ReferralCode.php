<?php

namespace App\Models\Referido;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ReferralCode extends Model
{
    use HasFactory;

    protected $fillable = ['code'];

    public function user()
    {
        return $this->belongsTo(\App\Models\User::class);
    }
}
