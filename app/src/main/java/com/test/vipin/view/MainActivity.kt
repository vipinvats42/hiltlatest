package com.test.vipin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.vipin.R
import com.test.vipin.databinding.ActivityMainBinding
import com.test.vipin.model.User
import com.test.vipin.utils.Status
import com.test.vipin.viewmodel.VipinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding : ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private val viewModel: VipinViewModel by viewModels()

    //private var TAG ="MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setupUI()
        setupObserver()
        setUpListener()

    }

    private fun initView(){
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    private fun setupUI() {
        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        activityMainBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activityMainBinding.recyclerView.context,
                ( activityMainBinding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        activityMainBinding.recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.users.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    activityMainBinding.progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    activityMainBinding.recyclerView.visibility = View.VISIBLE
                }

                Status.LOADING -> {
                    activityMainBinding.progressBar.visibility = View.VISIBLE
                    activityMainBinding.recyclerView.visibility = View.GONE
                }

                Status.ERROR -> {
                    //Handle Error
                    activityMainBinding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderList(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setUpListener(){
        activityMainBinding.btnMoveToNext.setOnClickListener{
            startActivity(Intent(this@MainActivity,PhotosActivity::class.java))
        }
    }

}
