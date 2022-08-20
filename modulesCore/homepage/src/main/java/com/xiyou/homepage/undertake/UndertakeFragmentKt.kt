package com.xiyou.homepage.undertake

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView.OnEditorActionListener
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.xiyou.advance.modulespublic.common.net.CourseInfo
import com.xiyou.homepage.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "img"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UndertakeFragmentKt.newInstance] factory method to
 * create an instance of this fragment.
 */
class UndertakeFragmentKt : Fragment() {
    // TODO: Rename and change types of parameters
    private var imgStr: String? = null
    private var param2: String? = null
    private var img_undertake : ImageView? = null
    private var viewpager2_undertake : ViewPager2? = null
    private var tab_undertake : TabLayout? = null
    private var fab_fragment_undertake : FloatingActionButton? = null
    private var edit_fragment_undertake : EditText? = null
    private var tabs :Array<String> = arrayOf("简介","评论")
    private var edit_card_fragment : CardView? = null
    private var courseList : List<CourseInfo>? = null
    private var courseId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imgStr = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_undertake, container, false)
        myRequestPermissions()
        //fab_fragment_undertake = view.findViewById(R.id.fab_fragment_undertake);
        //fab_fragment_undertake = view.findViewById(R.id.fab_fragment_undertake);
        edit_card_fragment = view.findViewById(R.id.edit_card_fragment)
        edit_fragment_undertake = view.findViewById(R.id.edit_fragment_undertake)
        img_undertake = view.findViewById(R.id.img_undertake)
        val bundle = arguments
        imgStr = bundle!!.getString("img")
        //ToastUtil.INSTANCE.showToast(imgStr);
        //ToastUtil.INSTANCE.showToast(imgStr);
        Log.d(TAG, imgStr!!)
        Glide.with(requireContext())
            .load(imgStr)
            .placeholder(com.advance.modulespublic.common.R.drawable.img0) //图片加载出来前，显示的图片
            .error(com.advance.modulespublic.common.R.drawable.img_1) //图片加载失败后，显示的图片
            .into(img_undertake!!)
        viewpager2_undertake = view.findViewById(R.id.viewpager2_undertake)
        tab_undertake = view.findViewById(R.id.tab_undertake)
        val adapter_undertake = Adapter_Undertake(courseList, courseId)
        viewpager2_undertake!!.setAdapter(adapter_undertake)
        viewpager2_undertake!!.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        viewpager2_undertake!!.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 1) edit_card_fragment!!.setVisibility(View.VISIBLE) else edit_card_fragment!!.setVisibility(
                    View.INVISIBLE
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
        TabLayoutMediator(tab_undertake!!, viewpager2_undertake!!,
            TabConfigurationStrategy { tab, position -> tab.text = tabs[position] }).attach()
        edit_fragment_undertake!!.setOnEditorActionListener(OnEditorActionListener { textView, i, keyEvent ->
            Log.d(TAG, "onEditorAction")
            false
        })
        edit_fragment_undertake!!.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            Log.d(TAG, "onKey")
            false
        })
        edit_fragment_undertake!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.d(TAG, "beforeTextChanged")
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                Log.d(TAG, "onTextChanged")
            }

            override fun afterTextChanged(editable: Editable) {
                Log.d(TAG, "afterTextChanged")
            }
        })
        return view
    }


    private fun myRequestPermissions() {
        //ACCESS_FINE_LOCATION通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
        //ACCESS_FINE_LOCATION，通过GPS芯片接收卫星的定位信息，定位精度达10米以内

        //ACCESS_FINE_LOCATION通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
        //ACCESS_FINE_LOCATION，通过GPS芯片接收卫星的定位信息，定位精度达10米以内
        if ((ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WAKE_LOCK
            )
                    != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    != PackageManager.PERMISSION_GRANTED)
        ) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WAKE_LOCK,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                1
            )
        }
    }

    companion object {
        private var TAG:String = "UndertakeFragmentTAG"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UndertakeFragmentKt.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UndertakeFragmentKt().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}