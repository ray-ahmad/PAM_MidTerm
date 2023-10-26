package com.example.tugas1pam

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rayahmad.pammidterm.databinding.RowUsersBinding
import com.rayahmad.pammidterm.model.UsersModel

class UsersAdapter(
    private var listData: List<UsersModel>
): RecyclerView.Adapter<UsersAdapter.DataViewHolder>() {
//    val users: List<UsersModel>

    //    private lateinit var OnClickCallBack: onClickCallBack
//
//    fun setOnClickCallBack(data: onClickCallBack){
//        this.OnClickCallBack = data
//    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(RowUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val (id,email,first_name,last_name,avatar) = listData[position]
        holder.binding.txtUsername.text = "$first_name $last_name"
        holder.binding.txtEmail.text = email
        Glide.with(holder.binding.avatar.context).load(avatar).into(holder.binding.avatar)

//        holder.itemView.setOnClickListener{
//            OnClickCallBack.onItemClicked(listData[holder.absoluteAdapterPosition])
//        }
    }

    fun setFilteredList(filterList: List<UsersModel>){
        this.listData = filterList as ArrayList<UsersModel>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listData.count()
    }

    class DataViewHolder(val binding: RowUsersBinding) : RecyclerView.ViewHolder(binding.root)

//    interface onClickCallBack{
//        fun onItemClicked(data: Skills)
//    }

}