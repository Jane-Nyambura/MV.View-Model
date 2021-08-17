package com.example.first_app.models

import com.google.gson.annotations.SerializedName

data class logInRequest(
    var message: String,
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("student_id")
    var studentId: String
    )
