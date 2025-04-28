package com.example.socialmediavideodownloaderapps.core

sealed class Resource<T>(
    val data : T? =null,val error : String?=""
) {
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()
    class Error<T>(error : String?) : Resource<T>(data = null, error = error)
    class Success<T>(data : T) : Resource<T>(data = data,error = null)

}