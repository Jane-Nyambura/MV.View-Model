package com.example.first_app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.first_app.Repository.logInRepository
import com.example.first_app.models.logInRequest
import com.example.first_app.models.logInResponse
import kotlinx.coroutines.launch


class loginViewModel:ViewModel() {
    var userRepository = logInRepository()
    var logInResponseLiveData = MutableLiveData<logInResponse>()
    var logInRequestErrorLiveData = MutableLiveData<String>()

    fun logInStudent(logInRequest: logInRequest) {
        viewModelScope.launch {
            var response = userRepository.loginStudent(logInRequest)
            if (response.isSuccessful) {
                logInResponseLiveData.postValue(response.body())
            } else {
                logInRequestErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}