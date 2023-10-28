package com.test.vipin.model.api

import com.test.vipin.model.Photos
import com.test.vipin.model.User
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService,private val apiServicePhotos: ApiServicePhotos) : ApiHelper {
    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()
    override suspend fun getPhotos(): Response<List<Photos>> = apiServicePhotos.gePhotos()

}
