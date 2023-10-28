package com.test.vipin.model.api

import com.test.vipin.model.Photos
import com.test.vipin.model.RetrofitTwo
import retrofit2.Response
import retrofit2.http.GET

interface ApiServicePhotos {
    @GET("photos")
    suspend fun gePhotos() : Response<List<Photos>>
}