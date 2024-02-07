package com.banklannister.dictionaryapp.network

import com.banklannister.dictionaryapp.data.Dictionary
import com.banklannister.dictionaryapp.utils.PATH_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET(PATH_URL)
    suspend fun getMeaning(@Path ("word") word: String): Response<List<Dictionary>>

}