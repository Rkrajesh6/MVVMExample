package com.example.demomvvmexample.model

/**
 * Created by rajeshkumar07 on 27-02-2020.
 */
data class Todo (
    val userId : Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)