package com.moeiny.reza.showpics.viewmodel

import androidx.lifecycle.ViewModel
import com.moeiny.reza.showpics.model.entity.Photo
import com.moeiny.reza.showpics.model.entity.users.Users

class AppViewModel: ViewModel() {
    private  var selectedAlbumId:String=""
    private  var selectedPhotoId:String=""
    private  var selectedUrl:String=""
    private  var selectedTitle:String=""
    private  var usersLists: List<Users> = ArrayList<Users>()
    private  var photoLists: List<Photo> = ArrayList<Photo>()

    public fun setAlbumId(albumId:String){
        selectedAlbumId=albumId
    }

    public fun setPhotoId(photoId:String){
        selectedPhotoId=photoId
    }

    public fun seturl(url:String){
        selectedUrl=url
    }

    public fun setTitle(title:String){
        selectedTitle=title
    }

    public fun setUserList(userList:List<Users>){
        usersLists=userList
    }

    public fun setPhotoList(photoList:List<Photo>){
        photoLists=photoList
    }

    public fun getAlbumId():String{
        return(selectedAlbumId)
    }

    public fun getPhotoId():String{
        return(selectedPhotoId)
    }

    public fun geturl():String{
        return(selectedUrl)
    }

    public fun getTitle():String{
        return(selectedTitle)
    }

    public fun getUserList():List<Users>{
        return(usersLists)
    }

    public fun getPhotoList():List<Photo>{
        return(photoLists)
    }

}

