package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.insightfuture.databinding.ActivityStoryBinding

class Story : AppCompatActivity() {

    private lateinit var binding : ActivityStoryBinding
    private lateinit var backBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_story)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtn = binding.backBtn

        backBtn.setOnClickListener{
            finish()
        }


    }
}