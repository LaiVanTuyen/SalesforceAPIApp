package com.vn.salesforceapiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LanguageRVAdapter(
    private var lngList: ArrayList<String>,
) : RecyclerView.Adapter<LanguageRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageRVAdapter.ViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.lng_rv_item,
            parent, false
        )
        // at last we are returning our view holder
        // class with our item View File.
        return LanguageRVAdapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LanguageRVAdapter.ViewHolder, position: Int) {
        // on below line we are setting text to our text view.
        holder.lngTV.text = lngList.get(position)
    }

    override fun getItemCount(): Int {
        // on below line we are
        // returning the size of list.
        return lngList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our text view.
        val lngTV: TextView = itemView.findViewById(R.id.idTVLngName)
    }
}