package com.banklannister.digitalclock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    val secondRightDisplayManager = DigitDisplayManager()
    val secondLeftDisplayManager = DigitDisplayManager()

    val hourRightDisplayManager = DigitDisplayManager()
    val hourLeftDisplayManager = DigitDisplayManager()


    fun startCounting() {
        viewModelScope.launch {

            var index = 0
            while (true) {
                val hours = index / 60
                val seconds = index %60

                hourRightDisplayManager.onNewDigit(hours % 10)
                hourLeftDisplayManager.onNewDigit(hours / 10)

                secondRightDisplayManager.onNewDigit(seconds % 10)
                secondLeftDisplayManager.onNewDigit(seconds / 10)
                index++
                delay(250)
            }

        }
    }


}