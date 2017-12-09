package com.jshvarts.mosbymvp

import io.reactivex.Single

object GetGreetingUseCase {

    fun getHelloGreeting(): Single<String> {
        return Single.just("hi there!")
    }

    fun getGoodbyeGreeting(): Single<String> {
        return Single.just("bye there!")
    }
}