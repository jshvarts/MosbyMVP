package com.jshvarts.mosbymvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

class MainActivity : AppCompatActivity() {

    @BindView(R.id.controller_container)
    lateinit var container: ViewGroup

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        router = Conductor.attachRouter(this, container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(GreetingViewController()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}