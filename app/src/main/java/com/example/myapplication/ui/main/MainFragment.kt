package com.example.myapplic

import com.example.myapplication.ui.main.MainViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.SavingsProfile
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

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it?.let { render(it) } })

        binding.submitButton.setOnClickListener {
            val person = SavingsProfile("Max")
            viewModel.createSavingsProfile(person, binding.plainTextInput.text)
            createTitle(person)
        }

    }

    fun createTitle(person: SavingsProfile) {
        binding.firstPanelText.text =
                "Advised Breakdown: `\n` With a post-tax income of ${person.incomeAfterTax} and a monthly usable income of ${person.monthlyUsable} `\n`You should save 40%: ${person.save}  rent 35%: ${person.rent} utilities 10% ${person.utilities} other 15%: ${person.other}"
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


    override fun <I : Any?, O : Any?> prepareCall(
        contract: ActivityResultContract<I, O>,
        callback: ActivityResultCallback<O>
    ): ActivityResultLauncher<I> {
        TODO("Not yet implemented")
    }

    override fun <I : Any?, O : Any?> prepareCall(
        contract: ActivityResultContract<I, O>,
        registry: ActivityResultRegistry,
        callback: ActivityResultCallback<O>
    ): ActivityResultLauncher<I> {
        TODO("Not yet implemented")
    }

}