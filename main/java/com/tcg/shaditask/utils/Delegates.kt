package com.tcg.garageapplication.util

import kotlinx.coroutines.*
import org.kodein.di.LazyDelegate

fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}