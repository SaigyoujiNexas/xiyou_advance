package com.xiyou.community.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xiyou.advance.modulespublic.common.net.BaseResponse
import com.xiyou.advance.modulespublic.common.utils.DialogUtil
import com.xiyou.advance.modulespublic.common.utils.ToastUtil
import com.xiyou.community.data.Answer
import com.xiyou.community.data.QuestionAnswer
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.data.QuestionRelease
import com.xiyou.community.repository.CommunityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionInfoViewModel
@Inject
    constructor(val repository: CommunityRepository)
    : ViewModel() {

    val list = mutableListOf<QuestionCard>()
    val answer  = mutableListOf<QuestionAnswer>()
    var title: String? = ""
    var content: String? = ""
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
            list += QuestionCard(
                id = 11,
                user = "silent碎月",
                title = "2016 年美国总统大选对你造成了怎样的改变？",
                content ="本次2016美国总统大选堪称21世纪乃至有史以来最重要的一次美国总统选举，双方候选人均极具争议性和话题性，其影响深远程度以及关注度之高可谓是空前绝后，本次大选结果也将直接决定人类文明的未来；在本次大选中爆发出来的诸多戏剧性事件和无数爆炸性重磅事实让许多人都开始重新评判自己以前对事物的认识和观点，动摇乃至改变了自己坚持多年的信念和选择；持续一年多的大选拉锯战逐渐走向高潮，它深远地影响和改变了参与者们，不仅仅包括每一个切身攸关的美国人，也包括知乎上的关注者们。那么，本次2016美国总统大选让你学到了什么？对你的观念和生活分别产生了怎么样的改变？",
                head="https://i1.hdslb.com/bfs/face/3eacf147bfe00ca8626198ac5eed886ee535bbee.jpg@96w_96h_1c_1s.webp",
                date = 1649943869,
                isSolved = true,
                answer = listOf(QuestionAnswer(content = "借用《Chicago》中“silver tongue”Billy Flynn\n" +
                        "\n" +
                        "\n" +
                        "在庭审前安慰杀了人的女士的金句——\n" +
                        "\n" +
                        "\n" +
                        "“It's all show business”", date=1649943869,
                    head ="https://pica.zhimg.com/v2-81aecab23a477d2de02f292077b7fec8_xs.jpg?source=1940ef5c",
                    name = "雨墨",
                    id = 12
                ))
            )
    }
    fun releaseQuestion(title: String, content: String, onSuccess: () -> Unit = {}) = viewModelScope.launch {
        val question = QuestionRelease(title = title, content = content)
        var res: BaseResponse<String?>?
        try{
            res = repository.releaseQuestion(question)
        }catch(e: Throwable)
        {
            res = BaseResponse(data = e.localizedMessage, code = 200, message = e.localizedMessage)
        }
        when(res!!.isSuccess)
        {
            true ->  onSuccess.invoke()
            false -> ToastUtil.showToast(res.msg)
        }
    }
    fun releaseAnswer(answer: String, onSuccess: () -> Unit) = viewModelScope.launch {
        val ans = Answer(answer)
        var res: BaseResponse<String?>?
        try{
            res = repository.releaseAnswer(ans)
        }catch (e: Throwable)
        {
            res = BaseResponse(data = e.localizedMessage, code = 200, message = e.localizedMessage)
        }
        when(res!!.isSuccess)
        {
            true -> onSuccess.invoke()
            false -> ToastUtil.showToast(res.msg)
        }
    }
}