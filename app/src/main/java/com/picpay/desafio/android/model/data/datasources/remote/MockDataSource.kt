package com.picpay.desafio.android.model.data.datasources.remote

import android.content.Context
import com.google.gson.Gson
import com.picpay.desafio.android.extensions.getMock
import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

class MockDataSource(
    private val gson: Gson,
    private val context: Context
) : RemoteDataSource {

    override fun getUsers(): Single<List<User>>  = context.getMock("UsersResponse.json", gson)

}