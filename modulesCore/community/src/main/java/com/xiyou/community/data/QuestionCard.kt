package com.xiyou.community.data

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class QuestionCard(
    val head: String,
    val title: String,
    val content: String,
    val date: Long,
    val user: String,
    val id: Int,
    val isSolved: Boolean,
    val answer: List<QuestionAnswer>
) :Parcelable


@Parcelize
data class QuestionAnswer(
    val head: String,
    val name: String,
    val id: Int,
    val date: Long,
    val content: String
): Parcelable