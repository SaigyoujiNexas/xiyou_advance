package com.xiyou.community.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.xiyou.advance.modulespublic.common.utils.DialogUtil
import com.xiyou.advance.modulespublic.common.utils.ToastUtil
import com.xiyou.community.data.QuestionCard
import com.xiyou.community.databinding.FragmentQuestionInfoBinding
import com.xiyou.community.view.adapter.questionAnswer.QuestionAnswerAdapter
import com.xiyou.community.view.adapter.questionAnswer.QuestionAnswerDiffCallback
import com.xiyou.community.viewModel.QuestionInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuestionInfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class QuestionInfoFragment : Fragment() {

    private var _binding: FragmentQuestionInfoBinding? = null

    private lateinit var question: QuestionCard
    private val binding: FragmentQuestionInfoBinding
    get() = _binding!!

    private val viewModel: QuestionInfoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.
        let {
            question = it.getParcelable<QuestionCard>("question") as QuestionCard
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivQuestionInfoHead.load(question!!.head)
        binding.tvQuestionInfoName.text = question?.user
        binding.tvQuestionInfoTitle.text = question?.title
        binding.etQuestionInfoEdit.setText(viewModel.content?:"")
        binding.btSendRes.setOnClickListener {
            val dialog = context?.let { it1 -> DialogUtil.showLoading(it1,"?????????") }
            dialog?.show()
            viewModel.releaseAnswer(binding.etQuestionInfoEdit.text.toString()){
                dialog?.hide()
                ToastUtil.showToast("??????????????????????????????")
            }
        }
        binding.tvQuestionInfoContent.text = question.comment
        val manager = LinearLayoutManager(context)
        manager.isSmoothScrollbarEnabled = false
        manager.orientation = RecyclerView.VERTICAL
        binding.rvQuestionInfoAnswers.layoutManager = manager
        val adapter = QuestionAnswerAdapter(QuestionAnswerDiffCallback())
        binding.rvQuestionInfoAnswers.adapter = adapter
        adapter.submitList(
            question.answer.toList()
        )

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuestionInfoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuestionInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}