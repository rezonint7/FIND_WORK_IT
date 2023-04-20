package com.example.find_work_it.common

sealed class Resource <T>(val data: T? = null, val pages: Map<String, Int>? = null, val message: String? = null) {
    class Success <T> (data: T): Resource<T>(data)
   // class SuccessPages<T>(pages: Map<String, Int>): Resource<T>(pages = pages)
    class Loading <T> (data: T? = null): Resource<T>(data)
    class Error <T> (data: T? = null, message: String): Resource<T>(data, message = message)
}