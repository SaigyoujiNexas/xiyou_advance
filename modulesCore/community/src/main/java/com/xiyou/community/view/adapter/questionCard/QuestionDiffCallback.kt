package com.xiyou.community.view.adapter.questionCard

import androidx.recyclerview.widget.DiffUtil
import com.xiyou.community.data.ui.QuestionCard

class QuestionDiffCallback(): DiffUtil.ItemCallback<QuestionCard>() {
    override fun areItemsTheSame(oldItem: QuestionCard, newItem: QuestionCard): Boolean {
        return oldItem === newItem
    }
    override fun areContentsTheSame(oldItem: QuestionCard, newItem: QuestionCard): Boolean {
        return oldItem.id == newItem.id
    }
}