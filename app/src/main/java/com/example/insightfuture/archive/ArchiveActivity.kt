package com.example.insightfuture.archive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.insightfuture.ArchiveAdapter
import com.example.insightfuture.databinding.ActivityArchiveBinding
import com.example.roomdatabase.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArchiveActivity : AppCompatActivity() {

    private lateinit var binding : ActivityArchiveBinding
    private lateinit var backBtn : Button
    private lateinit var searchBtn : Button
    private lateinit var searchText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_archive)
        binding = ActivityArchiveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backBtn = binding.backBtn
        searchBtn = binding.backBtn
        searchText = binding.searchBar

        backBtn.setOnClickListener{
            finish()
        }

        searchBtn.setOnClickListener {

            val searchQuery = searchText.text.toString().toLowerCase()

            if(searchQuery.isNotEmpty()){
               readData()
            }

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



/* private suspend fun displayData(student: Student){
  /*
 ithContext(Dispatchers.Main){
       binding.tvFirstName.text = student.firstName
       binding.tvLastName.text = student.lastName
       binding.tvRollNo.text = student.rollNo.toString()
   }

} */

 */


private fun readData(){
  /* val rollNo = binding.etRollNoRead.text.toString()

   if(rollNo.isNotEmpty()){
       lateinit var student: Student

       GlobalScope.launch {
           student = appDB.studentDao().findByRoll(rollNo.toInt())
           displayData(student)
       }

   }
   */
}

}