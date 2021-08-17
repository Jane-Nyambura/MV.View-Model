package com.example.first_app.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.first_app.Repository.UserRepository
import com.example.first_app.models.RegistrationRequest
import com.example.first_app.models.RegistrationResponse
import com.example.first_app.models.logInRequest
import com.example.first_app.models.logInResponse
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    var userRepository=UserRepository()
    var registrationResponseLiveData=MutableLiveData<RegistrationResponse>()
    var registrationErrorLiveData=MutableLiveData<String>()



    fun registerStudent(registrationRequest: RegistrationRequest){
        viewModelScope.launch {
            var response=userRepository.registerUser(registrationRequest)
            if (response.isSuccessful){
                registrationResponseLiveData.postValue(response.body())
            }
            else{
                registrationErrorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}
//scope is the life time of an object.
//coroutine is a process and only be alive when the view model is alive
//.launch is the one that creates the coroutine
//view model creates the coroutine and it will remove them ,it will launch within a scope
//livedata-object that will hold data and also it can be observed