package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplic.MainFragment
import com.example.myapplication.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.viewState.observe(this, Observer<MainViewModel.ViewState> {
            it?.let { render(it) }
        })

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }


    private fun render(viewState: MainViewModel.ViewState) {
        when (viewState.browserShowing) {
            true -> Log.i("laura", "webView.show()")
            false ->  Log.i("laura", "webView.hide()")
        }

        when (viewState.isLoading) {
            true ->  Log.i("laura", "pageLoadingIndicator.show()")
            false ->  Log.i("laura", "pageLoadingIndicator.hide()")
        }

        when (viewState.showClearButton) {
            true ->  Log.i("laura", "showClearButton()")
            false ->  Log.i("laura", "hideClearButton()")
        }
    }
}