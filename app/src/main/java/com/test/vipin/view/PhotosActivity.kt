package com.test.vipin.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.vipin.R
import com.test.vipin.databinding.ActivityPhotosBinding
import com.test.vipin.model.Photos
import com.test.vipin.utils.Status
import com.test.vipin.viewmodel.PhotosViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class PhotosActivity : AppCompatActivity() {
    private lateinit var activityPhotosBinding: ActivityPhotosBinding
    private val photosViewModel : PhotosViewModel by viewModels()
    private lateinit var adapter: PhotosAdapter

    private val TAG = "PhotosActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupUI()

       CoroutineScope(Dispatchers.IO).launch {
            observeData()
        }
    }

    private fun initView(){
        activityPhotosBinding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
    }

    private suspend fun observeData(){
        withContext(Dispatchers.Main) {
            photosViewModel.photosStateFlow.collect {
                when (it?.status) {
                    Status.SUCCESS -> {
                        Log.e(TAG, "vipin success")
                        it.data?.let { photo -> renderList(photo) }
                        activityPhotosBinding.recyclerView.visibility = View.VISIBLE
                    }

                    Status.LOADING -> {
                        Log.e(TAG, "vipin loading")
                        activityPhotosBinding.recyclerView.visibility = View.GONE
                    }

                    Status.ERROR -> {
                        Log.e(TAG, "vipin error")
                        activityPhotosBinding.recyclerView.visibility = View.GONE
                        Toast.makeText(this@PhotosActivity, it.message, Toast.LENGTH_LONG).show()
                    }

                    else -> {}
                }
            }
        }
    }
    private fun setupUI() {
        activityPhotosBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PhotosAdapter(arrayListOf())
        activityPhotosBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activityPhotosBinding.recyclerView.context,
                ( activityPhotosBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        activityPhotosBinding.recyclerView.adapter = adapter
    }

    private fun renderList(photos: Photos) {
        adapter.addData(photos)
        adapter.notifyDataSetChanged()
    }

}