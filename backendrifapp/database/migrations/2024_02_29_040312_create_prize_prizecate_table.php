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
        Schema::create('prize_prizecate', function (Blueprint $table) {
            $table->id();
            $table->foreignId('prize_id')->references('id')->on('prizes')->onDelete('cascade');
            $table->foreignId('prizecate_id')->references('id')->on('prizecates')->onDelete('cascade');
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
        Schema::dropIfExists('prize_prizecate');
    }
};
