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
import org.json.JSONArray
import org.json.JSONObject
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var question : EditText
    private lateinit var name : EditText
    private lateinit var surname : EditText
    private lateinit var bornPlace : EditText
    private lateinit var searchBtn : Button
    private var pos1 by Delegates.notNull<Int>()
    private var pos2 by Delegates.notNull<Int>()
    private var pos3 by Delegates.notNull<Int>()
    private var pos_tripla by Delegates.notNull<String>()
    private var stringa1 by Delegates.notNull<String>()
    private var stringa2 by Delegates.notNull<String>()

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
            val nameFirstLetter = getFirstLetter(name.text.toString())
            val surnameFirstLetter = getFirstLetter(surname.text.toString())
            val bornPlaceFirstLetter = getFirstLetter(bornPlace.text.toString())

            var first: Int? = null
            var last = 0
            var sum  = 0

            for(i in arrFirstLetter.indices){
                if(first == null){
                    first = getNumFromArr(arrFirstLetter[i])
                    Log.d("first", "first" + first)
                }

                last = getNumFromArr(arrFirstLetter[i])
                Log.d("first", "last" + last)

                sum = sum + getNumFromArr(arrFirstLetter[i])

            }

            sum = getRightNumber(sum)
            Log.d("first", "somma : " + sum )


           var sum2 = getNumFromArr(nameFirstLetter[0]) + getNumFromArr(surnameFirstLetter[0]) + getNumFromArr(bornPlaceFirstLetter[0]) + last
            sum2 = getRightNumber(sum2)
            Log.d("first", "somma 2 : " + sum2 )

           var sum3 = getNumFromArr(nameFirstLetter[0]) + getNumFromArr(surnameFirstLetter[0]) + first!! + last
            sum3 = getRightNumber(sum3)
            Log.d("first", "somma 3 : " + sum3 )

            var triplette1 = sum.toString() + sum2.toString() + sum3.toString()
            var triplette2 = sum2.toString() + sum3.toString() +  sum.toString()
            var triplette3 = sum3.toString() + sum.toString() + sum2.toString()
            Log.d("triple" , triplette1 + " " + triplette2 + " " + triplette3)

            //getFirstLetter(question.text.toString())


            //prende file da sibililia.json in cartella res/raw scritto in minuscolo
            val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "sibillia",
                "raw",
                applicationContext.packageName
            )
            ).bufferedReader().use { it.readText() }


            val outputJsonString = JSONArray(jsonData)
            //Log.d("sib", outputJsonString.toString()) //stampa tutti i dati



                //cicla array sibillia
                for (i in 0 until outputJsonString.length()) {
                     pos1 = outputJsonString.getJSONObject(i).getString("pos1").toInt()
                     pos2 = outputJsonString.getJSONObject(i).getString("pos2").toInt()
                     pos3 = outputJsonString.getJSONObject(i).getString("pos3").toInt()
                     pos_tripla = outputJsonString.getJSONObject(i).getString("pos_tripla").toString()
                     stringa1 = outputJsonString.getJSONObject(i).getString("stringa1").toString()
                     stringa2 = outputJsonString.getJSONObject(i).getString("stringa2").toString()


                    Log.d("sibComp", " Dati: 1: $sum, 2: $sum2, 3: $sum3" )
                    if(pos1 == sum && pos2 == sum2 && pos3 == sum3 && pos_tripla == "1"){
                        Log.d("sibComp", "Stringa 1: $stringa1 ") //prova comparazione pos1
                        Log.d("sibComp", "Stringa 2: $stringa2 ") //prova comparazione pos1
                    }
                    Log.d("sib", "pos1 $pos1") //prova stampa pos1 funzionante
                }
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




