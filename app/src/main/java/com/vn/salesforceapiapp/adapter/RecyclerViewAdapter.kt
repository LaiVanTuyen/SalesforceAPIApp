package com.vn.salesforceapiapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vn.salesforceapiapp.R
import com.vn.salesforceapiapp.databinding.PostRvItemBinding
import com.vn.salesforceapiapp.model.Feed


class RecyclerViewAdapter(private var postList: List<Feed>) :RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    inner class ViewHolder(val binding: PostRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_rv_item,parent,false)
        return ViewHolder(PostRvItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val post = postList[position]
        holder.binding.txtContent.text = post.feed.body
        holder.binding.txtName.text = post.feed.createdBy.name
        holder.binding.txtCompany.text = post.feed.insertedBy.companyName
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    fun updateData(newPostList: List<Feed>) {
        postList = newPostList
        notifyDataSetChanged()
    }

}