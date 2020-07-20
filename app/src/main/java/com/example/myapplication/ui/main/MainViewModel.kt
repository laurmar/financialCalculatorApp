package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val empty = MutableLiveData<Boolean>().apply { value = false }

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

    val toastMessage = MutableLiveData<String>()


    data class ViewState(
        var isLoading: Boolean = false,
        val progress: Int = 0,
        var url: String? = "message",
        val isEditing: Boolean = false,
        val browserShowing: Boolean = false,
        val showClearButton: Boolean = false
    )

    val viewState: MutableLiveData<ViewState> = MutableLiveData()
    init {
        viewState.value = ViewState()
    }

    private fun currentViewState(): ViewState = viewState.value!!


}


