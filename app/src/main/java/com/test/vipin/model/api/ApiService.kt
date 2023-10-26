package com.test.vipin.model.api

import com.test.vipin.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : Response<List<User>>
}