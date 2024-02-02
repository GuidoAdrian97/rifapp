<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'email',
        'password',
        'identificacion',
        'telefono',
        'fecha_nacimiento',
        'google_id',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * The attributes that should be cast.
     *
     * @var array<string, string>
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    public function wallet()
    {
        return $this->hasOne(Billetera\Wallet::class);
    }

    public function referralcode()
    {
        return $this->hasOne(Referido\ReferralCode::class);
    }

    public function referrals()
    {
        return $this->hasMany(Referido\Referral::class, 'user_id');
    }

    public function referredBy()
    {
        return $this->hasOne(Referido\Referral::class, 'referred_user_id');
    }

    public function rewards()
    {
        return $this->hasMany(Referido\ReferralReward::class, 'referral_id');
    }
}
