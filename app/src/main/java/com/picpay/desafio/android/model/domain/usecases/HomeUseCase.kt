package com.picpay.desafio.android.model.domain.usecases

import com.picpay.desafio.android.extensions.defaultSchedulers
import com.picpay.desafio.android.model.data.repositories.RemoteRepository

class HomeUseCase(
    private val repository: RemoteRepository
) {
    fun getUsers(forceUpdate: Boolean) = repository.getUsers(forceUpdate)
        .defaultSchedulers()
}