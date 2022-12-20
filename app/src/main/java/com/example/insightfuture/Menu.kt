package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.insightfuture.databinding.ActivityMainBinding
import com.example.insightfuture.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}