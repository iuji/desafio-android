package com.picpay.desafio.android.model.data.datasources.remote

import com.picpay.desafio.android.model.data.services.PicPayService
import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

class RemoteDataSourceImpl(
    private val service: PicPayService
) : RemoteDataSource {

    override fun getUsers() : Single<List<User>> =
        service.getUsers()

}