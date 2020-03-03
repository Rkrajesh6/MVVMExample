package com.example.demomvvmexample.network

import com.example.demomvvmexample.model.Heroes
import com.example.demomvvmexample.model.Todo
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
interface Api {
    @GET("/todos")
    suspend fun getTodo() : Response<List<Todo>>

    @GET("marvel")
    suspend fun getHeroes() : Response<List<Heroes>>
}