package com.example.insightfuture.login


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.insightfuture.MainActivity
import com.example.insightfuture.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var editTextEmail : TextInputEditText
    lateinit var editTextPassword : TextInputEditText
    lateinit var login_btn : Button
    lateinit var progressBar : ProgressBar
    lateinit var registerNow : TextView

    private lateinit var auth: FirebaseAuth

    /*
      private lateinit var googleBtn : GoogleSignInButton
      private lateinit var gOptions : GoogleSignInOptions
      private lateinit var gClient : GoogleSignInClient
  */

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            launchActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        editTextEmail = binding.email
        editTextPassword = binding.password
        login_btn = binding.btnLogin
        progressBar = binding.progressBar
        registerNow = binding.registerNow
        //googleBtn = binding.googleBtn

        registerNow.setOnClickListener{
            launchRegister()
        }

        login_btn.setOnClickListener {
            var email = editTextEmail.text.toString()
            var password = editTextPassword.text.toString()

            progressBar.visibility = View.VISIBLE

            if (email.isEmpty() || password.isEmpty()) {
                val builder = AlertDialog.Builder(this)

                builder.setMessage("Non lasciare campi vuoti")
                builder.setNeutralButton("Ok") { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        "Ok", Toast.LENGTH_SHORT
                    ).show()
                }
                builder.show()
            } else {


                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        progressBar.visibility = View.GONE

                        if (task.isSuccessful) {
                            Log.d(TAG, "Accesso eseguito con successo")
                            Toast.makeText(
                                baseContext, "Accesso eseguito con successo",
                                Toast.LENGTH_SHORT
                            ).show()
                            launchActivity()
                        } else {
                            Log.w(TAG, "Autentificazione fallita", task.exception)
                            Toast.makeText(
                                baseContext, "Autentificazione fallita",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

        //integrazione google

    /*    gOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        gClient = GoogleSignIn.getClient(this, gOptions)

        googleBtn.setOnClickListener {
            signInGoogle()
        }
        */


    }

    private fun launchRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        //finish()
    }

    private fun launchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
       // finish()
    }

/*
    private fun signInGoogle(){
        val signInIntent = gClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
    }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result

            if(account != null){
                updateUI(account)
            }

        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential  = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener{
            if(it.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    } */

}