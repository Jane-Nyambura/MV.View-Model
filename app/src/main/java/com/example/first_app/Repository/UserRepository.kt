package com.example.first_app.Repository

import com.example.first_app.API.ApiClient
import com.example.first_app.API.ApiInterface
import com.example.first_app.models.RegistrationRequest
import com.example.first_app.models.RegistrationResponse
import com.example.first_app.models.logInRequest
import com.example.first_app.models.logInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun registerUser(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.registerStudent(registrationRequest)
            return@withContext response
        }
    suspend fun loginUser(logInRequest: logInRequest): Response<logInResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.loginStudent(logInRequest)
            return@withContext response
        }
}
//IO- input,Output
//dispacher-it switches the context of the corotine
//@withContect- allows the data


