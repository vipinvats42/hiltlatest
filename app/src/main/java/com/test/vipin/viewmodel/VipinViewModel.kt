package com.test.vipin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.vipin.model.User
import com.test.vipin.model.repository.MainRepository
import com.test.vipin.utils.NetworkHelper
import com.test.vipin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VipinViewModel @Inject constructor(
     private val mainRepository: MainRepository,
     private val networkHelper: NetworkHelper
) :  ViewModel() {

     private val _users = MutableLiveData<Resource<List<User>>>()
     val users: LiveData<Resource<List<User>>>
          get() = _users

     init {
          fetchUsers()
     }

     private fun fetchUsers() {
          viewModelScope.launch {
               _users.postValue(Resource.loading(null))
               if (networkHelper.isNetworkConnected()) {
                    mainRepository.getUsers().let {
                         if (it.isSuccessful) {
                              _users.postValue(Resource.success(it.body()))
                         } else _users.postValue(Resource.error(it.errorBody().toString(), null))
                    }
               } else _users.postValue(Resource.error("No internet connection", null))
          }
     }
}