package com.test.vipin.model.remote

import retrofit2.http.GET

interface MyApi {
    @GET("test")
    fun doNetworkCall()
}