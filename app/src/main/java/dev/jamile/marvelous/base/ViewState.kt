package dev.jamile.marvelous.base

class ViewState<T, S>(
    val data: T? = null,
    val status: S,
    val error: Throwable? = null
)