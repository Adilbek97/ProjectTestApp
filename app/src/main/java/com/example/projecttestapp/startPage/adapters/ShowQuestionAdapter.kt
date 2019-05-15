package com.example.projecttestapp.startPage.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.teacher_page.add_question_page.AddQuestionActivity
import com.example.projecttestapp.startPage.teacher_page.add_question_page.show_question_page.change_question_page.ChangeQuestionActivity
import kotlinx.android.synthetic.main.question_item.view.*
import kotlinx.android.synthetic.main.show_question_adapter_footer_item.view.*

class ShowQuestionAdapter(val context: Context, var question:ArrayList<Question>, val subject: Subject): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER = 0
    private val ITEM = 1
    private val FOOTER = 2
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        if (position == HEADER){
            val view = LayoutInflater.from(context).inflate(R.layout.show_question_adapter_footer_item,viewGroup,false)
            return HeaderHolder(view)
        }
        if (position == FOOTER){
            val view = LayoutInflater.from(context).inflate(R.layout.show_question_adapter_footer_item,viewGroup,false)
            return FooterHolder(view)
        }else{
            val view =  LayoutInflater.from(context).inflate(R.layout.question_item,viewGroup,false)
            return MyViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        if (question.size == 0) {
            return 1
        }
        return question.size+2
    }
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is MyViewHolder){
            viewHolder.setData(this.question[position-1],position-1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == question.size+1){
            FOOTER
        }else if(position==0){
            HEADER
        }else
            ITEM
    }

//    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
//        myViewHolder.setData(this.question[position],position)
//    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var currentQuestion:Question?=null
        var currentPosition:Int= 0
        fun setData(question: Question?, position:Int){
            this.currentPosition = position
            this.currentQuestion = question
            itemView.item_question_et.text = currentQuestion!!.question
            itemView.item_optionA_et.text = currentQuestion!!.optionA
            itemView.item_optionB_et.text = currentQuestion!!.optionB
            itemView.item_optionC_et.text = currentQuestion!!.optionC
            itemView.item_optionD_et.text = currentQuestion!!.optionD
            itemView.item_correct_option_et.text = currentQuestion!!.correctOption
            itemView.item_changeQuestion_btn.setOnClickListener {
                context.showToast("woul you like to change this question")
                val intent=Intent(context, ChangeQuestionActivity::class.java)
                intent.putExtra("currentQuestionKey",currentQuestion)
                intent.putExtra("subject_key",subject)
                context.startActivity(intent)
            }
        }
    }
    inner class FooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, AddQuestionActivity::class.java)
                intent.putExtra("subject_key",subject)
                context.startActivity(intent)
            }
        }
    }
    inner class HeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.show_question_item_tv.text = subject.name
            itemView.show_question_item_tv.textSize = 20F

        }
    }
}