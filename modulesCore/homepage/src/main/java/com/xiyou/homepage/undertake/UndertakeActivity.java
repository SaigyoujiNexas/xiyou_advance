//package com.xiyou.homepage.undertake;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.google.android.material.tabs.TabLayout;
//import com.google.android.material.tabs.TabLayoutMediator;
//import com.xiyou.homepage.R;
//
//public class UndertakeActivity extends AppCompatActivity {
//    ImageView img_undertake;
//    ViewPager2 viewpager2_undertake;
//    TabLayout tab_undertake;
//    final String[] tabs = {"简介","评论"};
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_undertake);
//        img_undertake = findViewById(R.id.img_undertake);
//        viewpager2_undertake = findViewById(R.id.viewpager2_undertake);
//        tab_undertake = findViewById(R.id.tab_undertake);
//        Adapter_Undertake adapter_undertake  = new Adapter_Undertake();
//        viewpager2_undertake.setAdapter(adapter_undertake);
//        viewpager2_undertake.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
//        viewpager2_undertake.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });
//        new TabLayoutMediator(tab_undertake, viewpager2_undertake, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(tabs[position]);
//            }
//        }).attach();
//    }
//
//}