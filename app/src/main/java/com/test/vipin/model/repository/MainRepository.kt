package com.test.vipin.model.repository

import com.test.vipin.model.RetrofitOne
import com.test.vipin.model.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    @RetrofitOne
    suspend fun getUsers() = apiHelper.getUsers()

}