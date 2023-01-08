package com.example.insightfuture.login


import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.developer.gbuttons.GoogleSignInButton
import com.example.insightfuture.MainActivity
import com.example.insightfuture.Modal.users
import com.example.insightfuture.R
import com.example.insightfuture.databinding.ActivityLoginBinding
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var editTextEmail: TextInputEditText
    lateinit var editTextPassword: TextInputEditText
    lateinit var login_btn: Button
    lateinit var progressBar: ProgressBar
    lateinit var registerNow: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase

    private lateinit var googleBtn: GoogleSignInButton
    private lateinit var gOptions: GoogleSignInOptions
    private lateinit var gClient: GoogleSignInClient

    private lateinit var loginButton: LoginButton
    var callbackManager: CallbackManager? = null

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            launchActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance("https://insightfuture-login-default-rtdb.firebaseio.com/")
        FacebookSdk.sdkInitialize(getApplicationContext())

        editTextEmail = binding.email
        editTextPassword = binding.password
        login_btn = binding.btnLogin
        progressBar = binding.progressBar
        registerNow = binding.registerNow
        googleBtn = binding.googleBtn

        registerNow.setOnClickListener {
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
        gOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        gClient = GoogleSignIn.getClient(this, gOptions)

        googleBtn.setOnClickListener {
            signInGoogle()
        }

        //integrazione Facebook
        callbackManager = CallbackManager.Factory.create()

        loginButton = binding.fbBtn
        loginButton.setReadPermissions("email")
        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Toast.makeText(this@LoginActivity, "Facebook Authentication success.", Toast.LENGTH_SHORT).show()
                    handleFacebookAccessToken(loginResult.accessToken)
                }

                override fun onCancel() {
                    Toast.makeText(this@LoginActivity, "Facebook Authentication canceled.", Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException?) {
                    Toast.makeText(this@LoginActivity, "Facebook Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            })

}


    private fun launchRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun launchActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    //integrazione google
    private fun signInGoogle() {
        val signInIntent = gClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result

            if (account != null) {
                updateUI(account)
            }

        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
               // intent.putExtra("email", account.email)
               // intent.putExtra("name", account.displayName)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //integrazione con Facebook
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d("token", "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth!!.signInWithCredential(credential).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Autentificazione riuscita",Toast.LENGTH_SHORT).show()

                  /*  val user = Firebase.auth.currentUser
                    val users1 : users = users()
                    if (user != null) {
                        users1.setUserName(user.displayName!!)
                        database.reference.child("users").child(user.uid).setValue(users1)
                    } */

                    launchActivity()

                       //val intent = Intent(this, MainActivity::class.java)
                        // intent.putExtra("name", user.displayName.toString())
                        //startActivity(intent)
                       // finish()
                      } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

}