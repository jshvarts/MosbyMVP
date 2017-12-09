package com.jshvarts.mosbymvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import timber.log.Timber

class OtherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)
        ButterKnife.bind(this)

        Timber.d("Created other activity")
    }
}