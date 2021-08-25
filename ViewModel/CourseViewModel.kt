package com.example.first_app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.first_app.Repository.UserRepository
import com.example.first_app.models.CoursesResponse
import kotlinx.coroutines.launch


class CourseViewModel : ViewModel() {
        var userRepository=
            UserRepository()
        var courseResponseLiveData = MutableLiveData<List<CoursesResponse>>()
        var courseErrorLiveData = MutableLiveData<String>()



        fun showCourses(list: List<CoursesResponse>){
            viewModelScope.launch {
                val response = userRepository.retrofit()
                if (response.isSuccessful){
                    courseResponseLiveData.postValue(response.body())
                }
                else{
                    courseErrorLiveData.postValue(response.errorBody()?.string())
                }
            }
        }

    }
}