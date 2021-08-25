package com.example.first_app.API



    import com.example.first_app.models.RegistrationRequest
    import com.example.first_app.models.RegistrationResponse
    import com.example.first_app.models.logInRequest
    import com.example.first_app.models.logInResponse
    import retrofit2.Call
    import retrofit2.Response
    import retrofit2.http.Body
    import retrofit2.http.POST

interface ApiInterface {
        @POST("/student/register")
        fun registerStudent(@Body registrationRequest: RegistrationRequest):Response<RegistrationResponse>

        @POST("/students/login")
        fun loginStudent(@Body LoginRequest:logInRequest):Response<logInResponse>

}