package com.moeiny.reza.guesspic.model.repository

import retrofit2.Retrofit

object PhotoRepositoryProvider {

    fun providePhotoRepository(retrofit: Retrofit) : PhotoRepository {
        return PhotoRepository(PhotoApiService.create(retrofit))
    }
}