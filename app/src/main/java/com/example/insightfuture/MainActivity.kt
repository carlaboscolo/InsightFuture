package com.example.insightfuture
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.insightfuture.databinding.ActivityMainBinding
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.insightfuture.login.LoginActivity
import com.example.insightfuture.model.User
import com.facebook.FacebookSdk
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.w3c.dom.Text
import java.io.Serializable
import java.text.Normalizer
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics

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

    private var str1pos1 by Delegates.notNull<String>()
    private var str2pos1 by Delegates.notNull<String>()
    private var str1pos2 by Delegates.notNull<String>()
    private var str2pos2 by Delegates.notNull<String>()
    private var str1pos3 by Delegates.notNull<String>()
    private var str2pos3 by Delegates.notNull<String>()

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
        "l" to 4,
        "o" to 4,
        "a" to 5,
        "g" to 5,
        "i" to 6,
        "j" to 6,
        "n" to 6,
        "c" to 7,
        "f" to 7,
        "d" to 8,
        "z" to 8,
        "p" to 9,
        "b" to 9,
    )

    //senza login
    private lateinit var questionText : String
    private lateinit var questionUser : String
    private var isLogged : Boolean = false


    private lateinit var parentText : TextView
    private lateinit var childText : TextView
    private lateinit var parentSp : Spinner
    private lateinit var childSp : Spinner
    private lateinit var infoLogin : TextView

    private lateinit var arraylistParent : ArrayList<String>
    private lateinit var arrayAdapter : ArrayAdapter<String>

    private lateinit var arrayAmore : ArrayList<String>
    private lateinit var arrayLavoro : ArrayList<String>
    private lateinit var arrayFamiglia : ArrayList<String>
    private lateinit var arrayAdapterChild : ArrayAdapter<String>

    private lateinit var loginPageBtn : Button

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

        //senza login
        parentText = binding.tvParent
        childText = binding.tvChild
        parentSp = binding.spParent
        childSp = binding.spChild
        infoLogin = binding.info

        //pulsante login
        loginPageBtn = binding.loginPageBtn

        //toolbar
        val display = supportActionBar
        display?.title = "Interroga la Sibilla"
        display?.setDisplayHomeAsUpEnabled(true)

        //firebase
        auth = FirebaseAuth.getInstance()
        val user = Firebase.auth.currentUser
        analytics = Firebase.analytics

        FacebookSdk.sdkInitialize(getApplicationContext());

       if(user == null){
            Log.d("checkLog", "Non sei loggato")

           //visibility
           question.visibility = View.GONE
           parentText.visibility = View.VISIBLE
           childText.visibility = View.VISIBLE
           parentSp.visibility = View.VISIBLE
           childSp.visibility = View.VISIBLE
           infoLogin.visibility = View.VISIBLE
           loginPageBtn.visibility = View.VISIBLE

           //array categorie
           arraylistParent = ArrayList<String>()
           arraylistParent.add("Amore")
           arraylistParent.add("Lavoro")
           arraylistParent.add("Famiglia")

           arrayAdapter = ArrayAdapter<String>(applicationContext, android.R.layout.select_dialog_item, arraylistParent)

           parentSp.setAdapter(arrayAdapter)

           //child
           arrayAmore = ArrayList<String>()
           arrayAmore.add("Sarò fortunato/a in amore quest’anno ?")
           arrayAmore.add("Come sarà la mia prossima relazione ?")

           arrayLavoro = ArrayList<String>()
           arrayLavoro.add("Come posso affrontare le opportunità lavorative che mi attendono ?")
           arrayLavoro.add("Come posso raggiungere il lavoro dei miei sogni ?")

           arrayFamiglia = ArrayList<String>()
           arrayFamiglia.add("Come posso andare d’accordo con i miei genitori ?")
           arrayFamiglia.add("Cosa devo fare per avvicinarmi di più a mio figlio ?")

           //process
           arrayAdapterChild = ArrayAdapter(applicationContext,  android.R.layout.select_dialog_item, arrayAmore)
           childSp.setAdapter(arrayAdapterChild)

           parentSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
               override fun onNothingSelected(parent: AdapterView<*>?) {

               }
               override fun onItemSelected(parent : AdapterView<*>?, view : View?, position: Int, id : Long){
                   if(position == 0){
                       arrayAdapterChild = ArrayAdapter(applicationContext,  android.R.layout.select_dialog_item, arrayAmore)
                       childSp.setAdapter(arrayAdapterChild)
                   }else if(position == 1){
                       arrayAdapterChild = ArrayAdapter(applicationContext,  android.R.layout.select_dialog_item, arrayLavoro)
                       childSp.setAdapter(arrayAdapterChild)
                   } else if(position == 2){
                       arrayAdapterChild = ArrayAdapter(applicationContext,  android.R.layout.select_dialog_item, arrayFamiglia)
                       childSp.setAdapter(arrayAdapterChild)
                   }

               }
           }

           childSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
               override fun onNothingSelected(parent: AdapterView<*>?) {

               }
               override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                   questionText = parent?.getItemAtPosition(position).toString()
                   Log.d("scelta", questionText)
               }
           }

           //pulsante per il login
           loginPageBtn.setOnClickListener {
               launchLogin()
           }

           isLogged = false

           //fine senza login

       }else{
           Log.d("checkLog", "Sei loggato")

           //visibility
           question.visibility = View.VISIBLE
           parentText.visibility = View.GONE
           childText.visibility = View.GONE
           parentSp.visibility = View.GONE
           childSp.visibility = View.GONE
           infoLogin.visibility = View.GONE
           loginPageBtn.visibility = View.GONE

           isLogged = true
        }


        // KeyLetters.forEach { (key, value) -> Log.d("value","$key = $value" ) }

        searchBtn.setOnClickListener {

            if(!isLogged){
                Log.d("checkLog", isLogged.toString())

                //domanda senza login
                questionUser = questionText.toString().toLowerCase().replace("[-\\[\\][z0-9]^/.,'*:!><~@#\$%+=?|\"\\\\()]+".toRegex(), "").Normalize()
            }else{
                Log.d("checkLog", isLogged.toString())

                //domanda base scritta da utente
                questionUser = question.text.toString().toLowerCase().replace("[-\\[\\][z0-9]^/.,'*:!><~@#\$%+=?|\"\\\\()]+".toRegex(), "").Normalize()
            }

           val nameUser = name.text.toString().toLowerCase()
                .replace("[-\\[\\][z0-9]^/.,'*:!><~@#\$%+=?|\"\\\\()]+".toRegex(), "").Normalize()
            val surnameUser = surname.text.toString().toLowerCase()
                .replace("[-\\[\\][z0-9]^/.,'*:!><~@#\$%+=?|\"\\\\()]+".toRegex(), "").Normalize()
            val bornPlaceUser = bornPlace.text.toString().toLowerCase()
                .replace("[-\\[\\][z0-9]^/.,'*:!><~@#\$%+=?|\"\\\\()]+".toRegex(), "").Normalize()

            if (questionUser.isEmpty() || nameUser.isEmpty() || surnameUser.isEmpty() || bornPlaceUser.isEmpty()) {
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

                val user = User(questionUser, nameUser, surnameUser, bornPlaceUser, null)
                Log.d("questionUser", user.toString())

                val arrFirstLetter = getFirstLetter(questionUser)
                val nameFirstLetter = getFirstLetter(nameUser)
                val surnameFirstLetter = getFirstLetter(surnameUser)
                val bornPlaceFirstLetter = getFirstLetter(bornPlaceUser)

                var first: Int? = null
                var last = 0
                var sum = 0

                for (i in arrFirstLetter.indices) {
                    if (first == null) {
                        first = getNumFromArr(arrFirstLetter[i])
                        Log.d("first", "first " + first)
                    }

                    last = getNumFromArr(arrFirstLetter[i])
                    Log.d("first", "last " + last)

                    sum = sum + getNumFromArr(arrFirstLetter[i])

                }

                sum = getRightNumber(sum)
                Log.d("first", "somma : " + sum)


                var sum2 =
                    getNumFromArr(nameFirstLetter[0]) + getNumFromArr(surnameFirstLetter[0]) + getNumFromArr(
                        bornPlaceFirstLetter[0]
                    ) + last
                sum2 = getRightNumber(sum2)
                Log.d("first", "somma 2 : " + sum2)

                var sum3 =
                    getNumFromArr(nameFirstLetter[0]) + getNumFromArr(surnameFirstLetter[0]) + first!! + last
                sum3 = getRightNumber(sum3)
                Log.d("first", "somma 3 : " + sum3)

                var triplette1 = sum.toString() + sum2.toString() + sum3.toString()
                var triplette2 = sum2.toString() + sum3.toString() + sum.toString()
                var triplette3 = sum3.toString() + sum.toString() + sum2.toString()
                Log.d("triple", triplette1 + " " + triplette2 + " " + triplette3)

                //getFirstLetter(question.text.toString())


                //prende file da sibilila.json in cartella res/raw scritto in minuscolo
                val jsonData = applicationContext.resources.openRawResource(
                    applicationContext.resources.getIdentifier(
                        "sibilla",
                        "raw",
                        applicationContext.packageName
                    )
                ).bufferedReader().use { it.readText() }


                val outputJsonString = JSONArray(jsonData)
                //Log.d("sib", outputJsonString.toString()) //stampa tutti i dati


                //cicla array sibilla
                for (i in 0 until outputJsonString.length()) {
                    pos1 = outputJsonString.getJSONObject(i).getString("pos1").toInt()
                    pos2 = outputJsonString.getJSONObject(i).getString("pos2").toInt()
                    pos3 = outputJsonString.getJSONObject(i).getString("pos3").toInt()
                    pos_tripla =
                        outputJsonString.getJSONObject(i).getString("pos_tripla").toString()
                    stringa1 = outputJsonString.getJSONObject(i).getString("stringa1").toString()
                    stringa2 = outputJsonString.getJSONObject(i).getString("stringa2").toString()


                    Log.d("somme", "$sum ,$sum2,$sum3")
                    if (pos1 == sum && pos2 == sum2 && pos3 == sum3 && pos_tripla == "1") {
                        Log.d("sibComp", "1 tripletta")
                        Log.d("sibComp", "Stringa 1: $stringa1 ") //prova comparazione pos1
                        Log.d("sibComp", "Stringa 2: $stringa2 ") //prova comparazione pos1

                        str1pos1 = stringa1
                        str2pos1 = stringa2
                    }
                    if (pos1 == sum2 && pos2 == sum3 && pos3 == sum && pos_tripla == "2") {
                        Log.d("sibComp", "2 tripletta")
                        Log.d("sibComp", "Stringa 1: $stringa1 ") //prova comparazione pos1
                        Log.d("sibComp", "Stringa 2: $stringa2 ") //prova comparazione pos1

                        str1pos2 = stringa1
                        str2pos2 = stringa2
                    }
                    if (pos1 == sum3 && pos2 == sum && pos3 == sum2 && pos_tripla == "3") {
                        Log.d("sibComp", " 3 tripletta")
                        Log.d("sibComp", "Stringa 1: $stringa1 ") //prova comparazione pos1
                        Log.d("sibComp", "Stringa 2: $stringa2 ") //prova comparazione pos1

                        str1pos3 = stringa1
                        str2pos3 = stringa2
                    }
                    // Log.d("sib", "pos1 $pos1") //prova stampa pos1 funzionante
                }

                launchSibilla(user)

            }

          }
        }

   //funzione che lancia la schermata con il responso della Sibilla
    private fun launchSibilla(user: User) {
        val intent = Intent(this, SibillaActivity::class.java)
        intent.putExtra("str1pos1", str1pos1)
        intent.putExtra("str2pos1", str2pos1)
        intent.putExtra("str1pos2", str1pos2)
        intent.putExtra("str2pos2", str2pos2)
        intent.putExtra("str1pos3", str1pos3)
        intent.putExtra("str2pos3", str2pos3)
        intent.putExtra("Obj",user as Serializable)
        startActivity(intent)
    }

    private fun launchLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


   //funzione che prende le prime lettere delle stringhe
    private fun getFirstLetter(stringa : String): Array<String> {
        val delim = " "
        val arr = stringa.split(delim).toTypedArray()


        for (array in arr) {
            Log.d("array", array)
        }

        var arrFirstLetter = arrayOf<String>()

        for (i in arr.indices) {
            if(arr[i] != "") {
                arrFirstLetter += arrayOf(arr[i].substring(0, 1))
            }
        }

            return arrFirstLetter
        }


    //funzione che prende il valore della tabella in base alla lettera
        private fun getNumFromArr(firstLetter :  String)  :  Int {

            KeyLetters.forEach { (key, value) ->
                    if(firstLetter == key){
                        Log.d("first", "sono entrato qui $key $value")
                        return value
                    }
               }
            return 0
        }


       //funzione che prende l'unità dei numeri
        private fun getRightNumber(sum : Int) : Int {
          var somma = " "

           if(sum > 99 ) {
             somma =  sum.toString().substring(2,3)
           } else if(sum >= 10){
             somma =   sum.toString().substring(1,2)
           }else{
               somma = sum.toString()
           }

            Log.d("Sum", somma.toString())
            return somma.toInt()
        }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()

                //fa il refresh della pagina quando si viene sloggati
                finish()
                overridePendingTransition(0, 0)
                startActivity(getIntent())
                overridePendingTransition(0, 0)

                Log.d("logout", "logout clicked")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}

private fun String.Normalize(): String {
    return Normalizer.normalize(this, Normalizer.Form.NFD).replace("[^\\p{ASCII}]".toRegex(), "")
}







