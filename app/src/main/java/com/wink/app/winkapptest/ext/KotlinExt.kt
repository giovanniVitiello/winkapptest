package com.wink.app.winkapptest.ext

suspend fun <T> catchIgnoreError(block: suspend () -> T) =
    try {
        block()
    } catch (error: Throwable) {
        null
    }
