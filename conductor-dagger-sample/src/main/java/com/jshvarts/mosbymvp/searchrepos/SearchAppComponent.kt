package com.jshvarts.mosbymvp.searchrepos

import com.jshvarts.mosbymvp.di.AppComponent
import com.jshvarts.mosbymvp.di.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(SearchModule::class),
        dependencies = arrayOf(AppComponent::class))
interface SearchAppComponent {
    fun inject(view: SearchViewController)
}