package com.picpay.desafio.android.model.data.datasources.cache

import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

interface CacheDataSource {

    fun getUsers() : Single<List<User>>

    fun insertUsers(users: List<User>)

    fun updateUsers(users: List<User>)
}