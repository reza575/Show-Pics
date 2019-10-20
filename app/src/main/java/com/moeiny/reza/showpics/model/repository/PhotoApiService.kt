package com.moeiny.reza.guesspic.model.repository

import com.moeiny.reza.showpics.entity.Photo
import com.moeiny.reza.showpics.model.entity.users.Users
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.http.*

interface PhotoApiService {

    @GET
    fun getUsersInfo(@Url url: String): Flowable<List<Users>>

    @GET
    fun getPhotosInfo(@Url url: String): Flowable<List<Photo>>


    companion object Factory {
        fun create(retrofit: Retrofit): PhotoApiService {
            return retrofit.create(PhotoApiService::class.java)
        }
    }
}