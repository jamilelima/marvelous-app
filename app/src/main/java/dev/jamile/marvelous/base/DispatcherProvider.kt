package dev.jamile.marvelous.base

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class DispatcherProvider {
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val ui: CoroutineContext by lazy { Dispatchers.Main }
}