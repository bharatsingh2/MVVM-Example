package com.example.android.hackerearchpractice.vo

interface ResponseHandler<T> {
    fun response(response: T)
}