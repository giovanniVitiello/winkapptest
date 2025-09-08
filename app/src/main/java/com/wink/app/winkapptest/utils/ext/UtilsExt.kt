package com.wink.app.winkapptest.utils.ext

import com.wink.app.winkapptest.utils.data.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

fun CoroutineScope.launchSafe(block: suspend () -> Unit): Job =
    launch(context = CoroutineExceptionHandler { _, _ -> }) {

        block()

    }

suspend fun <T> catchIgnoreError(block: suspend () -> T) =
    try {
        block()
    } catch (error: Throwable) {
        null
    }

fun <T> executeUseCase(block: suspend () -> T): Flow<Resource<T>> = flow {
    try {
        val result = block()
        emit(Resource.Success(result))
    } catch (e: Throwable) {
        emit(Resource.Error(e.message.orEmpty(), e))
    }
}.onStart {
    emit(Resource.Loading)
}

fun <T> Flow<T>.asResource(): Flow<Resource<T>> {
    return this
        .map<T, Resource<T>> { data -> Resource.Success(data) } // Mappa il successo
        .onStart { emit(Resource.Loading) } // Emetti Loading all'inizio
        .catch { throwable -> // Cattura le eccezioni dal flusso upstream
            emit(Resource.Error(throwable.message ?: "Errore sconosciuto", throwable))
        }
}