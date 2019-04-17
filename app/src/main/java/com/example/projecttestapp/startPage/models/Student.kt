package com.example.projecttestapp.startPage.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Student() : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("firstName")
    @Expose
    var firstName: String? = null
    @SerializedName("lastName")
    @Expose
    var lastName: String? = null
    @SerializedName("login")
    @Expose
    var login: String? = null
    @SerializedName("password")
    @Expose
    var password: String? = null
    @SerializedName("results")
    @Expose
    var results: ArrayList<Result>? = null

    constructor(id:Int,firstName:String,lastName:String,login:String,password:String,results:ArrayList<Result>) : this() {
        this.id=id
        this.firstName = firstName
        this.lastName=lastName
        this.login=login
        this.password = password
        this.results = results
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(login)
        parcel.writeString(password)
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            val id:Int? = parcel.readInt()
            val firstName:String? = parcel.readString()
            val lastName:String? = parcel.readString()
            val login:String? = parcel.readString()
            val password:String? = parcel.readString()
            val results:ArrayList<Result>? = parcel.createTypedArrayList(Result)
            return Student(id!!,firstName!!,lastName!!,login!!,password!!,results!!)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

}
