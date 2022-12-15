package com.example.insightfuture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.insightfuture.databinding.ActivityMainBinding
import java.io.IOException
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var question : EditText
    private lateinit var name : EditText
    private lateinit var surname : EditText
    private lateinit var bornPlace : EditText
    private lateinit var searchBtn : Button

  //  private val keyLetters = arrayOf<String>("kqwyx", "hvu", "ers", "mt", "lo", "ag", "ijn", "cf", "dz", "pb")
    private val keyNumbers = arrayOf<Int>(0 , 1 , 2 ,3 ,4 ,5 , 6 ,7 ,8 ,9 )

  //  private val table: HashMap<Int,String> = hashMapOf(0 to "kqwyx", 1 to "hvu", 2 to "ers") // table for letters and numbers


    private val keyLetters = arrayOf<String>("k", "q", "w", "y", "x", "h", "v", "u", "e", "r", "s", "m", "t", "l", "o", "a", "g", "i", "j", "n", "c", "f", "d", "z", "p", "b")


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
            //getFirstLetter(question.text.toString())
            getSibillaCode(applicationContext, "../assets/Sibillia.json")?.let { it1 ->
                Log.d("sibillia",
                    it1
                )
            }

        }



    }


    fun getSibillaCode(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }


    data class Sibillia(var pos1 : String, var pos2 : String, var pos3 : String, var pos_tripla : String, var stringa1 : String, var stringa2 : String){

    }




    private fun getFirstLetter(stringa : String): Array<String> {
        val delim = " "
        val arr = stringa.split(delim).toTypedArray()

        // Log.d("array", arr[1])

        for (array in arr) {
            Log.d("array", array)
        }

        //first letter
        //val str = arr[1].toString()
        // val firstLetter = str.substring(0, 1)
        //Log.d("array", firstLetter)

        val arrayMath = arrayOf<Int>()
        //var arrFirstLetter = arrayOf<String>()

        for (array in arr) {
            val strArr = array.toString()
            val arrFirstLetter = strArr.substring(0, 1)
            Log.d("first", arrFirstLetter)


            // if(arrFirstLetter === )

            Log.d("first", keyLetters.copyOfRange(0, 5).contentToString())

            /*   if (arrFirstLetter == keyLetters.copyOfRange(0, 5).contentToString()) {
                     Log.d("first", "entrato qui")
                 }
             */


        }







            return arr
        }



}