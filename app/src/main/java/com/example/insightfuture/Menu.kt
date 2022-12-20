package com.example.insightfuture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.insightfuture.databinding.ActivityMainBinding
import com.example.insightfuture.databinding.ActivityMenuBinding

class Menu : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding

    private lateinit var questionSibBtn : Button
    private lateinit var storyBtn : Button
    private lateinit var archiveBtn : Button
    private lateinit var packageBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionSibBtn = binding.questionSibBtn
        storyBtn = binding.storyBtn
        archiveBtn = binding.archiveBtn
        packageBtn = binding.packageBtn

        questionSibBtn.setOnClickListener{
            launchSecondActivity()
        }

    }

    private fun launchSecondActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

