package com.example.insightfuture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.insightfuture.databinding.ActivityPackageBinding

class PackageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPackageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_package)
        binding = ActivityPackageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar
        val display = supportActionBar
        display?.title = "Pacchetti"
        display?.setDisplayHomeAsUpEnabled(true)
    }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}