<?php

namespace App\Models\Referido;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ReferralReward extends Model
{
    use HasFactory;

    protected $fillable = ['referral_id', 'amount'];

    public function referral()
    {
        return $this->belongsTo(Referral::class, 'referral_id');
    }
}
