package com.example.mad_collaborative.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.mad_collaborative.Domain.ItemDomain
import com.example.mad_collaborative.databinding.ActivityTicketBinding

class TicketActivity : BaseActivity() {
    private lateinit var binding: ActivityTicketBinding
    private lateinit var `object`: ItemDomain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentExtra()
        setVariable()
    }

    private fun setVariable() {
        Glide.with(this)
            .load(`object`.pic)
            .into(binding.pic)

        Glide.with(this)
            .load(`object`.tourGuidePic)
            .into(binding.profile)

        binding.backBtn.setOnClickListener { finish() }
        binding.titleTxt.text = `object`.title
        binding.durationTxt.text = `object`.duration
        binding.tourGuideTxt.text = `object`.dateTour
        binding.timeTxt.text = `object`.timeTour
        binding.tourGuideNameTxt.text = `object`.tourGuideName

        binding.callBtn.setOnClickListener {
            val sendIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("sms:${`object`.tourGuidePhone}")
                putExtra("sms_body", "type your message")
            }
            startActivity(sendIntent)
        }

        binding.messageBtn.setOnClickListener {
            val phone = `object`.tourGuidePhone
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }
    }

    private fun getIntentExtra() {
        `object` = intent.getSerializableExtra("object") as ItemDomain
    }
}