package com.test.vipin.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.vipin.R
import com.test.vipin.databinding.ActivityPhotosBinding
import com.test.vipin.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosActivity : AppCompatActivity() {
    private lateinit var activityPhotosBinding: ActivityPhotosBinding
    private val photosViewModel : PhotosViewModel by viewModels()

    private val TAG = "PhotosActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

       CoroutineScope(Dispatchers.IO).launch {
            getPhotosData()
        }
    }

    private fun initView(){
        activityPhotosBinding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
    }

    private suspend fun getPhotosData(){
        photosViewModel.photosStateFlow.collect {
            it.forEach{
                Log.e(TAG,"vipin 38 ${it.title}")
                Log.e(TAG,"vipin 39 ${it.id}")
                Log.e(TAG,"vipin 40 ${it.albumId}")
                Log.e(TAG,"vipin 41 ${it.thumbnailUrl}")
                Log.e(TAG,"vipin 42 ${it.url}")
            }
        }
    }

}