package com.example.projecttestapp.startPage.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result() : Parcelable {

    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("result")
    @Expose
    var result: Double = 0.toDouble()
    @SerializedName("student")
    @Expose
    var student_id: Int = 0
    @SerializedName("subject")
    @Expose
    var subject_id: Int = 0
    constructor(id:Int,result: Double,student_id: Int,subject_id:Int) : this() {
        this.id=id
        this.result=result
        this.student_id=student_id
        this.subject_id=subject_id
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeDouble(result)
        parcel.writeInt(student_id)
        parcel.writeInt(subject_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            val id = parcel.readInt()
            val result = parcel.readDouble()
            val student_id = parcel.readInt()
            val subject_id = parcel.readInt()
            return Result(id, result, student_id, subject_id)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }

}
