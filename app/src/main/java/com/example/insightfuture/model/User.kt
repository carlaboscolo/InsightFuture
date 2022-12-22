package com.example.insightfuture.model

import java.io.Serializable

//, val data : String
data class User(val question: String, val name: String, val surname: String, val bornPlace: String, var response: String?)  :
    Serializable {
}




/*

   val today = calendar()

                    val dateFormat = SimpleDateFormat("dd-MM-yyyy")

                    val firstDate: Date = dateFormat.parse(data)
                    val secondDate: Date = dateFormat.parse(today)

                    Log.d("check", "input " + firstDate.toString())
                    Log.d("check", "oggi " + secondDate.toString())
 */