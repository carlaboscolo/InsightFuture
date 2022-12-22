package com.example.insightfuture.model

import java.io.Serializable

//, val data : String
data class User(val question: String, val name: String, val surname: String, val bornPlace: String, var response: String?, var data : String?)  :
    Serializable {
}



