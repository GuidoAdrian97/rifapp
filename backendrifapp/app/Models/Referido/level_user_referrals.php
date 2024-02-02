<?php

namespace App\Models\Referido;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class level_user_referrals extends Model
{
    use HasFactory;

    protected $fillable = ['level','slug'];

    public function rewards()
    {
        return $this->hasMany(Referral::class);
    }
}
