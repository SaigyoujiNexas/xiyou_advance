package com.xiyou.homepage.pre;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.advance.modulespublic.common.net.CourseInfo;
import com.xiyou.advance.modulespublic.common.net.GetRequest;
import com.xiyou.advance.modulespublic.common.utils.ProgressUtil;
import com.xiyou.homepage.R;
import com.xiyou.homepage.viewModel.HomePageViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
public class HomePageFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView homepageRecyclerview;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<News_Homepage> newsList = new ArrayList<>();
    RecyclerView recyclerView;
    final String TAG = "HomePageFragmentTAG";
    private List<CourseInfo> courseList;
    private HomePageViewModel viewModel;
    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create the view model.
        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
        initData();
    }
    private void initData(){
        News_Homepage news1 = new News_Homepage(R.drawable.img1,"标题1");
        News_Homepage news2 = new News_Homepage(R.drawable.img1,"标题2");
        News_Homepage news3 = new News_Homepage(R.drawable.img1,"标题3");
        News_Homepage news4 = new News_Homepage(R.drawable.img1,"标题4");
        News_Homepage news5 = new News_Homepage(R.drawable.img1,"标题5");
        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        newsList.add(news5);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage,container,false);
        recyclerView = view.findViewById(R.id.homepage_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        initRetrofit();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //initList();

    }
    public void initList(){
        viewModel.getCourses(
                new Function0() {
                    @Override
                    public Object invoke() {

                        //onSuccess invoke, must return 0
                        return null;
                    }
                },
                new Function1() {
                    @Override
                    public Object invoke(Object o) {
                        var t = o.toString();
                        Log.d(TAG, "error+" + t);
                        Toast.makeText(recyclerView.getContext(), "访问失败，服务器出了问题", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                }
        );
        //对 course 进行观察
        viewModel.getCourses().observe(getViewLifecycleOwner(), new Observer<List<CourseInfo>>() {
            @Override
            public void onChanged(List<CourseInfo> courseInfos) {
                //如果不为0
                if(courseInfos.size() >0) {
                    Adapter_HomepageRecycler adapter_homepageRecycler = new Adapter_HomepageRecycler(newsList, courseInfos);
                    recyclerView.setAdapter(adapter_homepageRecycler);
                    adapter_homepageRecycler.notifyDataSetChanged();
                }
            }
        });
    }

    @Deprecated
    public void initRetrofit(){
        ProgressUtil.showProgressDialog("正在加载","正在加载... ",getContext());
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://8.142.65.201:8080").addConverterFactory(MoshiConverterFactory.create()).build();
        GetRequest getRequest = retrofit.create(GetRequest.class);
        Call<List<CourseInfo>> call = getRequest.getCourses();
        call.enqueue(new Callback<List<CourseInfo>>() {
            @Override
            public void onResponse(Call<List<CourseInfo>> call, Response<List<CourseInfo>> response) {
                Log.d(TAG,"onresponsebody:"+response.body()+",errorbody:"+response.errorBody()+",message:"+response.message());
                courseList = response.body();
                Adapter_HomepageRecycler adapter_homepageRecycler = new Adapter_HomepageRecycler(newsList, courseList);
                recyclerView.setAdapter(adapter_homepageRecycler);
                adapter_homepageRecycler.notifyDataSetChanged();
                ProgressUtil.hideProgressDialog();
            }

            @Override
            public void onFailure(Call<List<CourseInfo>> call, Throwable t) {
                Log.d(TAG,"error+"+t.toString());
                Toast.makeText(recyclerView.getContext(), "访问失败，服务器出了问题", Toast.LENGTH_SHORT).show();
                ProgressUtil.hideProgressDialog();
            }
        });
    }
}