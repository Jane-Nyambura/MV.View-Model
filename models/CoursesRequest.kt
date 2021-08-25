package com.example.first_app.models

 data class CoursesRequest
     (
var date_created: String,
var date_updated: String,
var created_by:String,
var updated_by: String,
var active: Boolean,
var course_id: String,
var course_name: String,
var course_code: String,
var description: String,
var instructor: String
)