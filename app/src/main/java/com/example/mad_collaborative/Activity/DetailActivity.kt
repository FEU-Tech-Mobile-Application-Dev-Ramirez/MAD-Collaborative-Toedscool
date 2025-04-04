package com.example.mad_collaborative.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.mad_collaborative.Domain.ItemDomain
import com.example.mad_collaborative.databinding.ActivityDetailBinding

class DetailActivity : DetailActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemDomain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setVariable()
    }

    private fun setVariable() {
        binding.titleTxt.text = item.title
        binding.priceTxt.text = "$${item.price}"
        binding.backBtn.setOnClickListener { finish() }
        binding.bedTxt.text = "${item.bed}"
        binding.durationTxt.text = item.duration
        binding.distanceTxt.text = item.distance
        binding.descriptionTxt.text = item.description
        binding.addressTxt.text = item.address
        binding.ratingTxt.text = "${item.score} Rating"
        binding.ratingBar.rating = item.score.toFloat()

        Glide.with(this)
            .load(item.pic)
            .into(binding.pic)

        binding.addToCartBtn.setOnClickListener {
            val intent = Intent(this, TicketActivity::class.java)
            intent.putExtra("object", item)
            startActivity(intent)
        }
    }

    private fun getIntentExtra() {
        item = intent.getSerializableExtra("object") as ItemDomain
    }
}