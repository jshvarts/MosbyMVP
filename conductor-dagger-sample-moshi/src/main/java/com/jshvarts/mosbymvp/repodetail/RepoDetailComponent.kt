package com.jshvarts.mosbymvp.repodetail

import com.jshvarts.mosbymvp.di.AppComponent
import com.jshvarts.mosbymvp.di.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(RepoDetailModule::class),
        dependencies = arrayOf(AppComponent::class))
interface RepoDetailComponent {
    fun presenter(): RepoDetailPresenter
}