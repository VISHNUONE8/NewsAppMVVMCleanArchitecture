package com.andrayudu.newsappcomposemvvmtej.data.util

sealed class ResultState<T>(
    val data: T? = null,
    val message: String? = null
) {

    // We'll wrap our data in this 'Success'
    // class in case of success response from api
    class Success<T>(data: T) : ResultState<T>(data)

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class Error<T>( val throwable: Throwable? = null,message: String,data: T? = null,) : ResultState<T>(data, message)

    // We'll just pass object of this Loading
    // class, just before making an api call
    class Loading<T> : ResultState<T>()
}