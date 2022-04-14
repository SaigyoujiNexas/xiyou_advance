package com.xiyou.homepage.undertake;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.xiyou.advance.modulespublic.common.net.CourseInfo;
import com.xiyou.homepage.R;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class UndertakeFragment extends Fragment {

    ImageView img_undertake;
    ViewPager2 viewpager2_undertake;
    TabLayout tab_undertake;
    //FloatingActionButton fab_fragment_undertake;
    EditText edit_fragment_undertake;
    final String[] tabs = {"简介","评论"};
    final String TAG = "UndertakeFragmentTAG";
    CardView edit_card_fragment;
    private List<CourseInfo> courseList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_undertake,container,false);
        myRequestPermissions();
        //fab_fragment_undertake = view.findViewById(R.id.fab_fragment_undertake);
        edit_card_fragment = view.findViewById(R.id.edit_card_fragment);
        edit_fragment_undertake = view.findViewById(R.id.edit_fragment_undertake);
        img_undertake = view.findViewById(R.id.img_undertake);
        viewpager2_undertake = view.findViewById(R.id.viewpager2_undertake);
        tab_undertake = view.findViewById(R.id.tab_undertake);
        Adapter_Undertake adapter_undertake  = new Adapter_Undertake(courseList);
        viewpager2_undertake.setAdapter(adapter_undertake);
        viewpager2_undertake.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewpager2_undertake.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 1 )       edit_card_fragment.setVisibility(View.VISIBLE);
                else edit_card_fragment.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        new TabLayoutMediator(tab_undertake, viewpager2_undertake, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();
        edit_fragment_undertake.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d(TAG,"onEditorAction");
                return false;
            }
        });
        edit_fragment_undertake.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(TAG,"onKey");
                return false;
            }
        });
        edit_fragment_undertake.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG,"beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG,"onTextChanged");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG,"afterTextChanged");
            }
        });
        return view;
    }

    private void myRequestPermissions(){
        //ACCESS_FINE_LOCATION通过WiFi或移动基站的方式获取用户错略的经纬度信息，定位精度大概误差在30~1500米
        //ACCESS_FINE_LOCATION，通过GPS芯片接收卫星的定位信息，定位精度达10米以内
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WAKE_LOCK)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.WAKE_LOCK,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

    }

}