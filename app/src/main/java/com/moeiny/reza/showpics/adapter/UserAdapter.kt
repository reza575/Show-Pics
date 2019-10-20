package com.moeiny.reza.showpics.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.showpics.R
import com.moeiny.reza.showpics.model.entity.users.Users
import com.moeiny.reza.showpics.view.PhotoActivity
import com.moeiny.reza.showpics.viewmodel.AppViewModel

class UserAdapter(var context:Context, var usersList:List<Users>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view:View=LayoutInflater.from(context).inflate(R.layout.usersrow,parent,false)
        var appViewModel: AppViewModel
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return usersList.count()
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var users:Users=usersList.get(position)
        holder.txtId.setText("ID : "+users.id)
        holder.txtName.setText("Name : "+users.name)
        holder.txtEmail.setText("Email : "+users.email)
        holder.txtPhone.setText("Phone : "+users.phone)

        holder.parent.setOnClickListener(){
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putExtra("albumId", users.id.toString())
            context!!.startActivity(intent)
        }
    }

    inner class UserViewHolder(itemView: View?):RecyclerView.ViewHolder(itemView!!){
        var txtId= itemView!!.findViewById<TextView>(R.id.txt_usersRow_id)
        var txtName= itemView!!.findViewById<TextView>(R.id.txt_usersRow_name)
        var txtEmail= itemView!!.findViewById<TextView>(R.id.txt_usersRow_email)
        var txtPhone= itemView!!.findViewById<TextView>(R.id.txt_usersRow_phone)
        var parent=itemView!!.findViewById<CardView>(R.id.rel_usersRow_parent)
    }
}