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
            $table->integer('cantidad_boletos');
            $table->integer('rango_inicial_boletos');
            $table->integer('rango_final_boletos');
            $table->decimal('costo_boleto', 8, 2);
            $table->date('fecha_sorteo_rifa');
            $table->enum('estado', ['vendido', 'en_progreso', 'finalizado', 'cancelado', 'pausado', 'en_espera_de_aprobacion', 'caducado'])->default('en_espera_de_aprobacion');
            $table->foreignId('user_id')->references('id')->on('users');
            $table->foreignId('metodo_sorteo_id')->references('id')->on('metodo_sorteos');
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
