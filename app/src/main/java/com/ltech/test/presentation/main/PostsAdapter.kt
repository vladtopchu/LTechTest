package com.ltech.test.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ltech.test.R
import com.ltech.test.domain.model.Post
import com.ltech.test.utils.Constants.BASE_URL


class PostsAdapter(
    private val context: Context,
    private val postsList: List<Post>,
    private val onItemClick: (postId: String) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postsList[position]

        Glide.with(context).load(BASE_URL + post.image).into(holder.image)

        holder.title.text = post.title
        holder.description.text = post.text
        holder.date.text = post.date

        holder.itemView.setOnClickListener {
            onItemClick(post.id)
        }
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: AppCompatImageView
        var title: AppCompatTextView
        var description: AppCompatTextView
        var date: AppCompatTextView

        init {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.text_title)
            description = itemView.findViewById(R.id.text_description)
            date = itemView.findViewById(R.id.text_date)
        }
    }
}