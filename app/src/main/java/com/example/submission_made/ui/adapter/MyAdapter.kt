package com.example.submission_made.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.submission_made.R
import com.example.submission_made.data.pojo.MovieData
import com.example.submission_made.ui.base.BaseAdapter
import com.example.submission_made.ui.callbacks.ListCallback
import com.squareup.picasso.Picasso

class MyAdapter(var listCallback: ListCallback) : BaseAdapter<MyAdapter.ViewHolder, MovieData>() {

    private var dataList = ArrayList<MovieData>()

    override fun setData(data: ArrayList<MovieData>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        val holder = ViewHolder(view)
        holder.container.setOnClickListener {

            listCallback.onItemClicked(
                holder.mImageView,
                dataList[holder.layoutPosition]
            )

        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = dataList[position].getPosterImageUrl()

        Picasso.with(holder.container.context)
            .load(url)
            .into(holder.mImageView)

        ViewCompat.setTransitionName(holder.mImageView, "TRANSITIONNAME_" + holder.layoutPosition)

        holder.title.text = dataList[position].title
        holder.overview.text = dataList[position].overview
        holder.releaseDate.text = dataList[position].release_date
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: LinearLayout
        var mImageView: ImageView
        var title: TextView
        var overview: TextView
        var releaseDate: TextView

        init {
            container = itemView.findViewById(R.id.container)
            mImageView = itemView.findViewById(R.id.movieImage) as ImageView
            title = itemView.findViewById(R.id.title)
            overview = itemView.findViewById(R.id.overview)
            releaseDate = itemView.findViewById(R.id.releaseDate)
        }
    }

}

