<?php

namespace App\Models\Referido;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Referral extends Model
{
    use HasFactory;

    protected $fillable = ['user_id', 'referred_user_id','level_user_referral_id'];

    public function referredUser()
    {
        return $this->belongsTo(User::class, 'referred_user_id');
    }

    public function rewards()
    {
        return $this->hasMany(ReferralReward::class, 'referral_id');
    }

    public function level_referral()
    {
        return $this->belongsTo(level_user_referrals::class, 'level_user_referral_id');
    }

    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function calculateRewards($amount)
    {
        // Lógica para calcular las recompensas basadas en referidos directos.
        // Puedes personalizar esto según tus reglas de negocio.
        // (Se mantiene la lógica ajustada según nuestras discusiones anteriores.)
    }
}
