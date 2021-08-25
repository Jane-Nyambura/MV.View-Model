package com.example.first_app.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_app.models.Courses
import com.example.first_app.CousesAdapter
import com.example.first_app.R

class CoursesActivity : AppCompatActivity() {
    lateinit var rvCourses: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        displayCourses()
    }
     fun displayCourses(){
         var coursesList= listOf(
             Courses("IOT","467665","IOT for 21st","Bare"),
             Courses("Python","467665","Python for 21st","Mwai"),
             Courses("Kotlin","467665","KOtlin for 21st","John"),
             Courses("UI/UX design","467665","UI/UX design for 21st","Eric"),
             Courses("JavaScript","467665","JavaScript for 21st","Purity"),
             Courses("UX research","467665","UX research for 21st","Joy"),
             Courses("PD","467665","PD for 21st","Awoko"),
             Courses("NYJ","467665","NYJ for 21st","Veronica"),
         )
         rvCourses=findViewById(R.id.rvCourses)
         var cousesAdapter= CousesAdapter(coursesList)
         rvCourses.layoutManager=LinearLayoutManager(baseContext)
         rvCourses.adapter=cousesAdapter

         rvCourses.apply {
             layoutManager=LinearLayoutManager(baseContext)
             adapter=cousesAdapter
         }
     }
}