package com.picpay.desafio.android.model.data.services

import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    fun getUsers(): Single<List<User>>

}