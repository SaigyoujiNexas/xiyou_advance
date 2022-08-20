package com.xiyou.homepage.pre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiyou.advance.modulespublic.common.net.CourseInfo
import com.xiyou.advance.modulespublic.common.net.GetRequest
import com.xiyou.advance.modulespublic.common.utils.ProgressUtil
import com.xiyou.homepage.R
import com.xiyou.homepage.viewModel.HomePageViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomePageFragmentKt : Fragment() {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    private val homepageRecyclerview: RecyclerView? = null

    // TODO: Rename and change types of parameters
    private val mParam1: String? = null
    private val mParam2: String? = null
    var newsList: MutableList<News_Homepage> = ArrayList()
    var recyclerView: RecyclerView? = null
    val TAG = "HomePageFragmentTAG"
    private var courseList: List<CourseInfo?>? = null
    private var viewModel: HomePageViewModel? = null
    fun HomePageFragment() {
        // Required empty public constructor
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //create the view model.
        viewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)
        initData()
    }

    private fun initData() {
        val news1 = News_Homepage(R.drawable.img1, "标题1")
        val news2 = News_Homepage(R.drawable.img1, "标题2")
        val news3 = News_Homepage(R.drawable.img1, "标题3")
        val news4 = News_Homepage(R.drawable.img1, "标题4")
        val news5 = News_Homepage(R.drawable.img1, "标题5")
        newsList.add(news1)
        newsList.add(news2)
        newsList.add(news3)
        newsList.add(news4)
        newsList.add(news5)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_homepage, container, false)
        recyclerView = view.findViewById(R.id.homepage_recyclerview)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView!!.setLayoutManager(linearLayoutManager)
        initRetrofit()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initList();
    }

    fun initList() {
        viewModel!!.getCourses({ null }) { o: Any ->
            val t = o.toString()
            Log.d(TAG, "error+$t")
            Toast.makeText(recyclerView!!.context, "访问失败，服务器出了问题", Toast.LENGTH_SHORT).show()
            null
        }
        //对 course 进行观察
        viewModel!!.courses.observe(
            viewLifecycleOwner
        ) { courseInfos -> //如果不为0
            if (courseInfos.size > 0) {
                val adapter_homepageRecycler = Adapter_HomepageRecycler(newsList, courseInfos)
                recyclerView!!.adapter = adapter_homepageRecycler
                adapter_homepageRecycler.notifyDataSetChanged()
            }
        }
    }

    @Deprecated("")
    fun initRetrofit() {
        ProgressUtil.showProgressDialog("正在加载", "正在加载... ", context)
        val retrofit = Retrofit.Builder().baseUrl("http://8.142.65.201:8080")
            .addConverterFactory(MoshiConverterFactory.create()).build()
        val getRequest = retrofit.create(GetRequest::class.java)
        val call = getRequest.courses
//        call.enqueue(object : Callback<List<CourseInfo?>> {
//            override fun onResponse(
//                call: Call<List<CourseInfo?>>,
//                response: Response<List<CourseInfo?>>
//            ) {
//                Log.d(
//                    TAG,
//                    "onresponsebody:" + response.body() + ",errorbody:" + response.errorBody() + ",message:" + response.message()
//                )
//                courseList = response.body()
//                val adapter_homepageRecycler = Adapter_HomepageRecycler(newsList, courseList)
//                recyclerView!!.adapter = adapter_homepageRecycler
//                adapter_homepageRecycler.notifyDataSetChanged()
//                ProgressUtil.hideProgressDialog()
//            }
//
//            override fun onFailure(call: Call<List<CourseInfo?>>, t: Throwable) {
//                Log.d(TAG, "error+$t")
//                Toast.makeText(recyclerView!!.context, "访问失败，服务器出了问题", Toast.LENGTH_SHORT).show()
//                ProgressUtil.hideProgressDialog()
//            }
//        })
    }
}