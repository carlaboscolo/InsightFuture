package com.example.insightfuture.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insightfuture.ArchiveAdapter
import com.example.insightfuture.R
import com.example.insightfuture.databinding.ActivityArchiveBinding
import com.example.roomdatabase.AppDatabase
import kotlinx.coroutines.launch

class ArchiveActivity : AppCompatActivity() {

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

    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            val archiveList = AppDatabase(this@ArchiveActivity).sibillaDao().getAll()

            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@ArchiveActivity)
                adapter = ArchiveAdapter().apply {
                    setData(archiveList)
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.menu_item, menu)

        return true
    }

}