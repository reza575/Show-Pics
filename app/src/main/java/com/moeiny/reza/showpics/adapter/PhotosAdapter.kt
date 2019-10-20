package com.moeiny.reza.showpics.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.showpics.R
import com.moeiny.reza.showpics.model.entity.Photo
import com.moeiny.reza.showpics.view.ShowActivity
import com.squareup.picasso.Picasso

class PhotosAdapter(var context:Context, var photoList:List<Photo>):RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var view:View=LayoutInflater.from(context).inflate(R.layout.photorow,parent,false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.count()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        var photo:Photo=photoList.get(position)
        Picasso.get().load(photo.thumbnailUrl).into(holder.imgPicUrl)
        holder.txttitle.setText(photo.title)

        holder.parent.setOnClickListener(){
            val intent = Intent(context, ShowActivity::class.java)
            intent.putExtra("albumId", photo.albumId.toString())
            intent.putExtra("photoid", photo.id.toString())
            intent.putExtra("url", photo.url.toString())
            intent.putExtra("title", photo.title.toString())
            context!!.startActivity(intent)
        }
    }

    inner class PhotoViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        var txttitle= itemView!!.findViewById<TextView>(R.id.txt_photoRow_title)
        var imgPicUrl=itemView!!.findViewById<ImageView>(R.id.img_photoRow_icon)
        var parent=itemView!!.findViewById<CardView>(R.id.rel_photorow_parent)
    }
}