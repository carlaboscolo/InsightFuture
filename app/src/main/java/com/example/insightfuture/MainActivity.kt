package com.example.insightfuture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.insightfuture.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var question : EditText
    private lateinit var name : EditText
    private lateinit var surname : EditText
    private lateinit var bornPlace : EditText
    private lateinit var searchBtn : Button

  //  private val keyLetters = arrayOf<String>("kqwyx", "hvu", "ers", "mt", "lo", "ag", "ijn", "cf", "dz", "pb")
  //  private val keyNumbers = arrayOf<Int>(0 , 1 , 2 ,3 ,4 ,5 , 6 ,7 ,8 ,9 )

    private val table: HashMap<String,Int> = hashMapOf("kqwyx" to 0, "hvu" to 1, "ers" to 2) // table for letters and numbers




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        question = binding.question
        name = binding.name
        surname = binding.surname
        bornPlace = binding.bornPlace
        searchBtn = binding.searchBtn

        searchBtn.setOnClickListener{
            getFirstLetter(question.text.toString())
        }

        //Log.d("Table", table["kqwyx"].toString())
    }


    private fun getFirstLetter(stringa : String): Array<String> {
        val delim = " "
        val arr = stringa.split(delim).toTypedArray()
       // Log.d("array", arr[2])
        return arr
    }



}