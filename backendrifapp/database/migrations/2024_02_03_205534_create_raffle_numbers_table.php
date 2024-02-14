<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('raffle_numbers', function (Blueprint $table) {
            $table->id();
            $table->integer('number');
            $table->boolean('es_ganador')->default(false);
            $table->timestamps();
            $table->foreignId('raffle_id')->references('id')->on('raffles')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('raffle_numbers');
    }
};
