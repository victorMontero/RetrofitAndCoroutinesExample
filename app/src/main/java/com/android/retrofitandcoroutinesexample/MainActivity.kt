package com.android.retrofitandcoroutinesexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get_image.setOnClickListener {
            launch(Dispatchers.Main){
                try{
                    val response = ApiAdapter.apiClient.getRandomDogImage()
                    if(response.isSuccessful && response.body() != null){
                        val data = response.body()!!
                        data.message?.let {
                            iv_dog_image.load(it)
                        }
                    } else {
                        Toast.makeText(this@MainActivity,
                            "error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG).show()

                    }
                } catch (e: Exception){
                    Toast.makeText(this@MainActivity,
                    "error occurred: ${e.message}",
                    Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}