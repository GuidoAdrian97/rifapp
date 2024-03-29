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
        Schema::create('prizes', function (Blueprint $table) {
            $table->id();
            $table->string('name_prize');
            $table->string('descripcion_prize');
            $table->decimal('calidad', 8, 1);
            $table->string('categorias');
            $table->integer('posicion_prize');
            $table->foreignId('raffle_id')->references('id')->on('raffles');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('prizes');
    }
};
