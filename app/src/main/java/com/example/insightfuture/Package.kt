package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.insightfuture.databinding.ActivityMenuBinding
import com.example.insightfuture.databinding.ActivityPackageBinding

class Package : AppCompatActivity() {

    private lateinit var binding : ActivityPackageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_package)
        binding = ActivityPackageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}