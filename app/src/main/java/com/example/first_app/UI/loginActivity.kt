package com.example.first_app.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.first_app.API.ApiClient
import com.example.first_app.API.ApiInterface
import com.example.first_app.R
import com.example.first_app.ViewModel.loginViewModel
import com.example.first_app.models.logInRequest
import com.example.first_app.models.logInResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val loginViewModel: loginViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
        override fun onResume() {
            super.onResume()
            loginViewModel.logInResponseLiveData.observe(this { logInResponse ->
                if (!logInResponse.password.isNullOrEmpty()) {
                    Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
                }
            })
            loginViewModel.logInRequestErrorLiveData.observe(this { error ->
                Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

            })
        }

//        var etpassword = findViewById<EditText>(R.id.etpass)
//        var etemail = findViewById<EditText>(R.id.etEmail)
//        var btnLogin = findViewById<Button>(R.id.btnLogin)
    }

