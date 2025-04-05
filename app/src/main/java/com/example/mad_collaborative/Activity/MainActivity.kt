package com.example.mad_collaborative

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.mad_collaborative.ui.theme.MADCollaborativeTheme
import com.google.firebase.database.FirebaseDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        database = FirebaseDatabase.getInstance()
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            MADCollaborativeTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { padding ->
                        Text(
                            text = "Hello, World!",
                            modifier = Modifier.padding(padding)
                        )
                    }
                )
            }
        }

        // Call the fetchData function to make the API call
        fetchData()
    }

    private fun fetchData() {
        val apiService = RetrofitInstance.retrofit.create(ApiService::class.java)
        val call = apiService.getData("123")

        call.enqueue(object : Callback<MyDataModel> {
            override fun onResponse(call: Call<MyDataModel>, response: Response<MyDataModel>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    // Handle the data
                    println("Data: $data")
                } else {
                    // Handle the error response
                    println("Error: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MyDataModel>, t: Throwable) {
                // Handle the failure
                println("Failure: ${t.message}")
            }
        })
    }
}