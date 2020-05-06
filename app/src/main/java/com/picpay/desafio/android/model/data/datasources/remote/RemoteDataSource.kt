package com.picpay.desafio.android.model.data.datasources.remote

import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

interface RemoteDataSource {

    fun getUsers() : Single<List<User>>

}