//package com.xiyou.homepage.undertake
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.xiyou.advance.modulespublic.common.net.CourseInfo
//import com.xiyou.homepage.R
//
//class Adapter_UndertakeKt() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    var list : List<CourseInfo>? = null
//    constructor(list:List<CourseInfo>) : this() {
//        this.list = list
//    }
//
//    enum class ItemType {
//        ITEM0, ITEM1
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        if (viewType == Adapter_Undertake.ItemType.ITEM0.ordinal) {
//            return Viewholder0_Expand_Undertake(
//                LayoutInflater.from(parent.context)
//                    .inflate(R.layout.viewholder0_undertake, parent, false), parent.context
//            )
//        } else if (viewType == Adapter_Undertake.ItemType.ITEM1.ordinal) {
//            return Viewholder1_Expand_Undertake(
//                LayoutInflater.from(parent.context)
//                    .inflate(R.layout.viewholder1_undertake, parent, false), parent.context
//            )
//        }
//        return Viewholder1_Expand_Undertake(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.viewholder1_undertake, parent, false), parent.context
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is Viewholder0_Expand_Undertake) {
//            holder.initRetrofit()
//        } else if (holder is Viewholder1_Expand_Undertake) {
//            holder.initRetrofit()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return 2
//    }
//
//
//    override fun getItemViewType(position: Int): Int {
//        if (position == 0) {
//            return Adapter_Undertake.ItemType.ITEM0.ordinal //获取某个枚举对象的位置索引值
//        } else if (position == 1) {
//            return Adapter_Undertake.ItemType.ITEM1.ordinal
//        }
//        return 0
//    }
//}