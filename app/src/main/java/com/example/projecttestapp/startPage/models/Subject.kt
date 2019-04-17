package com.example.projecttestapp.startPage.models

import android.os.Parcel
import android.os.Parcelable

class Subject(var id:Int=0, var name: String? = null, val questions: ArrayList<Question>? = null, val results: ArrayList<Result>? = null,var teacher: Int = 0):
    Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(questions)
        parcel.writeTypedList(results)
    }
    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Subject> {
        override fun createFromParcel(parcel: Parcel): Subject {
            val id:Int = parcel.readInt()
            val name:String? = parcel.readString()
            val questions:ArrayList<Question>?  = parcel.createTypedArrayList(Question.CREATOR)
            val results:ArrayList<Result>? = parcel.createTypedArrayList(Result.CREATOR)
            return Subject(id, name, questions, results)
        }
        override fun newArray(size: Int): Array<Subject?> {
            return arrayOfNulls(size)
        }
    }
}
