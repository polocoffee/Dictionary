package com.banklannister.dictionaryapp.network

import com.banklannister.dictionaryapp.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object Instance {


     fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val api: DictionaryApi = getInstance().create(DictionaryApi::class.java)
}