package com.example.myapplication.ui.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var other: Double = 0.0
    var utilities: Double = 0.0
    var rent: Double = 0.0
    var save: Double = 0.0
    var monthlyUsable: Double = 0.0
    var incomeAfterTax: Double = 0.0

    val empty = MutableLiveData<Boolean>().apply { value = false }

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

    val toastMessage = MutableLiveData<String>()

    fun updateValues(inputtedNumber: Editable){
        val x: Int = inputtedNumber.toString().toInt()
        getIncomeAfterTax(x, 25.0)
        getMonthlyUsableIncome(this.incomeAfterTax)
        calculateAdvisedSave(monthlyUsable)
        calculateAdvisedRent(monthlyUsable)
        calculateAdvisedUtilities(monthlyUsable)
        calculateAdvisedOther(monthlyUsable)

    }

    private fun getIncomeAfterTax(income: Int, tax: Double) {
        val taxedAmount: Double = income * (tax / 100.0f)
        this.incomeAfterTax=  income - taxedAmount
    }

    private fun getMonthlyUsableIncome(afterTax: Double) {
        val weekly = afterTax / 52
        this.monthlyUsable = weekly * 4
    }

    private fun calculateAdvisedRent(totalPerMonth: Double) {
        this.rent = totalPerMonth * (35 / 100.0f)
    }

    private fun calculateAdvisedUtilities(totalPerMonth: Double) {
        this.utilities = totalPerMonth * (10 / 100.0f)
    }

    private fun calculateAdvisedOther(totalPerMonth: Double) {
        this.other = totalPerMonth * (15 / 100.0f)
    }

    private fun calculateAdvisedSave(totalPerMonth: Double) {
        this.save = totalPerMonth * (40 / 100.0f)
    }


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


