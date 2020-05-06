package com.picpay.desafio.android.model.data.datasources.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.picpay.desafio.android.model.domain.entities.User
import io.reactivex.Single

@Dao
interface  UserDAO {

    @Query("SELECT * FROM users")
    fun getUsers(): Single<List<User>>

    @Transaction
    fun updateData(users: List<User>) {
        deleteAll()
        insertAll(users)
    }

    @Query("DELETE FROM users")
    fun deleteAll()

    @Insert
    fun insertAll(users: List<User>)

}