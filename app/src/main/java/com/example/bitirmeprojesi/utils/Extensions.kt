package com.example.bitirmeprojesi.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController


fun Navigation.gecisYap(it: View, actionId: Int) {
    Navigation.findNavController(it).navigate(actionId)
}

fun Navigation.gecisYap(it: View, directions: NavDirections) {
    Navigation.findNavController(it).navigate(directions)
}
