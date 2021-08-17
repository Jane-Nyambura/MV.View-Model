package com.example.first_app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.first_app.Repository.UserRepository
import com.example.first_app.models.logInRequest
import com.example.first_app.models.logInResponse
import kotlinx.coroutines.launch


class loginViewModel:ViewModel() {
    var userRepository = UserRepository()
    var logInResponseLiveData = MutableLiveData<logInResponse>()
    var logInRequestErrorLiveData = MutableLiveData<String>()

    fun logInStudent(logInRequest: logInRequest) {
        viewModelScope.launch {
            var response = userRepository.loginUser(logInRequest)
            if (response.isSuccessful) {
                logInResponseLiveData.postValue(response.body())
            } else {
                logInRequestErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}