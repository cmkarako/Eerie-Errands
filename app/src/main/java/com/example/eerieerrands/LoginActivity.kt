package com.example.eerieerrands

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.example.eerieerrands.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            if (validateLoginDetails()) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
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


}