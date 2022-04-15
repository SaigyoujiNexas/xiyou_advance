package com.xiyou.community.view.adapter.questionAnswer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiyou.advance.modulespublic.common.utils.StringUtil
import com.xiyou.community.R
import com.xiyou.community.data.QuestionAnswer

class QuestionAnswerViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
    private val head: ImageView = itemView.findViewById(R.id.iv_question_answer_head);
    private val name: TextView = itemView.findViewById(R.id.tv_question_answer_name);
    private val date: TextView = itemView.findViewById(R.id.tv_question_answer_date)
    private val content: TextView = itemView.findViewById(R.id.tv_question_answer_content)

    fun bind(ans: QuestionAnswer)
    {
        head.load(ans.head)
        name.text = ans.name
        date.text = StringUtil.timeInMillisToString(ans.date)
        content.text = ans.content
    }
    companion object{
        fun create(parent: ViewGroup): QuestionAnswerViewHolder{
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_question_answer, parent, false)
            return QuestionAnswerViewHolder(v)
        }
    }
}