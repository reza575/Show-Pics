package com.moeiny.reza.guesspic.presenter

import com.moeiny.reza.showpics.model.entity.Photo
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.utils.OptusCallback


interface IPhotoService {

    fun getUsersInfo(block: OptusCallback<List<Users>, Throwable>)

    fun getPhotosInfo(block: OptusCallback<List<Photo>, Throwable>)

}