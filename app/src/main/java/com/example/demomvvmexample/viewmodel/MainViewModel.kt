package com.example.demomvvmexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demomvvmexample.model.Todo
import com.example.demomvvmexample.repository.MainRepository
import kotlinx.coroutines.launch

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
class MainViewModel : ViewModel(){
    private val mMainRepository = MainRepository()

    val successFulLiveData = mMainRepository.successLiveData
    val failureLiveData = mMainRepository.failureLiveData

    private var mutableList = MutableLiveData<List<Todo>>().apply { value = emptyList() }

    val listData : LiveData<List<Todo>> = mutableList

    fun getResponseData(){
        viewModelScope.launch {
            mMainRepository.getTodoList()
        }
    }
}