package com.vn.salesforceapiapp.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.vn.salesforceapiapp.R
import com.vn.salesforceapiapp.databinding.PostRvItemBinding
import com.vn.salesforceapiapp.model.Feed
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


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
        val html = HtmlCompat.fromHtml(post.feed.body, HtmlCompat.FROM_HTML_MODE_COMPACT)
        holder.binding.txtContent.text = html
        holder.binding.txtName.text = post.feed.createdBy.name
        holder.binding.txtCompany.text = post.feed.insertedBy.companyName
        val formattedDateDifference = calculateDateDifference(post.feed.createdDate)
        holder.binding.txtTime.text = formattedDateDifference
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    fun updateData(newPostList: List<Feed>) {
        postList = newPostList
        notifyDataSetChanged()
    }

    fun calculateDateDifference(isoDateString: String): String {
        val inputFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val createdDate = LocalDateTime.parse(isoDateString, inputFormat)
        val currentDate = LocalDateTime.now()

        val diffInDays = ChronoUnit.DAYS.between(createdDate, currentDate)
        val diffInHours = ChronoUnit.HOURS.between(createdDate, currentDate)
        val diffInMinutes = ChronoUnit.MINUTES.between(createdDate, currentDate)

        return when {
            diffInDays > 0 -> "$diffInDays days ago"
            diffInHours > 0 -> "$diffInHours hours ago"
            else -> "$diffInMinutes minutes ago"
        }
    }

}