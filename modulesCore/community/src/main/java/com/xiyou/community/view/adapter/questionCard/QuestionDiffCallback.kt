package com.xiyou.community.view.adapter.questionCard

import androidx.recyclerview.widget.DiffUtil
import com.xiyou.community.data.net.QuestionData
import com.xiyou.community.data.ui.QuestionCard

class QuestionDiffCallback(): DiffUtil.ItemCallback<QuestionData>() {
    override fun areItemsTheSame(oldItem: QuestionData, newItem: QuestionData): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: QuestionData, newItem: QuestionData): Boolean {
        return oldItem.id == newItem.id
    }
}