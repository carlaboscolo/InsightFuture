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
    private val keyNumbers = arrayOf<Int>(0 , 1 , 2 ,3 ,4 ,5 , 6 ,7 ,8 ,9 )

  //  private val table: HashMap<Int,String> = hashMapOf(0 to "kqwyx", 1 to "hvu", 2 to "ers") // table for letters and numbers


    // private val keyLetters = arrayOf<String>("k", "q", "w", "y", "x", "h", "v", "u", "e", "r", "s", "m", "t", "l", "o", "a", "g", "i", "j", "n", "c", "f", "d", "z", "p", "b")


    private val KeyLetters = mapOf(
        "k" to 0,
        "q" to 0,
        "w" to 0,
        "y" to 0,
        "x" to 0,
        "h" to 1,
        "v" to 1,
        "u" to 1,
        "e" to 2,
        "r" to 2,
        "s" to 2,
        "m" to 3,
        "t" to 3,
        "i" to 4,
        "o" to 4,
        "a" to 5,
        "g" to 5,
        "l" to 6,
        "j" to 6,
        "n" to 6,
        "c" to 7,
        "f" to 7,
        "d" to 8,
        "z" to 8,
        "p" to 9,
        "b" to 9,
    )



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

        //Log.d("value", KeyLetters["k"].toString())

       // KeyLetters.forEach { (key, value) -> Log.d("value","$key = $value" ) }

        searchBtn.setOnClickListener{

          val arrFirstLetter =  getFirstLetter(question.text.toString())
            for(i in arrFirstLetter.indices){
                var sum  = 0
                sum = sum + getNumFromArr(arrFirstLetter[i])
              //  Log.d("first", i.toString())
            }

        }



       /* for(key in table.keys){
            Log.d("Table", table[key].toString())
        } */

        //Log.d("Table", table["kqwyx"].toString())
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


        var arrFirstLetter = arrayOf<String>()

        for (i in arr.indices) {
           // val strArr = i.toString()
            arrFirstLetter += arrayOf(arr[i].toString().substring(0, 1))
        }



            // if(arrFirstLetter === )

           // Log.d("first", keyLetters.copyOfRange(0, 5).contentToString())

            /*   if (arrFirstLetter == keyLetters.copyOfRange(0, 5).contentToString()) {
                     Log.d("first", "entrato qui")
                 }
             */
            return arrFirstLetter
        }


        private fun getNumFromArr(firstLetter :  String)  :  Int {

            KeyLetters.forEach { (key, value) ->
                if(firstLetter == key){
                    Log.d("first", "sono entrato qui $key")
                }

               }




            return 0
        }





}

