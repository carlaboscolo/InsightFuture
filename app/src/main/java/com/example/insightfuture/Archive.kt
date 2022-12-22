package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.insightfuture.databinding.ActivityArchiveBinding
import com.example.insightfuture.databinding.ActivityMenuBinding
import com.example.insightfuture.roomDatabase.SibillaDatabase

class Archive : AppCompatActivity() {

    private lateinit var binding : ActivityArchiveBinding
    private lateinit var backBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_archive)
        binding = ActivityArchiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtn = binding.backBtn

        val archives = ArrayList<SibillaDatabase>()

        backBtn.setOnClickListener{
            finish()
        }
    }

}