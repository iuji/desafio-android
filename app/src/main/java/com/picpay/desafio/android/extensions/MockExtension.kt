package com.picpay.desafio.android.extensions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.IOException
import java.nio.charset.Charset

inline fun <reified T> Context.getMock(fileName: String, gson: Gson): Single<T> {
    val json = this.createJsonObject("mocks/$fileName")
    return Single.just(gson.fromJson(json, object: TypeToken<T>(){}.type))
}

fun Context.createJsonObject(fileName: String): String? {
    var json: String ? = null

    try {
        val fileNamePath =
            if(!fileName.contains("mocks/")) "mocks/$fileName"
            else fileName

        val inputStream = this.assets.open(fileNamePath)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charset.forName("UTF-8"))
     } catch (e: IOException) {
        e.printStackTrace()
    }

    return json
}