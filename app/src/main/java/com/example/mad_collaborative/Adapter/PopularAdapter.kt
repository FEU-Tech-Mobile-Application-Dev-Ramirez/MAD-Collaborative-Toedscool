package com.example.mad_collaborative.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mad_collaborative.Activity.DetailActivity
import com.example.mad_collaborative.Domain.ItemDomain
import com.example.mad_collaborative.databinding.ViewholderPopularBinding

class PopularAdapter(private val items: ArrayList<ItemDomain>) : RecyclerView.Adapter<PopularAdapter.Viewholder>() {
    private lateinit var context: Context
    private lateinit var binding: ViewholderPopularBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        binding = ViewholderPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        binding.titleTxt.text = items[position].title
        binding.priceTxt.text = "$${items[position].price}"
        binding.addressTxt.text = items[position].address
        binding.scoreTxt.text = "${items[position].score}"

        Glide.with(context)
            .load(items[position].pic)
            .into(binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("object", items[position])
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(binding: ViewholderPopularBinding) : RecyclerView.ViewHolder(binding.root)
}