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
        Schema::create('raffles', function (Blueprint $table) {
            $table->id();
            $table->string('title');
            $table->text('description');
            $table->integer('cantidad_boletos')->unique();
            $table->integer('rango_inicial_boletos')->unique();
            $table->integer('rango_final_boletos')->unique();
            $table->decimal('costo_bololeto', 8, 2);
            $table->date('fecha_sorteo_rifa');
            $table->foreignId('metodo_sorteos')->references('id')->on('metodo_sorteos');
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
        Schema::dropIfExists('raffles');
    }
};
