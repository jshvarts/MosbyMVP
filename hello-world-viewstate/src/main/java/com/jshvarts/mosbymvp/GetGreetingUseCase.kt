package com.jshvarts.mosbymvp

import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * This is a business logic (Domain layer)
 */
object GetGreetingUseCase {

    fun getHelloGreeting(): Single<String> {
        return Single.just("hi there!").delay(2, TimeUnit.SECONDS)
    }
}