package com.example.demomvvmexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demomvvmexample.R
import com.example.demomvvmexample.adapter.HeroesAdapter
import com.example.demomvvmexample.utils.Util
import com.example.demomvvmexample.viewmodel.HeroesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class HerosActivity : AppCompatActivity() {
    val TAG = HerosActivity::class.java.simpleName
    private lateinit var mAdapter: HeroesAdapter
    private lateinit var mViewModel: HeroesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heros)
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
        mAdapter = HeroesAdapter(this, mViewModel.listData.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun setViewModel() {
        mViewModel = ViewModelProviders.of(this).get(HeroesViewModel::class.java)
        mViewModel.successfulLiveData.observe(this, Observer { listItem ->
            listItem?.let {
                Log.e(TAG, "data $it")
                shimmer_text.visibility = View.GONE
                shimmer_text.stopShimmerAnimation()
                mAdapter.update(it)
            }
        })
        mViewModel.failureLiveData.observe(this, Observer { isFailed ->
            isFailed.let {
                successconstraintlayout.visibility = View.GONE
                errorconstratintlayout.visibility = View.VISIBLE
            }
        })
        if (Util.isNetworkAvailable(this)) {
            successconstraintlayout.visibility = View.VISIBLE
            errorconstratintlayout.visibility = View.GONE
            mViewModel.getHeroesData()
        } else {
            successconstraintlayout.visibility = View.GONE
            errorconstratintlayout.visibility = View.VISIBLE
        }
    }
}
