package com.example.insightfuture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.insightfuture.archive.ArchiveActivity
import com.example.insightfuture.databinding.ActivitySibillaBinding
import com.example.insightfuture.model.User
import com.example.insightfuture.roomDatabase.SibillaDatabase
import com.example.roomdatabase.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class SibillaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySibillaBinding

    private lateinit var sibillaResponse: TextView

    private lateinit var appDB: AppDatabase
    private lateinit var writeDataBtn: Button
    private lateinit var archiveBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_sibilla)
        binding = ActivitySibillaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = AppDatabase.invoke(this)
        writeDataBtn = binding.saveBtn
        sibillaResponse = binding.sibillaResponse
        archiveBtn = binding.archiBtn

        var str1pos1 = intent.getStringExtra("str1pos1")
        var str2pos1 = intent.getStringExtra("str2pos1")
        var str1pos2 = intent.getStringExtra("str1pos2")
        var str2pos2 = intent.getStringExtra("str2pos2")
        var str1pos3 = intent.getStringExtra("str1pos3")
        var str2pos3 = intent.getStringExtra("str2pos3")
        val receivedObject: User = intent?.getSerializableExtra("Obj") as User
        Log.d("userResponse", receivedObject.toString())

        val sibResponse =
            str1pos1 + " " + str1pos2 + " " + str1pos3 + "\n" + str2pos1 + " " + str2pos2 + " " + str2pos3
        sibillaResponse.text = sibResponse

        receivedObject.response = sibResponse
        Log.d("userResponse", receivedObject.toString())

        writeDataBtn.setOnClickListener {
            writeData(receivedObject)
        }

        archiveBtn.setOnClickListener {
            launchArchive()
        }

    }


    //funzione che scrive i dati nel database locale
    private fun writeData(receivedObject: User) {

        val today = calendar()
        Log.d("saveData", "oggi " + today.toString())

        val userData = SibillaDatabase(
            null,
            today,
            receivedObject.question,
            receivedObject.name,
            receivedObject.surname,
            receivedObject.bornPlace,
            receivedObject.response
        )

        GlobalScope.launch(Dispatchers.IO) {
            appDB.sibillaDao().insert(userData)
        }

        Toast.makeText(this@SibillaActivity, "Successfully written", Toast.LENGTH_SHORT).show()
    }

    //selezionare la data di oggi
    fun calendar(): String {
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val day = calendar[Calendar.DAY_OF_MONTH]
        val month = calendar[Calendar.MONTH] + 1

        val dayString = if (day < 10) "0$day" else "$day"
        val monthString = if (month < 10) "0$month" else "$month"

        val data_string = "$dayString-$monthString-$year"

        Log.d("data", data_string)
        return data_string
    }

    private fun launchArchive() {
        val intent = Intent(this, ArchiveActivity::class.java)
        startActivity(intent)
    }

}