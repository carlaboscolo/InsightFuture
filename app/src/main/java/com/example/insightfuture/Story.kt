package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.insightfuture.databinding.ActivityMenuBinding
import com.example.insightfuture.databinding.ActivityStoryBinding

class Story : AppCompatActivity() {

    private lateinit var binding : ActivityStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_story)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}