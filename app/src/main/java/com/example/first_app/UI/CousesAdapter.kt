package com.example.first_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.first_app.models.Courses


class CousesAdapter(var coursesList: List<Courses>):RecyclerView.Adapter<CousesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CousesViewHolder {
      var itemView=LayoutInflater.from(parent.context)
        .inflate(R.layout.couse_list_item,parent,false)
        return CousesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CousesViewHolder, position: Int) {
      var currentCourses=coursesList.get(position)
        holder.tvcouseName.text=currentCourses.coursesName
        holder.tvCouseCode.text=currentCourses.coursesCode
        holder.tvDescription.text=currentCourses.description
        holder.tvInstructor.text=currentCourses.instructor
    }

    override fun getItemCount(): Int {
        return coursesList.count()
    }
}
class  CousesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvcouseName=itemView.findViewById<TextView>(R.id.tvcouseName)
    var tvCouseCode=itemView.findViewById<TextView>(R.id.tvCouseCode)
    var tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)
    var tvInstructor=itemView.findViewById<TextView>(R.id.tvInstuctor)

}