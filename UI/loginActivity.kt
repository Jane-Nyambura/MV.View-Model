package com.example.first_app.UI

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.first_app.UI.Constance.Companion.ACCESS_TOKEN
import com.example.first_app.models.logInRequest

class LogInActivity : AppCompatActivity() {

    lateinit var bindin: ActivitylogInBinding
    lateinit var sharedPrefs:SharedPreferences
    val logViewModel: logInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        bindin = ActivitylogInBinding.inflate(layoutInflater)
        setContentView(bindin.root)


        sharedPrefs=getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE)

        bindin.button.setOnClickListener {
            bindin.progressBar2.visibility= View.VISIBLE

            val email = bindin.emaill.text.toString()
            if (email.isEmpty()) {
                bindin.emaill.setError("enter name")
            }
            val pass = bindin.passs.text.toString()
            if (pass.isEmpty()) {
                bindin.passs.setError("enter name")
            }
            val logRequest =
                logInRequest(
                    email = email, password = pass
                )
            logViewModel.logInStudent(logRequest)


        }
    }
    //george.odenyo@actuatedigital.co.ke

    override fun onResume() {
        super.onResume()
        logViewModel.logResponseLiveData.observe(this, { logResponse ->
//            if (!logResponse.student_id.isNullOrEmpty()) {
            bindin.progressBar2.visibility= View.GONE
            var editor=sharedPrefs.edit()
            editor.putString(Constants.ACCESS_TOKEN, logResponse.access_token)
            editor.putString(Constants.STUDENT_ID, logResponse.student_id)
            editor.apply()
            Toast.makeText(baseContext, logResponse.message, Toast.LENGTH_LONG).show()
            val intent = Intent(baseContext, CoursesActivity::class.java)
            startActivity(intent)

//            }
        })
        logViewModel.logErrorLiveData.observe(this, { error ->
            bindin.progressBar2.visibility= View.GONE
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        })

    }
}




//
//import android.content.Context
//import android.content.SharedPreferences
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.activity.viewModels
//import com.example.first_app.ViewModel.loginViewModel
//import com.example.first_app.databinding.ActivityLoginBinding
//
//class loginActivity : AppCompatActivity() {
//
//    lateinit var binding:ActivitylogInBinding
//    val loginViewModel: loginViewModel by viewModels()
//    lateinit var  sharedprefs:SharedPreferences
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding=ActivityLoginBinding.inflate(logInInflater)
//        setContentView(binding.activity_login)
//        sharedprefs=getSharedPreferences(Constance.PREFS_FILE,Context.MODE_APPEND)
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        binding.btnLogin.setOnClicklistener{
//            validateLogin()
//        }
//        loginViewModel.logInResponseLiveData.observe(this { logInResponse ->
//            var editor=sharedprefs.edit()
//            editor=putString(com.example.first_app.UI.Constance.ACCESS_TOKEN,logInResponse.accessToken)
//            editor=putString(com.example.first_app.UI.Constance.STUDENT_ID,logInRequest.studentId)
//            editor.apply()
//            binding.progressbar
//            Toast.makeText(baseContext, logInResponse.message, Toast.LENGTH_LONG).show()
//
//        loginViewModel.logInRequestErrorLiveData.observe(this { error ->
//            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//
//
//            fun validateLogin() {
//                var email = binding.etEmail.text.toString()
//                var password = binding.etpass.text.toString()
//                var error = false
//                if (email.isEmpty()) {
//                    binding.etuserName.setError("This field is compulsary")
//                }
//                if (password.isEmpty()) {
//                    binding.etpass.setError("This field is compulsary")
//                }
//                if (error) {
//                    binding.progracessBar3.visibility.GONE
//                    var logInRequest = logInRequest(email, password)
//                    loginViewModel.logInStudent(logInRequest)
//                    fun validateLogin() {
//                        var email = binding.etemmail.text.toString()
//                        var password = binding.etpasswrd.text.toString()
//                        var error = false
//                        if (password.isBlank() || email.isBlank())
//
//                        fun validateLogIn() {
//                            var email = binding.etemmail.text.toString()
//                            var password = binding.etpasswrd.text.toString()
//                            var error = false
//
//                            if (email.isBlank() || email.isEmpty()) {
//                                var error = true
//                                binding.etemmail.setError("Email is required")
//                            }
//                            if (password.isBlank() || password.isEmpty()) {
//                                var error = true
//                                binding.etemmail.setError("Password is required")
//                            }
//                            if (!error) {
//                                binding.root.visibility = View.GONE
//                            }
//                        }
//
//                    }
//                }
//            }
//
//        }


