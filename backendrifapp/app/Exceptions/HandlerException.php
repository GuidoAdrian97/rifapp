<?php

namespace App\Exceptions;

use Exception;

class HandlerException extends Exception
{
    protected $dontReport = [
        //
    ];

    protected $dontFlash = [
        'password',
        'password_confirmation',
    ];

    public function report(\Throwable $exception)
    {
        parent::report($exception);
    }

    public function render($request, \Throwable $exception)
    {
        if ($exception instanceof AuthenticationException) {
            return response()->json(['error' => 'Unauthenticated.'], Response::HTTP_UNAUTHORIZED);
        }

        return parent::render($request, $exception);
    }
}
