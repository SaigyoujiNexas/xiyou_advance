package com.xiyou.community.view.adapter.questionCard

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.xiyou.community.data.QuestionCard

class QuestionCardAdapter(diffCallback: DiffUtil.ItemCallback<QuestionCard>)
    : ListAdapter<QuestionCard, QuestionCardViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionCardViewHolder {
        return QuestionCardViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: QuestionCardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}