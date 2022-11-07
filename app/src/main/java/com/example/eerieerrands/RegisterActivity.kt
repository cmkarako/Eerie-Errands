package com.example.eerieerrands

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.eerieerrands.databinding.ActivityRegisterBinding
import com.example.eerieerrands.model.NewUser
import com.example.eerieerrands.model.UserEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : BaseActivity() {

    lateinit var binding: ActivityRegisterBinding

    val api = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val firstNameString = binding.editTextFirstName.text.toString()
        val lastNameString = binding.editTextLastName.text.toString()
        val passwordString = binding.editTextTextPassword.text.toString()
        val emailString = binding.editTextEmailAddress.text.toString()

        if(validateRegisterDetails()) {
            MainScope().launch(Dispatchers.IO) {
                try {
                    val user = NewUser(emailString, passwordString, firstNameString, lastNameString)
                    Log.d("LAUNCH", "ATTEMPTING TO REGISTER USER")
                    val response = api.service.register(user).awaitResponse()
                    Log.d("RESPONSE CODE", response.code().toString())
                    val responseCode = response.code().toString()
                    if (responseCode == "500") {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "Account has been registered.", Toast.LENGTH_LONG).show()
                        }
                    }
                    if (response.isSuccessful) {
                        val data = response.body()!!
                        withContext(Dispatchers.Main) {
                            Log.d("TOKEN", "Response was successful")
                        }
                    }
                }
                catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Goblin spooks!!! Registration not successful.", Toast.LENGTH_LONG).show()
                        //showErrorSnackBar("Goblin spooks!!! Registration not successful.", true)
                        Log.d("Error", e.message.toString())
                    }
                }
            }
            //THIS CODE IS HERE SPECIFICALLY TO NAVIGATE TO NEW PAGE UNTIL THE API CALL IS ALL WORKING PROPERLY!!
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun validateRegisterDetails() : Boolean {

        val email : String = binding.editTextEmailAddress.text.toString().trim { it <= ' ' }
        val email2 : String = binding.editTextEmailAddress2.text.toString().trim { it <= ' ' }
        val password : String = binding.editTextTextPassword.text.toString().trim { it <= ' ' }
        val password2 : String = binding.editTextTextPassword2.text.toString().trim { it <= ' ' }

        return when {
            TextUtils.isEmpty(binding.editTextFirstName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("The Coroner requires a name to put on your tombstone.", true)
                false
            }
            TextUtils.isEmpty(binding.editTextLastName.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar("The Coroner requires a name to put on your tombstone.", true)
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email", true)
                false
            }
            TextUtils.isEmpty(email2) -> {
                showErrorSnackBar("Don't get tricked! Please confirm email", true)
                false
            }
            email != email2 -> {
                showErrorSnackBar("You've been spooked! Emails do not match", true)
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Did a ghost steal your password? Please enter", true)
                false
            }
            TextUtils.isEmpty(password2) -> {
                showErrorSnackBar("Don't get tricked! Please confirm password", true)
                false
            }
            password != password2 -> {
                showErrorSnackBar("Oops you've been spooked! Passwords do not match", true)
                false
            }
            else -> {
                //showErrorSnackBar("Thank you for registering", false)
                true
            }
        }
    }

}