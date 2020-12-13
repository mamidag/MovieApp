package com.example.movieapp.ui.movielist.adapter

import android.R.attr.data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.api.model.movie.Results
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.listener.IListener


class MovieListAdapter(var list: List<Results>,val itemClickListener: IListener) :
    RecyclerView.Adapter<MovieListAdapter.CategoryHolder>() {

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryHolder(
            DataBindingUtil.inflate<ItemMovieBinding>
                (
                LayoutInflater.from(parent.context),
                R.layout.item_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.itemMovieBinding.item = list[position]
        holder.bind(list[position],itemClickListener)
    }

    inner class CategoryHolder(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun bind(result: Results, clickListener: IListener) {
            itemMovieBinding.layout.setOnClickListener {
                clickListener.onClick(result)
            }

        }

    }

    fun clear() {
        list = listOf()
    }

}