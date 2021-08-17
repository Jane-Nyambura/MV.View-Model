package com.example.first_app.UI


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.first_app.API.ApiClient
import com.example.first_app.API.ApiInterface
import com.example.first_app.R
import com.example.first_app.ViewModel.UserViewModel
import com.example.first_app.ViewModel.loginViewModel
import com.example.first_app.databinding.ActivityMainBinding
import com.example.first_app.models.RegistrationRequest
import com.example.first_app.models.RegistrationResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels()
//    val LoginViewModel:loginViewModel by viewModels()//factory design model   //calling an interface that allows us
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        clickRegister()

    }

    fun setupSpinner() {
        var nationalities = arrayOf("Kenya", "Uganda", "Rwanda", "SouthSudan")
        var nationalitiesAdapter =
            ArrayAdapter(baseContext, android.R.layout.simple_spinner_item, nationalities)
        nationalitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalitiesAdapter
    }

    fun clickRegister() {
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
                userViewModel.registerStudent(registrationRequest)       //

            }
//    data class ApiError(var errors:List<String>)

        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registrationResponseLiveData.observe(this, { RegistrationResponse ->
            if (!RegistrationResponse.studentId.isNullOrEmpty()) {
                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
            }
        })
        userViewModel.registrationErrorLiveData.observe(this, { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

        })
    }


//    override fun onResume(){
//        super.onResume()
//        loginViewModel.logInResponseLiveData.observe(this { logInResponse ->
//            if (!logInResponse.password.isNullOrEmpty()) {
//                Toast.makeText(baseContext, "Registration successful", Toast.LENGTH_LONG).show()
//            }
//        })
//        loginViewModel.logInRequestError.observe(this { error ->
//            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
//
//        })
//    }
}

