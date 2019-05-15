package com.example.projecttestapp.startPage.network

import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.models.Student
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.models.Teacher
import retrofit2.http.*

interface ApiService {
    // actions for teacher
    @GET("api/teachers/")
    fun getTeachers():retrofit2.Call<ArrayList<Teacher>>

    @POST("api/teachers/")
    fun addTeacher(@Body teacher: Teacher): retrofit2.Call<Teacher>

    @GET("/api/teachers/")
    fun getTeacherByLogin(@Query("login")login:String, @Query("password")password:String?):retrofit2.Call<ArrayList<Teacher>>

    //actions for student
    @GET("api/students/")
    fun getStudents():retrofit2.Call<ArrayList<Student>>

    @POST("api/students/")
    fun addStudent(@Body student: Student): retrofit2.Call<Student>

    @GET("api/students/{id}/")
    fun getStudent(@Path("id")id:Int):retrofit2.Call<Student>

    @GET("api/students/")
    fun getStudentByLogin(@Query("login")login:String, @Query("password")password:String?):retrofit2.Call<ArrayList<Student>>

    //actions for subjects

    @GET("api/subjects/")
    fun getSubjects():retrofit2.Call<ArrayList<Subject>>

    @GET("api/subjects/{id}/")
    fun getSubject(@Path("id")id:Int):retrofit2.Call<Subject>

    @POST("api/subjects/")
    fun addSubject(@Body subject: Subject):retrofit2.Call<Subject>

    @DELETE("api/subjects/{id}/")
    fun deleteSubject(@Path("id")id:Int):retrofit2.Call<Unit>

    //actions for questions

    @GET("api/questions/")
    fun getQuestions():retrofit2.Call<ArrayList<Question>>

    @POST("api/questions/")
    fun addQuestion(@Body question: Question):retrofit2.Call<Question>

    @PATCH("api/questions/{id}/")
    fun updateQuestion(@Body question: Question):retrofit2.Call<Question>

    //actions for results
    @GET("api/results/")
    fun getResults():retrofit2.Call<ArrayList<Result>>

    @POST("api/results/")
    fun addResult(@Body result: Result):retrofit2.Call<Result>

}