package com.picpay.desafio.android.model.data.datasources.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.model.domain.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDAO

    companion object {
        fun createDataBase(context: Context) : UserDAO {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "Users.db")
                .build()
                .userDao()
        }
    }
}