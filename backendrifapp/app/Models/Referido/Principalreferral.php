<?php

namespace App\Models\Referido;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Principalreferral extends Model
{
    use HasFactory;

    protected $fillable = ['estado','referral_code_id'];


    public function referralcode()
    {
        return $this->belongsTo(ReferralCode::class,'referral_code_id');
    }
}
