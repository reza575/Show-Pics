package com.moeiny.reza.showpics

import com.moeiny.reza.guesspic.presenter.PhotoService
import com.moeiny.reza.showpics.model.entity.Photo
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.utils.OptusCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetPhotosInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetPhotosInfo() {

        val lock =  CountDownLatch(1)
        lateinit var photos: List<Photo>


        PhotoService.getPhotosInfo(object : OptusCallback<List<Photo>, Throwable> {

            override fun onSuccess(result: List<Photo>) {
                Assert.assertNotNull(result)
                photos = result
                lock.countDown()
            }

            override fun onError(error: Throwable?) {
                Assert.assertNotNull(error)
                lock.countDown()
            }

            override fun onComplete() {
                print("complete")
                lock.countDown()
            }
        })

        photos.let {
            Assert.assertEquals(it[0].albumId, 1)
            Assert.assertEquals(it[0].title, "accusamus beatae ad facilis cum similique qui sunt")
            Assert.assertEquals(it[0].url, "https://via.placeholder.com/600/92c952")
        }
    }
}