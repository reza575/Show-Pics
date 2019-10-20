package com.moeiny.reza.showpics.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.moeiny.reza.showpics.R
import com.moeiny.reza.showpics.databinding.ActivityShowBinding
import com.moeiny.reza.showpics.viewmodel.AppViewModel
import com.squareup.picasso.Picasso

class ShowActivity : AppCompatActivity() {

    lateinit var txtalbumid: TextView
    lateinit var txtphotoid: TextView
    lateinit var txttitle: TextView
    lateinit var imgPicUrl: ImageView
    lateinit var appViewModel: AppViewModel
    var mBinding: ActivityShowBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_show);

        appViewModel= ViewModelProviders.of(this).get(AppViewModel::class.java);

        val bundle = intent.extras
        appViewModel.setAlbumId(bundle?.getString("albumId").toString())
        appViewModel.setPhotoId(bundle?.getString("photoid").toString())
        appViewModel.seturl(bundle?.getString("url").toString())
        appViewModel.setTitle(bundle?.getString("title").toString())
        setUpView()
    }

    fun setUpView() {
        txtalbumid = findViewById(R.id.txt_showFragment_albumid)
        txtphotoid = findViewById(R.id.txt_showFragment_photoid)
        txttitle = findViewById(R.id.txt_showFragment_title)
        imgPicUrl = findViewById(R.id.img_showFragment_photo)
        txtalbumid.setText("Album Id : "+appViewModel.getAlbumId())
        txtphotoid.setText("Photo Id : "+appViewModel.getPhotoId())
        txttitle.setText(appViewModel.getTitle())
        Picasso.get().load(appViewModel.geturl()).into(imgPicUrl)
    }
}
