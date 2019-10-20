package com.moeiny.reza.showpics

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.guesspic.presenter.PhotoService
import com.moeiny.reza.showpics.viewmodel.AppViewModel
import com.moeiny.reza.showpics.adapter.UserAdapter
import com.moeiny.reza.showpics.databinding.ActivityMainBinding
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.utils.OptusCallback

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var usersList: List<Users>
    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: UserAdapter
    lateinit var context : Context
    lateinit var appViewModel: AppViewModel
    var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        context=this
        appViewModel= ViewModelProviders.of(this).get(AppViewModel::class.java);
        setUpView()
        getUsersInfo()
    }


    fun getUsersInfo() {

        PhotoService.getUsersInfo(object : OptusCallback<List<Users>, Throwable> {

            override fun onSuccess(result: List<Users>) {
                usersList = ArrayList<Users>()
                appViewModel.setUserList(result)
                setDataOnRecycler()
            }

            override fun onError(error: Throwable?) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onComplete() {
                print("complete")
            }

        })
    }

    fun setDataOnRecycler() {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = UserAdapter(context!!, appViewModel.getUserList())
    }

    fun setUpView() {
        recyclerView = findViewById(R.id.rv_fragmentUser_users)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}

