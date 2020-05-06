package com.picpay.desafio.android.model.data.datasources.cache

import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

class CacheDataSourceImpl(
    private val userDAO: UserDAO
) : CacheDataSource{

    override fun getUsers() : Single<List<User>> = userDAO.getUsers()

    override fun insertUsers(users: List<User>) {
        userDAO.insertAll(users)
    }

    override fun updateUsers(users: List<User>) {
        userDAO.updateData(users)
    }
}