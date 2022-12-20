package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.insightfuture.databinding.ActivityMainBinding
import com.example.insightfuture.databinding.ActivitySibilliaBinding

class Sibillia : AppCompatActivity() {

    private lateinit var binding: ActivitySibilliaBinding

    private lateinit var sibilliaResponse : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_sibillia)
        binding = ActivitySibilliaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sibilliaResponse = binding.sibilliaResponse

        var str1pos1 = intent.getStringExtra("str1pos1")
        var str2pos1 =  intent.getStringExtra("str2pos1")
        var str1pos2 = intent.getStringExtra("str1pos2")
        var str2pos2 =  intent.getStringExtra("str2pos2")
        var str1pos3 = intent.getStringExtra("str1pos3")
        var str2pos3 =  intent.getStringExtra("str2pos3")

       sibilliaResponse.text = str1pos1 + " " + str2pos1 + "\n" + str1pos2 + " " + str2pos2 + "\n" + str1pos3 + " " + str2pos3

    }
}