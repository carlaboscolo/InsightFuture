package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.insightfuture.databinding.ActivityArchiveBinding
import com.example.insightfuture.databinding.ActivityMenuBinding

class Archive : AppCompatActivity() {

    private lateinit var binding : ActivityArchiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_archive)
        binding = ActivityArchiveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}