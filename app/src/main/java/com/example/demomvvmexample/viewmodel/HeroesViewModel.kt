package com.example.demomvvmexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demomvvmexample.model.Heroes
import com.example.demomvvmexample.repository.HeroesRepository
import kotlinx.coroutines.launch

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
class HeroesViewModel : ViewModel() {
    private val mHeroesRepository = HeroesRepository()

    val successfulLiveData = mHeroesRepository.successLiveData
    val failureLiveData = mHeroesRepository.failureLiveData

    val mutableList = MutableLiveData<List<Heroes>>().apply { value = emptyList() }

    val listData: LiveData<List<Heroes>> = mutableList

    fun getHeroesData() {
        viewModelScope.launch {
            mHeroesRepository.getResponseData()
        }
    }
}