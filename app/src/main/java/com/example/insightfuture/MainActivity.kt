package com.example.insightfuture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.insightfuture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var question : EditText
    private lateinit var name : EditText
    private lateinit var surname : EditText
    private lateinit var bornPlace : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        question = binding.question
        name = binding.name
        surname = binding.surname
        bornPlace = binding.bornPlace



    }
}