package com.moeiny.reza.guesspic.model.repository



import com.moeiny.reza.showpics.model.entity.Photo
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.utils.API
import io.reactivex.Flowable

class PhotoRepository(private val photoApiService: PhotoApiService) {

    fun getUsersInfo(): Flowable<List<Users>> {
        return photoApiService.getUsersInfo(API.GET_USERS_URL.value)
    }

    fun getPhotosInfo(): Flowable<List<Photo>> {
        return photoApiService.getPhotosInfo(API.GET_PHOTOS_URL.value)
    }
}