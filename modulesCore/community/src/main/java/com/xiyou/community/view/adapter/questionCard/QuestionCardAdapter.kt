package com.xiyou.community.view.adapter.questionCard

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.xiyou.community.data.net.QuestionData
import com.xiyou.community.data.ui.QuestionCard
import java.util.*

class QuestionCardAdapter(diffCallback: DiffUtil.ItemCallback<QuestionData>)
    : ListAdapter<QuestionData, QuestionCardViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionCardViewHolder {
        return QuestionCardViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: QuestionCardViewHolder, position: Int) {
        val item = getItem(position)

        val date =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            {
                val format = SimpleDateFormat("yyyy-MM-dd")
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = item.date
                format.format(calendar)
            }
        else{
            val date = Date(item.date)
                val str = "${date.year}-" +
                        if(date.month < 10) "0${date.month}" else "${date.month}-"+
                                if(date.day < 10) "0${date.day}" else "${date.day}"
            str
        }
        val card = QuestionCard(user = item.user, date = date, title = item.title, content = item.content, head = item.head)
        holder.bind(card)
    }
}