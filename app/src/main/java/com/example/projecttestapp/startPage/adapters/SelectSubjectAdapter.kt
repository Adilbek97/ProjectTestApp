package com.example.projecttestapp.startPage.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.projecttestapp.R
import com.example.projecttestapp.startPage.models.Subject
import com.example.projecttestapp.startPage.showToast
import com.example.projecttestapp.startPage.student_page.pass_test_page.PassTestActivity
import com.example.projecttestapp.startPage.teacher_page.add_question_page.AddQuestionActivity
import com.example.projecttestapp.startPage.teacher_page.add_subject_page.AddSubjectActivity
import com.example.projecttestapp.startPage.teacher_page.see_result_page.SeeResultActivity
import kotlinx.android.synthetic.main.select_subject_item.view.*

class SelectSubjectAdapter(val context: Context, var result: ArrayList<Subject>, val activity_id: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    private val HEADER = 0
    private val ITEM = 0
    private val FOOTER = 1
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        if (position == FOOTER) {
            val view =
                LayoutInflater.from(context).inflate(R.layout.show_question_adapter_footer_item, viewGroup, false)
            return FooterHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.select_subject_item, viewGroup, false)
            return MyViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return result.size + 1
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is MyViewHolder && !result.isEmpty()) {
            val res: Subject? = result[position]
            viewHolder.setData(res, position)
        }
    }
    /*  override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
          val res: Subject? = result[position]
          viewHolder.setData(res,position)
      }*/

    override fun getItemViewType(position: Int): Int {
        return if (position == result.size) {
            FOOTER
        } else
            ITEM
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val intent: Intent
        var currentSubject: Subject? = null
        var currentPosition: Int = 0
        init {
            if (activity_id == 1) {
                intent = Intent(context, SeeResultActivity::class.java)
            }else if(activity_id == 5){
                intent = Intent(context, PassTestActivity::class.java)
            } else {
                intent = Intent(context, AddQuestionActivity::class.java)
            }
            itemView.setOnClickListener {
                Toast.makeText(context, currentSubject!!.name + " clicked", Toast.LENGTH_SHORT).show()
//                    intent.putExtra("subject_id",currentSubject!!.id)
//                    intent.putExtra("subject_name",currentSubject!!.name)
                intent.putExtra("subject_key", currentSubject)
                intent.putExtra("results_key", currentSubject?.results)
                context.startActivity(intent)
            }
        }

        fun setData(subject: Subject?, position: Int) {
            this.currentSubject = subject
            this.currentPosition = position
            itemView.select_subject_name_tv.text = currentSubject!!.name
        }
    }

    inner class FooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val intent = Intent(context, AddSubjectActivity::class.java)
                context.startActivity(intent)

                context.showToast("Add subject")
            }
        }
    }
}
