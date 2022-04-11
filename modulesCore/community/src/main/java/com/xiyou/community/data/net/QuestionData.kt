package com.xiyou.community.data.net

data class QuestionData(
    val id: Int,
    val user: String,
    val title: String,
    val date: Long,
    val content: String,
    val head: String
)
