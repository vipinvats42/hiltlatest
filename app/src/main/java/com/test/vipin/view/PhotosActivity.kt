package com.test.vipin.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.test.vipin.R
import com.test.vipin.databinding.ActivityPhotosBinding
import com.test.vipin.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotosActivity : AppCompatActivity() {
    lateinit var activityPhotosBinding: ActivityPhotosBinding
    val photosViewModel : PhotosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        activityPhotosBinding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
    }

}