package com.xiyou.community.data.ui

import java.io.Serializable
data class QuestionCard(
    val head: String,
    val title: String,
    val content: String,
    val date: Long,
    val user: String,
    val id: Int,
    val isSolved: Boolean,
    val answer: List<QuestionAnswer>
) : Serializable

data class QuestionAnswer(
    val head: String,
    val name: String,
    val id: Int,
    val date: Long,
    val content: String
): Serializable