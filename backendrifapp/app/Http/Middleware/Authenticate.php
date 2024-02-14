<?php

namespace App\Http\Middleware;

use Illuminate\Support\Facades\Auth;
use Illuminate\Auth\Middleware\Authenticate as Middleware;
use Closure;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Auth\AuthenticationException;

class Authenticate extends Middleware
{
    /**
     * Get the path the user should be redirected to when they are not authenticated.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return string|null
     */
    protected function redirectTo($request)
    {

    }

    public function handle($request, Closure $next, ...$guards)
    {

   //     dd(auth('mobile')->check());
       

        if($toke = $request->cookie('cookie_token')){
            $request->headers->set('Authorization', 'Bearer '.$toke);
        }

       // return response()->json($guards);
        $this->authenticate($request, $guards);
        return $next($request);
    }

}
