package com.xiyou.community.view.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
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

    private lateinit var binding: FragmentCommunityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        list = mutableListOf()
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
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val v = inflater.inflate(R.layout.fragment_community, container, false)

        binding = FragmentCommunityBinding.bind(v)
        // Inflate the layout for this fragment
        return v
    }

    override fun onResume() {
        super.onResume()
        val rv = binding.rvCommunityQuestionCards
        rv.layoutManager = LinearLayoutManager(context)
        val adapter = QuestionCardAdapter(QuestionDiffCallback())
        rv.adapter = adapter
        adapter.submitList(list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabCommunityEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_community_to_question_release))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_community, menu)
        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            (menu.findItem(R.id.search_community).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
                setIconifiedByDefault(true)
            }

    }

    override fun onDestroy() {
        super.onDestroy()
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
            CommunityFragment()
    }

}