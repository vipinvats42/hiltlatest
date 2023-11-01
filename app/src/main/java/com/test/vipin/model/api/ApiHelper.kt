package com.test.vipin.model.api

import com.test.vipin.model.Photos
import com.test.vipin.model.User
import com.test.vipin.utils.Resource
import retrofit2.Response

interface ApiHelper {
    suspend fun getUsers() : Response<List<User>>

    suspend fun getPhotos() : Response<Photos>?
}