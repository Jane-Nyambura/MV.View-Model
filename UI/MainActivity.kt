package com.example.first_app.UI

import android.R
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.first_app.ViewModel.UserViewModel
import com.example.first_app.databinding.ActivityMainBinding
import com.example.first_app.models.RegistrationRequest


class MainActivity :  AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var sharedprefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedprefs=getSharedPreferences(Constance.PREFS_FILE, Context.MODE_PRIVATE)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.spNationality()
        binding.btRegister.setOnClickListener {
            if (binding.etName.text.toString().isEmpty() || binding.etDob.text.toString()
                    .isEmpty() || binding.etPhone.text.toString().isEmpty()
            ) {
                binding.etName.setError("Please input name")
                binding.etDob.setError("Please input Date of Birth")
                binding.etEmail.setError("Please input email")
                binding.etPhone.setError("Please input phone")


                var name = binding.etName.text.toString()
                var date_of_birth = binding.etDob.text.toString()
                var email = binding.etEmail.text.toString()
                var phone_number = binding.etPhone.text.toString()

                var nationality = binding.spNationality.selectedItem.toString()


                var registrationRequest = RegistrationRequest(
                    name = name,
                    phoneNumber = phone_number,
                    email = email,
                    nationality = nationality,
                    dateOfBirth = date_of_birth,
            )

            binding.progressBar.visibility=View.VISIBLE

            userViewModel.registerStudent(regRequest)
        }
        binding.textView5.setOnClickListener {
            binding.progressBar.visibility=View.VISIBLE
            val intent = Intent(baseContext, LogInActivity::class.java)
            startActivity(intent)
        }
        redirectUser()
    }

    fun redirectUser(){
        //to check whether the user is logged in or not
        val token=sharedprefs.getString(Constance.ACCESS_TOKEN,Constance.EMPTY_STRING)
        if (token!!.isNotEmpty()){
            startActivity(Intent(baseContext, CoursesActivity::class.java))
        }
        else{
            startActivity(Intent(baseContext, LogInActivity::class.java))
        }
    }
    fun setUpSpinner() {


        val nationalities =
            arrayListOf(
                "Kenyan",
                "Rwandan",
                "SouthSudanese",
                "Sudanese",
                "Rwandan",
                "Ugandan"
            )
        val nationalitiesAdapter =
            ArrayAdapter(
                baseContext,
                R.layout.simple_spinner_dropdown_item,
                nationalities
            )
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = nationalitiesAdapter


    }
    // define the endpoint, repository, view model, observe, make the call and imply

    override fun onResume() {
        super.onResume()
        userViewModel.registrationResponseLiveData.observe(this, { regResponse ->
            binding.progressBar.visibility= View.GONE
            if (!regResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
            }
        })
        userViewModel.registrationErrorLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
        })
    }


}

//
//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.*
//import androidx.activity.viewModels
//import com.example.first_app.ViewModel.UserViewModel
//import com.example.first_app.databinding.ActivityLoginBinding
//import com.example.first_app.databinding.ActivityMainBinding
//import com.example.first_app.models.RegistrationRequest
//
//class MainActivity : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding
//    lateinit var loginActivity:ActivityLoginBinding
//    val userViewModel: UserViewModel by viewModels()
//    lateinit var sharedPrefs: SharedPreferences
////    val LoginViewModel:loginViewModel by viewModels()//factory design model   //calling an interface that allows us
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//    sharedPrefs=getSharedPreferences(Constance.PREFS_FILE,Context.MODE_APPEND)
//        setContentView(binding.root)
//        setupSpinner()
//        clickRegister()
//        redirectUser()
//
//    }
//    fun redirectUser(){
//        var token=sharedPrefs.getString()
//    }
//
//    fun setupSpinner() {
//        var nationalities = arrayOf("Kenya", "Uganda", "Rwanda", "SouthSudan")
//        var nationalitiesAdapter =
//            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationalities)
//        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spNationality.adapter = nationalitiesAdapter
//    }
//
//    fun clickRegister() {
//        binding.btRegister.setOnClickListener {
//            if (binding.etName.text.toString().isEmpty() || binding.etDob.text.toString()
//                    .isEmpty() || binding.etPhone.text.toString().isEmpty()
//            ) {
//                binding.etName.setError("Please input name")
//                binding.etDob.setError("Please input Date of Birth")
//                binding.etEmail.setError("Please input email")
//                binding.etPhone.setError("Please input phone")
//
//
//                var name = binding.etName.text.toString()
//                var date_of_birth = binding.etDob.text.toString()
//                var email = binding.etEmail.text.toString()
//                var phone_number = binding.etPhone.text.toString()
//
//                var nationality = binding.spNationality.selectedItem.toString()
//
//
//                var registrationRequest = RegistrationRequest(
//                    name = name,
//                    phoneNumber = phone_number,
//                    email = email,
//                    nationality = nationality,
//                    dateOfBirth = date_of_birth,
//                )
//                userViewModel.registerStudent(registrationRequest)
//            var intent=Intent(baseContext,loginActivity::class.java)
//            startActivity(intent)//
//
//            }
//
//        }
//        binding.progressBar.visibility= View.GONE
//    }
//    override fun onResume() {
//        super.onResume()
//        userViewModel.registrationResponseLiveData.observe(this, { RegistrationResponse ->
//            if (!RegistrationResponse.studentId.isNullOrEmpty()) {
//                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
//            }
//        })
//        userViewModel.registrationErrorLiveData.observe(this, { error ->
//            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//
//        })
//    }
//
//
//}

