package com.xiyou.community.view.adapter.questionCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiyou.community.R
import com.xiyou.community.data.ui.QuestionCard

class QuestionCardViewHolder( v: View):RecyclerView.ViewHolder(v) {
    private val head: ImageView = v.findViewById(R.id.iv_question_card_head)
    private val title: TextView = v.findViewById(R.id.tv_question_card_title)
    private val content: TextView = v.findViewById(R.id.tv_question_card_content)
    private val date : TextView = v.findViewById(R.id.tv_question_card_date)
    fun bind (question: QuestionCard)
    {
        head.load(question.head)
        title.text = question.title
        content.text = question.content
        date.text = question.date
    }
    companion object{
        fun create(parent: ViewGroup): QuestionCardViewHolder {
            val v = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_question_card, parent, false)
            return QuestionCardViewHolder(v)
        }
    }
}