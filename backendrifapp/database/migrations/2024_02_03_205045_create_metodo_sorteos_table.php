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
        Schema::create('metodo_sorteos', function (Blueprint $table) {
            $table->id();
            $table->string('nombre_sorteo');
            $table->string('descripcion_sorteo');
            $table->boolean('estado_tipo_sorteo')->default(false);
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
        Schema::dropIfExists('metodo_sorteos');
    }
};
