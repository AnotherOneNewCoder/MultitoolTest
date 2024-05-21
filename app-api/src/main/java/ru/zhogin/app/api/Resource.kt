package ru.zhogin.app.api

sealed class Resource<out R : Any>(open val data: R? = null) {
    class Success<R : Any>(override val data: R): Resource<R>(data)
    class Failure<R: Any>(data: R? = null, val error: Throwable? = null): Resource<R>(data)
    class Loading<R : Any>(data: R? = null): Resource<R>(data)
}

fun <I : Any, O : Any> Resource<I>.map(mapper: (I) -> O) : Resource<O> {
    return when(this) {
        is Resource.Failure -> Resource.Failure(data?.let(mapper))
        is Resource.Loading -> Resource.Loading(data?.let(mapper))
        is Resource.Success -> Resource.Success(mapper(data))
    }
}