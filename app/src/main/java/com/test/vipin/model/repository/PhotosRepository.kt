package com.test.vipin.model.repository

import com.test.vipin.model.RetrofitTwo
import com.test.vipin.model.api.ApiHelper
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val apiHelper: ApiHelper){
    @RetrofitTwo
    suspend fun getPhotos() = apiHelper.getPhotos()
}