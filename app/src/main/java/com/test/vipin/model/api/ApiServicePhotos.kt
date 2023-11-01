package com.test.vipin.model.api

import com.test.vipin.model.Photos
import com.test.vipin.utils.Resource
import retrofit2.Response
import retrofit2.http.GET

interface ApiServicePhotos {
    @GET("photos/2")
    suspend fun gePhotos() : Response<Photos>?
}