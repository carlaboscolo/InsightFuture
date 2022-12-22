package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.insightfuture.databinding.ActivitySibillaBinding
import com.example.insightfuture.model.User
import com.example.roomdatabase.AppDatabase


class SibillaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySibillaBinding

    private lateinit var sibillaResponse : TextView

    private lateinit var appDB : AppDatabase
    private lateinit var writeDataBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_sibilla)
        binding = ActivitySibillaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sibillaResponse = binding.sibillaResponse

        var str1pos1 = intent.getStringExtra("str1pos1")
        var str2pos1 =  intent.getStringExtra("str2pos1")
        var str1pos2 = intent.getStringExtra("str1pos2")
        var str2pos2 =  intent.getStringExtra("str2pos2")
        var str1pos3 = intent.getStringExtra("str1pos3")
        var str2pos3 =  intent.getStringExtra("str2pos3")
        val receivedObject:User=intent?.getSerializableExtra("Obj") as User
        Log.d("userResponse", receivedObject.toString())

        val sibResponse  =  str1pos1 + " " + str1pos2 + " " +  str1pos3 +  "\n" +  str2pos1 + " " + str2pos2 + " " + str2pos3
        sibillaResponse.text = sibResponse

        receivedObject.response = sibResponse
        Log.d("userResponse", receivedObject.toString())

    }
}