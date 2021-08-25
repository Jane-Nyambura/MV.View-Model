package com.example.first_app.Repository

import com.example.first_app.API.ApiClient
import com.example.first_app.API.ApiClient.retrofit
import com.example.first_app.API.ApiInterface
import com.example.first_app.UI.Constance
import com.example.first_app.models.CoursesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepository {
    var retrofit = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun getCourses(): Response<List<CoursesResponse>> {
        return withContext(Dispatchers.IO){
            val responce=retrofit.getCourses(Constance.ACCESS_TOKEN)
            return@withContext responce
        }
    }
}