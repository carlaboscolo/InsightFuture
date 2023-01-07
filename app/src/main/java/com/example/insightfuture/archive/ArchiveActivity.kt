package com.example.insightfuture.archive

import android.app.ActionBar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insightfuture.ArchiveAdapter
import com.example.insightfuture.R
import com.example.insightfuture.databinding.ActivityArchiveBinding
import com.example.insightfuture.roomDatabase.SibillaDatabase
import com.example.roomdatabase.AppDatabase
import kotlinx.coroutines.launch
import java.util.*

class ArchiveActivity : AppCompatActivity() {

    private lateinit var appDB: AppDatabase
    private lateinit var binding: ActivityArchiveBinding
    private lateinit var backBtn: Button

    private lateinit var arcAdapter: ArchiveAdapter
    private lateinit var archiveList: ArrayList<SibillaDatabase>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_archive)
        binding = ActivityArchiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        archiveList = ArrayList()
        arcAdapter = ArchiveAdapter(archiveList)

        backBtn = binding.backBtn
        appDB = AppDatabase.invoke(this)

        backBtn.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            archiveList = AppDatabase(this@ArchiveActivity).sibillaDao()
                .getAll() as ArrayList<SibillaDatabase>

            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@ArchiveActivity)
                adapter = ArchiveAdapter(archiveList)
            }
        }

        arcAdapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.getActionView() as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }
        })

        return true
    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<SibillaDatabase> = ArrayList()

        /*  ----------- non funziona -------------------------
           lateinit var sibillaDb : SibillaDatabase

          GlobalScope.launch {
                sibillaDb = appDB.sibillaDao().findByQuery(text)
             //   Log.d("searchquery", "sono entrato qui")
              //Log.d("sibillaTextQuery", searchQuery + " "  + sibilla.question)
              //  displayData(sibilla)
          }
          --------------------------------------------------- */

        for (item in archiveList) {
            if (item.name?.toLowerCase(Locale.ROOT)?.contains(text.toLowerCase()) == true) {
                Toast.makeText(this, "Data Found..", Toast.LENGTH_SHORT).show()
                filteredlist.add(item)
            }
        }

        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            arcAdapter.filterList(filteredlist)
        }
    }
}


// ------------------------  NON FUNZIONA -----------------------------
/* private suspend fun displayData(student: Student){
 ithContext(Dispatchers.Main){
       binding.tvFirstName.text = student.firstName
       binding.tvLastName.text = student.lastName
       binding.tvRollNo.text = student.rollNo.toString()
   }

} */


/*  private fun readData(searchQuery: String) {

      //val searchQuery = binding.searchBar.text.toString().toLowerCase()

      if (searchQuery.isNotEmpty()) {
          lateinit var sibilla: SibillaDatabase

          GlobalScope.launch {
              sibilla = appDB.sibillaDao().findByQuery(searchQuery)
              Log.d("sibillaTextQuery", searchQuery + " "  + sibilla.question)
              //  displayData(sibilla)
          }

          // binding.searchBar.text.clear()

      }

} */

