package com.xiyou.community.view.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiyou.advance.modulespublic.common.constant.NetConstant
import com.xiyou.community.R
import com.xiyou.community.databinding.FragmentCommunityBinding
import com.xiyou.community.net.CommunityService
import com.xiyou.community.repository.CommunityRepository
import com.xiyou.community.view.adapter.questionCard.QuestionCardAdapter
import com.xiyou.community.view.adapter.questionCard.QuestionDiffCallback
import com.xiyou.community.viewModel.QuestionInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CommunityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//@AndroidEntryPoint
class CommunityFragment : Fragment() {

//    private val viewModel: QuestionInfoViewModel by viewModels()
    private lateinit var viewModel : QuestionInfoViewModel
    private lateinit var binding: FragmentCommunityBinding
    private lateinit var repository: CommunityRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this ,
            ViewModelProvider.NewInstanceFactory()).get(QuestionInfoViewModel::class.java)
        val service = NetConstant.retrofit.create(CommunityService::class.java)
        repository = CommunityRepository(service)
        viewModel.getAllQuestion2({},{})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentCommunityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabCommunityEdit.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_community_to_question_release))
        val rv = binding.rvCommunityQuestionCards
        rv.layoutManager = LinearLayoutManager(context)
        val adapter = QuestionCardAdapter(QuestionDiffCallback())
        rv.adapter = adapter
        viewModel.questions.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

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