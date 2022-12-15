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

       // KeyLetters.forEach { (key, value) -> Log.d("value","$key = $value" ) }

        searchBtn.setOnClickListener{

          val arrFirstLetter =  getFirstLetter(question.text.toString())
            var sum  = 0
            for(i in arrFirstLetter.indices){
                sum = sum + getNumFromArr(arrFirstLetter[i])
            }

            Log.d("first", "somma : " + sum )
            getRightNumber(sum)
        }



    }


    private fun getFirstLetter(stringa : String): Array<String> {
        val delim = " "
        val arr = stringa.split(delim).toTypedArray()
        

        for (array in arr) {
            Log.d("array", array)
        }

        var arrFirstLetter = arrayOf<String>()

        for (i in arr.indices) {
            arrFirstLetter += arrayOf(arr[i].toString().substring(0, 1))
        }

            return arrFirstLetter
        }


        private fun getNumFromArr(firstLetter :  String)  :  Int {

            KeyLetters.forEach { (key, value) ->
                if(firstLetter == key){
                    Log.d("first", "sono entrato qui $key")
                    return value
                }

               }

            return 0
        }



        private fun getRightNumber(sum : Int) : Int {
          var somma = " "

           if(sum > 99) {
             somma =  sum.toString().substring(2,3)
           } else if(sum > 10){
             somma =   sum.toString().substring(1,2)
           }else{
               somma = sum.toString()
           }

            Log.d("Sum", somma.toString())
            return somma.toInt()
        }



}

