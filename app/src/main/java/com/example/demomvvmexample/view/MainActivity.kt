package com.example.demomvvmexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomvvmexample.R
import com.example.demomvvmexample.adapter.MainAdapter
import com.example.demomvvmexample.utils.Util
import com.example.demomvvmexample.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mAdapter: MainAdapter
    val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        setRecyclerview()

        shimmer_text.visibility = View.VISIBLE
        shimmer_text.startShimmerAnimation()

        btnReload.setOnClickListener {
            setViewModel()
            setRecyclerview()
            shimmer_text.visibility = View.VISIBLE
            shimmer_text.startShimmerAnimation()
        }
    }

    private fun setRecyclerview() {
        mAdapter = MainAdapter(this, mViewModel.listData.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun setViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel.successFulLiveData.observe(this, Observer { todoList ->
            todoList?.let {
                Log.e(TAG, "data Updated=$it")
                shimmer_text.visibility = View.GONE
                shimmer_text.stopShimmerAnimation()
                mAdapter.update(it)
            }
        })
        mViewModel.failureLiveData.observe(this, Observer { isFailed ->
            isFailed?.let {
                successconstraintlayout.visibility = View.GONE
                errorconstratintlayout.visibility = View.VISIBLE
            }
        })
        if (Util.isNetworkAvailable(this)) {
            successconstraintlayout.visibility = View.VISIBLE
            errorconstratintlayout.visibility = View.GONE
            mViewModel.getResponseData()
        } else {
            successconstraintlayout.visibility = View.GONE
            errorconstratintlayout.visibility = View.VISIBLE
        }
    }
}
