package com.example.insightfuture.Modal

class users {
    private var userName = " "

    @JvmName("getUserName1")
    fun getUserName(): String {
        return this.userName
    }

    fun setUserName(userName : String){
        this.userName = userName
    }

    fun users(userName: String){
        this.userName = userName
    }

}