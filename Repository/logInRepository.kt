package com.example.first_app.Repository

import com.example.first_app.API.ApiClient
import com.example.first_app.API.ApiInterface
import com.example.first_app.models.logInRequest
import com.example.first_app.models.logInResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class logInRepository {
    var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginStudent(logInRequest: logInRequest): Response<logInResponse> =
        withContext(Dispatchers.IO) {
            var response = retrofit.loginStudent(logInRequest)
            return@withContext response
        }
}