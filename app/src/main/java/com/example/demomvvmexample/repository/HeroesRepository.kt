package com.example.demomvvmexample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demomvvmexample.model.Heroes
import com.example.demomvvmexample.network.RetrofitService
import java.lang.Exception

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
class HeroesRepository {
    private val TAG = HeroesRepository::class.java.simpleName
    private val retorfit = RetrofitService.create()
    var successLiveData = MutableLiveData<List<Heroes>>()
    var failureLiveData = MutableLiveData<Boolean>()

    suspend fun getResponseData() {
        try {
            val respone = retorfit.getHeroes()
            if (respone.isSuccessful) {
                Log.e(TAG, "Sucess=${respone.body()}")
                successLiveData.postValue(respone.body())
            } else {
                Log.e(TAG, "Failure=${respone.body()}")
                failureLiveData.postValue(true)
            }
        } catch (e: Exception) {
            failureLiveData.postValue(true)
            e.printStackTrace()
        }
    }
}