package com.picpay.desafio.android.model.data.repositories

import com.picpay.desafio.android.model.data.datasources.cache.CacheDataSource
import com.picpay.desafio.android.model.data.datasources.remote.RemoteDataSource
import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val cacheDataSource: CacheDataSource
) : RemoteRepository {

    override fun getUsers(forceUpdate: Boolean): Single<List<User>> {
        return if (forceUpdate) {
            getUsersRemote(forceUpdate)
        }
        else {
            getUsersCached()
        }

    }

    private fun getUsersCached(): Single<List<User>> {
        println("consultou o DB")
        return cacheDataSource.getUsers()
            .flatMap { userList ->
                when {
                    userList.isEmpty() -> getUsersRemote(false)
                    else -> Single.just(userList)
                }
            }
    }

    private fun getUsersRemote(isUpdate: Boolean): Single<List<User>> {
        println("consultou o API")
        return remoteDataSource.getUsers()
            .flatMap { userList ->
                if (isUpdate) {
                    println("atualizou o DB")
                    cacheDataSource.updateUsers(userList)
                }
                else {
                    println("inseriou no DB")
                    cacheDataSource.insertUsers(userList)
                }

                Single.just(userList)
            }
    }

}