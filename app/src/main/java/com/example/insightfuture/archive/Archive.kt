package com.example.insightfuture.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insightfuture.ArchiveAdapter
import com.example.insightfuture.archive.model.Response
import com.example.insightfuture.databinding.ActivityArchiveBinding
import com.example.insightfuture.model.User

class Archive : AppCompatActivity() {

    private lateinit var binding : ActivityArchiveBinding
    private lateinit var backBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_archive)
        binding = ActivityArchiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtn = binding.backBtn

        backBtn.setOnClickListener{
            finish()
        }

        val archives = ArrayList<Response>()

        val recyclerView = binding.recyclerView
        val archiveAdapter = ArchiveAdapter(archives, this@Archive)
        recyclerView.apply {
            adapter = archiveAdapter
            layoutManager = LinearLayoutManager(this@Archive, LinearLayoutManager.VERTICAL, false)
        }

       /* archiveAdapter.setOnCallback(object : ArchiveAdapter.AdapterCallback{
            override fun selectItem(position: Int) {
                Log.d("LogAdapter", "Product: $position")
                val intent = Intent(this@Archive, DetailActivity::class.java)
                startActivity(intent)
            }
        }) */

    }

}