package com.test.a1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.test.a1.data.network.response.NewsInfoResponse
import com.test.a1.databinding.NewsItemBinding

class NewsAdapter(
    private val inflater: LayoutInflater,
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var items = mutableListOf<NewsInfoResponse>()

    fun updateList(newList: List<NewsInfoResponse>) {
        items.clear()
        items.addAll(newList)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(NewsItemBinding.inflate(inflater,parent,false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class NewsViewHolder(private val binding: NewsItemBinding) : ViewHolder(binding.root) {

        fun bind(newsInfoResponse: NewsInfoResponse) {
            with(binding) {
                if (newsInfoResponse.img.isNotEmpty())
                    Picasso.get().load(newsInfoResponse.img).into(imNews)

                tvNewsTitle.text = newsInfoResponse.tittle
                tvDate.text = newsInfoResponse.date
                tvNewsText.text = newsInfoResponse.text
            }
        }
    }
}