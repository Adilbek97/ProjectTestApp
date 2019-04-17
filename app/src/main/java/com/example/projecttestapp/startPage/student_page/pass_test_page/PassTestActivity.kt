package com.example.projecttestapp.startPage.student_page.pass_test_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.Constants
import com.example.projecttestapp.startPage.models.Question
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.models.Result
import com.example.projecttestapp.startPage.student_page.result_test_page.ResultTestActivity
import kotlinx.android.synthetic.main.activity_pass_test.*

class PassTestActivity : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null
    var index:Int = 0
    var lastIndex:Int = 0
    var corectResults = 0
    lateinit var answers:Array<String>
    lateinit var subject: Subject
    lateinit var selected_answers:Array<Int>
    lateinit var count_point:Array<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_test)
        val bundle:Bundle = intent.extras!!
        subject = bundle.getParcelable("subject_key") as Subject
        answers = Array(subject.questions!!.size,{" "})
        selected_answers = Array(subject.questions!!.size,{0})
        count_point = Array(subject.questions!!.size,{0})
        pass_test_activity_Title_tv.text = subject.name
        pass_test_activity_next_btn.setOnClickListener {
            this.index+=1
            onResume()
        }
        pass_test_activity_prev_btn.setOnClickListener {
            this.index-=1
            onResume()
        }
        pass_test_activity_count_btn.setOnClickListener {
            this.corectResults=0
            for (ans in this.count_point){
                if (ans.equals(1)){
                    this.corectResults+=1
                }
            }
            sharedPreferences  = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
//            showToast("result is "+ corectResults)
            val result = Result()
            result.result=countResult(corectResults)
            result.subject_id = subject.id
            result.student_id=sharedPreferences!!.getInt(Constants.APP_PREFERENCES_KEY,1)
            val intent = Intent(this, ResultTestActivity::class.java)
            intent.putExtra("result_key",result)
            startActivity(intent)
        }
    }
    fun countResult(res:Int):Double{
        val resultPoint:Double = ((res*100)/subject.questions!!.size).toDouble()
        return resultPoint
    }
    override fun onResume() {
        super.onResume()
        if (index>=lastIndex){
            pass_test_activity_optionA.isChecked=false
            pass_test_activity_optionB.isChecked=false
            pass_test_activity_optionC.isChecked=false
            pass_test_activity_optionD.isChecked=false
            lastIndex = index
        }else{
            findViewById<RadioButton>(selected_answers[index]).isChecked=true
            pass_test_activity_next_btn.visibility = Button.VISIBLE
        }
        pass_test_activity_next_btn.visibility = Button.INVISIBLE
        setQuestionAndOptions(subject.questions!!)
        pass_test_activity_radio_group_options.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(radioGroup: RadioGroup?, id: Int) {
                if(this@PassTestActivity.index< subject.questions!!.size-1) {
                    pass_test_activity_next_btn.visibility = Button.VISIBLE
                }
                if (this@PassTestActivity.index == subject.questions!!.size-1){
                    pass_test_activity_count_btn.visibility = Button.VISIBLE
                }
                when(id){
                    R.id.pass_test_activity_optionA ->{
                        answers[this@PassTestActivity.index]="A"
                        selected_answers[this@PassTestActivity.index]= R.id.pass_test_activity_optionA
                    }
                    R.id.pass_test_activity_optionB -> {
                        answers[this@PassTestActivity.index] = "B"
                        selected_answers[this@PassTestActivity.index]= R.id.pass_test_activity_optionB

                    }
                    R.id.pass_test_activity_optionC -> {
                        answers[this@PassTestActivity.index] = "C"
                        selected_answers[this@PassTestActivity.index]= R.id.pass_test_activity_optionC
                    }
                    R.id.pass_test_activity_optionD -> {
                        answers[this@PassTestActivity.index] = "D"
                        selected_answers[this@PassTestActivity.index]= R.id.pass_test_activity_optionD
                    }
                }
                if (subject.questions!!.get(index).correctOption.equals(answers.get(index))){
                    count_point[this@PassTestActivity.index]=1
                }else{
                    count_point[this@PassTestActivity.index]=-1
                }
            }
        })
        if (this.index>0){
            pass_test_activity_prev_btn.visibility = Button.VISIBLE
        }else{
            pass_test_activity_prev_btn.visibility = Button.INVISIBLE
        }
    }
    fun setQuestionAndOptions(questions: ArrayList<Question>){
        if(!questions.isEmpty()) {
            pass_test_activity_question_tv.text = questions.get(this.index).question
            pass_test_activity_optionA.text = questions.get(this.index).optionA
            pass_test_activity_optionB.text = questions.get(this.index).optionB
            pass_test_activity_optionC.text = questions.get(this.index).optionC
            pass_test_activity_optionD.text = questions.get(this.index).optionD
        }else{
            pass_test_activity_question_tv.text = "There is no question in this subject yet."
            pass_test_activity_optionA.visibility = View.GONE
            pass_test_activity_optionB.visibility = View.GONE
            pass_test_activity_optionC.visibility = View.GONE
            pass_test_activity_optionD.visibility = View.GONE
        }
    }
}
