package com.tcg.garageapplication.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Coroutines {

    public fun main(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }


    fun io(work: suspend  (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

}