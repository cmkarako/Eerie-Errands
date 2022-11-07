package com.example.eerieerrands

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.eerieerrands.databinding.ActivityMainBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = Client()

        GlobalScope.launch {
            //val children = api.service.listRepos("4dea1e3b-4cce-4484-bc8a-eb203620a01f")
            Log.d("Test", "Here goes...")
           // Log.d("Test", "${children.body().toString()}")
            println("here goes...")
          //  println(children.body().toString())
        }
    }

}