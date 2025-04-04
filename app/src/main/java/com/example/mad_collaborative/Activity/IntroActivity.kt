package com.example.mad_collaborative.Activity

import android.content.Intent
import android.os.Bundle
import com.example.mad_collaborative.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.introBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}