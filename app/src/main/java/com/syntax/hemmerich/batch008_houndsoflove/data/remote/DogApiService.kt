package com.syntax.hemmerich.batch008_houndsoflove.data.remote

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.syntax.hemmerich.batch008_houndsoflove.data.model.DogImages
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://dog.ceo/api/breed/hound/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogApiService{

    @GET("images")
    suspend fun getImages(): DogImages
}

object DogApi{
    val retrofitService: DogApiService by lazy { retrofit.create(DogApiService::class.java) }
}

