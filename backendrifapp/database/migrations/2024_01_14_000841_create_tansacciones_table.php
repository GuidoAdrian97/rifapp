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
        Schema::create('tansacciones', function (Blueprint $table) {
            $table->id();
            $table->decimal('saldo_consumible', 14, 4)->default(0);
            $table->string('stado_transaccion');
            $table->dateTime('fecha_transaccion');
            $table->foreignId('cuentas_id')->references('id')->on('cuentas');
            $table->foreignId('type_tansacciones_id')->references('id')->on('type_tansacciones');
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
        Schema::dropIfExists('tansacciones');
    }
};
