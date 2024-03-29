package com.test.vipin.model.api

import com.test.vipin.model.User
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers() : Response<List<User>>
}