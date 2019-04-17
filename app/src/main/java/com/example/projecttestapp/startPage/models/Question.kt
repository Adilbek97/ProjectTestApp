package com.example.projecttestapp.startPage.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Question() : Parcelable {
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("question")
    @Expose
    var question: String? = null
    @SerializedName("option_A")
    @Expose
    var optionA: String? = null
    @SerializedName("option_B")
    @Expose
    var optionB: String? = null
    @SerializedName("option_C")
    @Expose
    var optionC: String? = null
    @SerializedName("option_D")
    @Expose
    var optionD: String? = null
    @SerializedName("correct_option")
    @Expose
    var correctOption: String? = null
    @SerializedName("subject")
    @Expose
    var subject_id: Int = 0
    constructor(id:Int,question: String,optionA:String,optionB:String,optionC: String,optionD: String,correctOption:String,subject_id:Int) : this() {
        this.id=id
        this.question=question
        this.optionA=optionA
        this.optionB=optionB
        this.optionC=optionC
        this.optionD=optionD
        this.correctOption=correctOption
        this.subject_id=subject_id
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(question)
        parcel.writeString(optionA)
        parcel.writeString(optionB)
        parcel.writeString(optionC)
        parcel.writeString(optionD)
        parcel.writeString(correctOption)
        parcel.writeInt(subject_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            val id:Int? = parcel.readInt()
            val question:String? = parcel.readString()
            val optionA:String? = parcel.readString()
            val optionB:String? = parcel.readString()
            val optionC:String? = parcel.readString()
            val optionD:String? = parcel.readString()
            val correctOption:String? = parcel.readString()
            val subject_id:Int? = parcel.readInt()
            return Question(id!!, question!!, optionA!!, optionB!!, optionC!!, optionD!!, correctOption!!, subject_id!!)
        }
        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }

}