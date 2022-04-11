package com.xiyou.community.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiyou.community.R
import com.xiyou.community.data.net.QuestionData
import com.xiyou.community.databinding.FragmentCommunityBinding
import com.xiyou.community.view.adapter.questionCard.QuestionCardAdapter
import com.xiyou.community.view.adapter.questionCard.QuestionDiffCallback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommunityFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var list: List<QuestionData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        list = mutableListOf<QuestionData>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_community, container, false)
        // Inflate the layout for this fragment
        for(i in 0 until 10)
        {
            list += QuestionData(id = i, user = "Jack Gram", title = "Hello, World"
                , content = "Use this factory method to create a new instance of\n" +
                        "* this fragment using the provided parameters.\n" +
                        "*\n" +
                        "* @param param1 Parameter 1.\n" +
                        "* @param param2 Parameter 2.\n" +
                        "* @return A new instance of fragment CommunityFragment.",
                head = "https://i1.hdslb.com/bfs/face/130f111812e082c13d7c8b2232231a122957aa20.jpg@240w_240h_1c_1s.webp",
                date = 1649668243
            )
        }
        val binding = FragmentCommunityBinding.bind(v)
        val rv = binding.rvCommunityQuestionCards
        rv.layoutManager = LinearLayoutManager(context)
        val adapter = QuestionCardAdapter(QuestionDiffCallback())
        rv.adapter = adapter
        adapter.submitList(list)
        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommunityFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CommunityFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}