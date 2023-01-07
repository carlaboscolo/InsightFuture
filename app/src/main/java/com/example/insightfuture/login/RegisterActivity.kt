package com.example.insightfuture.login

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.insightfuture.MainActivity
import com.example.insightfuture.databinding.ActivityRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    lateinit var editTextEmail : TextInputEditText
    lateinit var editTextPassword : TextInputEditText
    lateinit var editTextPasswordCheck : TextInputEditText
    lateinit var register_btn : Button
    lateinit var progressBar : ProgressBar
    lateinit var loginNow : TextView

    private lateinit var auth: FirebaseAuth

   /* lancia activity dopo registrazione
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            launchActivity()
        }
    }
    */



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        editTextEmail = binding.email
        editTextPassword = binding.password
        editTextPasswordCheck = binding.passwordCheck
        register_btn = binding.btnRegister
        progressBar = binding.progressBar
        loginNow = binding.loginNow

        loginNow.setOnClickListener{
            launchLogin()
        }

        register_btn.setOnClickListener {
            var email = editTextEmail.text.toString()
            var password = editTextPassword.text.toString()
            var password2 = editTextPasswordCheck.text.toString()

            progressBar.visibility = View.VISIBLE

            if(email.isEmpty() || password.isEmpty() || password2.isEmpty()){
                val builder = AlertDialog.Builder(this)

                builder.setMessage("Non lasciare campi vuoti")
                builder.setNeutralButton("Ok") { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        "Ok", Toast.LENGTH_SHORT
                    ).show()
                }
                builder.show()
            }else{


                if(password != password2){
                    Toast.makeText(
                        baseContext, "Le password sono diverse",
                        Toast.LENGTH_SHORT
                    ).show()
                }else if (password.length < 6){
                    Toast.makeText(
                        baseContext, "La password deve contenere più di 6 caratteri",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->

                            progressBar.visibility = View.GONE

                            if (task.isSuccessful) {
                                Log.d(TAG, "Account creato")
                                Toast.makeText(
                                    baseContext, "Account creato",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Log.w(TAG, "Creazione account fallita", task.exception)
                                Toast.makeText(
                                    baseContext, "Creazione account fallita",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                }



            }

        }

    }

    private fun launchLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}