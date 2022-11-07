package com.example.eerieerrands

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.eerieerrands.databinding.ActivityLoginBinding
import com.example.eerieerrands.model.LoginUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    val api = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            if (validateLoginDetails()) {
                loginUser()
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    fun validateLoginDetails() : Boolean {
        return when {
            TextUtils.isEmpty(binding.editTextLoginEmail.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("You've been spooked! Email required to enter the haunted house.", true)
                false
            }
            TextUtils.isEmpty(binding.editTextLoginPassword.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("Did a ghost steal your password?", true)
                false
            }
            else -> {
                true
            }
        }
    }

    fun loginUser() {
        val email = binding.editTextLoginEmail.text.toString()
        val password = binding.editTextLoginPassword.text.toString()

        MainScope().launch(Dispatchers.IO) {
            try {
                val user = LoginUser(email, password)
                Log.d("Login", "${email} ${password}")
                val response = api.service.login(user).awaitResponse()
                Log.d("RESPONSE CODE", response.code().toString())
                val responseCode = response.code().toString()
                if (responseCode == "200") {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Account has been logged in.", Toast.LENGTH_LONG).show()
                    }
                }
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        val jwt = response.body()?.jwt
                        Log.d("TOKEN", "${jwt}")
                    }
                }
            }
            catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Goblin spooks!!! Login not successful.", Toast.LENGTH_LONG).show()
                    //showErrorSnackBar("Goblin spooks!!! Registration not successful.", true)
                    Log.d("Error", e.message.toString())
                }
            }

        }
    }


}