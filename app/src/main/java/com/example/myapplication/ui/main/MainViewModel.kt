package com.example.myapplication.ui.main

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.SavingsProfile

class MainViewModel : ViewModel() {


    //Laura: next I want to convert these into a data class


    val empty = MutableLiveData<Boolean>().apply { value = false }

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

    val toastMessage = MutableLiveData<String>()


    private fun getIncomeAfterTax(income: Int, tax: Double): Double {
        val taxedAmount: Double = income * (tax / 100.0f)
        return  income - taxedAmount
    }

    private fun getMonthlyUsableIncome(afterTax: Double): Double {
        val weekly = afterTax / 52
        return weekly * 4
    }

    private fun calculateAdvisedRent(totalPerMonth: Double): Double {
        return totalPerMonth * (35 / 100.0f)
    }

    private fun calculateAdvisedUtilities(totalPerMonth: Double): Double {
        return totalPerMonth * (10 / 100.0f)
    }

    private fun calculateAdvisedOther(totalPerMonth: Double): Double {
        return totalPerMonth * (15 / 100.0f)
    }

    private fun calculateAdvisedSave(totalPerMonth: Double): Double {
        return totalPerMonth * (40 / 100.0f)
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

    fun createSavingsProfile(person: SavingsProfile, text: Editable?) {
        val x: Int = text.toString().toInt()
         person.incomeAfterTax = getIncomeAfterTax(x, 25.0)
        person.monthlyUsable = getMonthlyUsableIncome(person.incomeAfterTax)
        person.save = calculateAdvisedSave(person.monthlyUsable)
        person.rent = calculateAdvisedRent(person.monthlyUsable)
        person.utilities = calculateAdvisedUtilities(person.monthlyUsable)
        person.other = calculateAdvisedOther(person.monthlyUsable)

    }


}


