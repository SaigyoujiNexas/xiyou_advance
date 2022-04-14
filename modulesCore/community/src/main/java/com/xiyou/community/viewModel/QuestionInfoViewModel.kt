package com.xiyou.community.viewModel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.xiyou.community.data.ui.QuestionAnswer
import com.xiyou.community.data.ui.QuestionCard
import com.xiyou.community.repository.CommunityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionInfoViewModel
@Inject
    constructor(val repository: CommunityRepository)
    : ViewModel() {

    val list = mutableListOf<QuestionCard>()
    val answer  = mutableListOf<QuestionAnswer>();
    init {
        for (i in 0 until 5) {
            answer += QuestionAnswer(
                id = i,
                date = 1649668243L,
                content = "Item $i",
                name = "Temp",
                head = "https://i1.hdslb.com/bfs/face/130f111812e082c13d7c8b2232231a122957aa20.jpg@240w_240h_1c_1s.webp"
            )
        }
        for (i in 0 until 10) {
            list += QuestionCard(
                id = i,
                user = "Jack Gram",
                title = "Hello, World",
                content = "Use this factory method to create a new instance of\n" +
                        "* this fragment using the provided parameters.\n" +
                        "*\n" +
                        "* @param param1 Parameter 1.\n" +
                        "* @param param2 Parameter 2.\n" +
                        "* @return A new instance of fragment CommunityFragment.",
                head = "https://i1.hdslb.com/bfs/face/130f111812e082c13d7c8b2232231a122957aa20.jpg@240w_240h_1c_1s.webp",
                date = 1649668243L,
                isSolved = i % 2 != 0,
                answer = answer
            )
        }
    }

}