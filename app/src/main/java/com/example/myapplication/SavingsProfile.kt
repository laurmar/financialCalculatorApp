package com.example.myapplication

data class SavingsProfile(val name: String) {
    var other: Double = 0.0
    var utilities: Double = 0.0
    var rent: Double = 0.0
    var save: Double = 0.0
    var monthlyUsable: Double = 0.0
    var incomeAfterTax: Double = 0.0
}