package com.example.demomvvmexample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demomvvmexample.model.Todo
import com.example.demomvvmexample.network.RetrofitService
import java.lang.Exception

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
class MainRepository {
    private val retrofitService = RetrofitService.create()
    val successLiveData = MutableLiveData<List<Todo>>()
    val failureLiveData = MutableLiveData<Boolean>()
    val TAG = MainRepository::class.java.simpleName

    suspend fun getTodoList() {
        try {
            val response = retrofitService.getTodo()

            if (response.isSuccessful) {
                Log.e(TAG, "Success")
                Log.e(TAG, "${response.body()}")
                successLiveData.postValue(response.body())
            } else {
                Log.e(TAG, "Failure")
                Log.e(TAG, "${response.body()}")
                failureLiveData.postValue(true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}