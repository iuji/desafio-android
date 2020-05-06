package com.picpay.desafio.android.model.data.repositories

import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

interface RemoteRepository {
    fun getUsers(forceUpdate: Boolean) : Single<List<User>>
}