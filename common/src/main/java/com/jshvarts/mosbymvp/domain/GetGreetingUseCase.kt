package com.jshvarts.mosbymvp.domain

import com.jshvarts.mosbymvp.data.GreetingRepository
import io.reactivex.Single

/**
 * In a Production app, inject the Use Case in your Presentation layer.
 */
object GetGreetingUseCase {
    fun getHelloGreeting(): Single<String> = GreetingRepository.getHelloGreeting()
}