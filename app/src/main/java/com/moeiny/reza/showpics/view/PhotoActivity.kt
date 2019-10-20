package com.moeiny.reza.showpics.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.guesspic.presenter.PhotoService
import com.moeiny.reza.showpics.R
import com.moeiny.reza.showpics.viewmodel.AppViewModel
import com.moeiny.reza.showpics.adapter.PhotosAdapter
import com.moeiny.reza.showpics.databinding.ActivityPhotoBinding
import com.moeiny.reza.showpics.entity.Photo
import com.moeiny.reza.showpics.utils.OptusCallback

class PhotoActivity : AppCompatActivity() {
    lateinit var photoList: List<Photo>
    lateinit var recyclerView: RecyclerView
    lateinit var photosAdapter: PhotosAdapter
    lateinit var txtTitle: TextView
    lateinit var id:String
    lateinit var context : Context
    lateinit var appViewModel: AppViewModel
    var mBinding: ActivityPhotoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo);
        context=this
        appViewModel= ViewModelProviders.of(this).get(AppViewModel::class.java);

        val bundle = intent.extras
        appViewModel.setAlbumId (bundle?.getString("albumId").toString())
        getPhotoInfo()
        setUpView()
    }

    fun getPhotoInfo() {

        PhotoService.getPhotosInfo(object : OptusCallback<List<Photo>, Throwable> {

            override fun onSuccess(result: List<Photo>) {
                photoList = ArrayList<Photo>()
                photoList = result
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
        val photoListTemp:ArrayList<Photo> = ArrayList<Photo>()
        for(item:Photo in photoList ){
            if(item.albumId.toString().equals( appViewModel.getAlbumId())){
                photoListTemp.add(item)
            }
            appViewModel.setPhotoList(photoListTemp)
        }

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = PhotosAdapter(context!!, appViewModel.getPhotoList())
    }

    fun setUpView() {
        txtTitle = findViewById(R.id.txt_fragmentphoto_title)
        recyclerView = findViewById(R.id.rv_fragmentphoto_photo)
        recyclerView.layoutManager = LinearLayoutManager(context)
        txtTitle.setText("Album Id : "+appViewModel.getAlbumId())
    }
}
