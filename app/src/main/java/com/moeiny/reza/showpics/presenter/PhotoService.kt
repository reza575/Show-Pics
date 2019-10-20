package com.moeiny.reza.guesspic.presenter


import com.moeiny.reza.guesspic.model.repository.PhotoRepositoryProvider
import com.moeiny.reza.showpics.entity.Photo
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.utils.OptusCallback
import com.moeiny.reza.showpics.utils.okHttpClientGETBuilder
import com.moeiny.reza.showpics.utils.retrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class PhotoService private constructor() {

    companion object {
        private var compositeDisposable: CompositeDisposable = CompositeDisposable()

        fun getUsersInfo(block: OptusCallback<List<Users>, Throwable>) {
            val disposableService: Disposable = PhotoRepositoryProvider.providePhotoRepository(retrofit(okHttpClientGETBuilder()))
                .getUsersInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
              //  .observeOn(Schedulers.computation())
                    .subscribe(
                            { result ->
                                block.onSuccess(result = result)
                            },
                            {error ->
                                block.onError(error)
                            },
                            {
                                block.onComplete()
                            }
                    )

            compositeDisposable.add(disposableService)

        }

        fun getPhotosInfo(block: OptusCallback<List<Photo>, Throwable>) {
            val disposableService: Disposable = PhotoRepositoryProvider.providePhotoRepository(retrofit(okHttpClientGETBuilder()))
                .getPhotosInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
             //   .observeOn(Schedulers.computation())
                .subscribe(
                    { result ->
                        block.onSuccess(result = result)
                    },
                    {error ->
                        block.onError(error)
                    },
                    {
                        block.onComplete()
                    }
                )

            compositeDisposable.add(disposableService)

        }

    }

}