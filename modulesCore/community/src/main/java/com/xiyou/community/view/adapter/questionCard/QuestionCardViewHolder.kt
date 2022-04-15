package com.xiyou.community.view.adapter.questionCard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiyou.advance.modulespublic.common.utils.StringUtil
import com.xiyou.community.R
import com.xiyou.community.data.QuestionCard
import java.math.RoundingMode

class QuestionCardViewHolder(val itemView: View):RecyclerView.ViewHolder(itemView) {
    private val head: ImageView = itemView.findViewById(R.id.iv_question_card_head)
    private val name: TextView = itemView.findViewById(R.id.tv_question_card_user_name)
    private val title: TextView = itemView.findViewById(R.id.tv_question_card_title)
    private val content: TextView = itemView.findViewById(R.id.tv_question_card_content)
    private val date : TextView = itemView.findViewById(R.id.tv_question_card_date)
    private val isSolved: ImageView = itemView.findViewById(R.id.iv_question_card_solved)
    fun bind (question: QuestionCard)
    {
        val bundle = bundleOf("question" to question)

        head.load(question.head){
            crossfade(true)
            placeholder(R.mipmap.img_user)
            
        }
        if(question.solved)
        {
            isSolved.visibility = View.VISIBLE
        }
        else{
            isSolved.visibility = View.INVISIBLE
        }
        name.text = question.user
        title.text = question.title
        content.text = question.comment
        date.text = question.date
        itemView.setOnClickListener(Navigation
            .createNavigateOnClickListener(R.id.action_community_to_questionInfo, bundle))
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