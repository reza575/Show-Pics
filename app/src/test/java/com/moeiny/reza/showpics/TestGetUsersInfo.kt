package com.moeiny.reza.showpics

import com.moeiny.reza.guesspic.presenter.PhotoService
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.utils.OptusCallback
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.util.concurrent.CountDownLatch

class TestGetUsersInfo {
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetUsersInfo() {

        val lock =  CountDownLatch(1)
        lateinit var users: List<Users>


        PhotoService.getUsersInfo(object : OptusCallback<List<Users>, Throwable> {

            override fun onSuccess(result: List<Users>) {
                Assert.assertNotNull(result)
                users = result
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

        users.let {
            Assert.assertEquals(it[0].username, "Bret")
            Assert.assertEquals(it[0].name, "Leanne Graham")
            Assert.assertEquals(it[0].address.city, "Gwenborough")
            Assert.assertEquals(it[0].company.name, "Romaguera-Crona")
        }
    }
}