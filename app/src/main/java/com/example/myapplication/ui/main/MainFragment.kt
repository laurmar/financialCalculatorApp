package com.example.myapplic

import com.example.myapplication.ui.main.MainViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private  var viewState: MainViewModel.ViewState? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.viewState.observe(this, Observer {
            it?.let { render(it) } })

        binding.submitButton.setOnClickListener {
            viewModel.updateValues(binding.plainTextInput.text)
            createTitle()
        }

    }

    fun createTitle(){
        binding.firstPanelText.text =
                "Advised Breakdown: `\n` With a post-tax income of ${viewModel.incomeAfterTax} and a monthly usable income of ${viewModel.monthlyUsable} `\n`You should save 40%: ${viewModel.save}  rent 35%: ${viewModel.rent} utilities 10% ${viewModel.utilities} other 15%: ${viewModel.other}"
    }






    fun fakeSetViewState(viewState: MainViewModel.ViewState){
        viewState.url= "Hello World"
    }


    private fun render(viewState: MainViewModel.ViewState) {
        fakeSetViewState(viewState)

        when (viewState.browserShowing) {
            true -> Log.i("laura", "webView.show()")
            false ->  binding.firstPanelText.text= viewState.url
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