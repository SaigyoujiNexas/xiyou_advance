//package com.xiyou.homepage.undertake
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.GridView
//import android.widget.ImageView
//import android.widget.LinearLayout
//import android.widget.TextView
//import androidx.navigation.Navigation.findNavController
//import androidx.recyclerview.widget.RecyclerView
//import com.xiyou.advance.modulespublic.common.bean.BaseInfo
//import com.xiyou.advance.modulespublic.common.bean.ChapterInfo
//import com.xiyou.advance.modulespublic.common.bean.CourseInfo
//import com.xiyou.advance.modulespublic.common.bean.SectionInfo
//import com.xiyou.homepage.R
//
//class Adapter_ExpandRecyclerviewKt(_courseInfo: CourseInfo) : View.OnClickListener,RecyclerView.Adapter
//<RecyclerView.ViewHolder>() {
//    //传进来的课程信息
//    private val courseInfo: CourseInfo? = null
//
//    //显示的数据集
//    private val dataInfos: List<BaseInfo> = ArrayList()
//
//    //当前展开的课时，-1代表没有任何展开
//    private var curExpandChapterIndex = -1
//    private var chapterCount = 1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        TODO("Not yet implemented")
//        val itemView: View
//        return if (viewType == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER) {
//            itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_chapter_undertake, parent, false)
//            ChapterViewHolder(itemView)
//        } else {
//            itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_expand, parent, false)
//            ItemSectionHolder(itemView)
//
//        }
//    }
//
//    //
//    class ChapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
//        View.OnClickListener {
//        var llBg: LinearLayout? = null
//        var ivArrow: ImageView
//        var tvName: TextView
//        var tvNumber: TextView
//        var llSection: LinearLayout? = null
//        var gvSection: GridView? = null
//        init {
//            ivArrow = itemView.findViewById<View>(R.id.iv_item_chapter_arrow) as ImageView
//            tvName = itemView.findViewById<View>(R.id.tv_item_chapter_name) as TextView
//            tvNumber = itemView.findViewById<View>(R.id.tv_item_chapter_number) as TextView
//
//            //将创建的View注册点击事件
//            //itemView.setOnClickListener(this@Adapter_ExpandRecyclerviewKt)
//            itemView.setOnClickListener(this@ChapterViewHolder)
//            tvNumber.setOnClickListener(this@ChapterViewHolder)
//        }
//
//        override fun onClick(p0: View?) {
//            TODO("Not yet implemented")
//
//        }
//    }
////
//
//    //
//    class ItemSectionHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
//        View.OnClickListener {
//        var tvName: TextView
//        init {
//            tvName = itemView.findViewById<View>(R.id.title_item_expand) as TextView
//            //将创建的View注册点击事件
//            tvName.setOnClickListener(this@ItemSectionHolder)
//        }
//
//        override fun onClick(p0: View?) {
//            TODO("Not yet implemented")
//
//        }
//
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        //Timber.v("---onBindViewHolder---position = "+position);
//
//        //Timber.v("---onBindViewHolder---position = "+position);
//        Log.d(TAG, "onBindViewHolder0")
//        if (getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER) { //VIEW_TYPE_CHAPTER的onBindViewHolder
//            val itemHolder = holder as Adapter_ExpandRecyclerview.ChapterViewHolder
//            itemHolder.itemView.tag = position
//            val chapterInfo = dataInfos.get(position) as ChapterInfo
//            itemHolder.tvNumber.text = "第" + chapterCount++ + "章"
//            itemHolder.tvName.text = chapterInfo.title
//            if (chapterInfo.list.size > 0) {
//                itemHolder.ivArrow.visibility = View.VISIBLE
//                if (curExpandChapterIndex == position) {
//                    itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_up)
//                } else {
//                    itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_down)
//                }
//            } else {
//                itemHolder.ivArrow.visibility = View.INVISIBLE
//            }
//        } else { //VIEW_TYPE_SECTIONR的onBindViewHolder
//            val itemSectionHolder = holder as Adapter_ExpandRecyclerview.ItemSectionHolder
//            itemSectionHolder.tvName.tag = position
//            val sectionInfo = dataInfos[position] as SectionInfo
//            itemSectionHolder.tvName.text = sectionInfo.name
//        }
//    }
//
//
//    //该方法只更改itemView的部分信息，不全部刷新
//    override fun onBindViewHolder(
//        holder: RecyclerView.ViewHolder,
//        position: Int,
//        payloads: List<*>
//    ) {
//        Log.d(TAG, "onBindViewHolder1")
//        //Timber.v("---onBindViewHolder---payloads = "+payloads + ", "+position);
//        if (payloads.isEmpty()) {
//            super.onBindViewHolder(holder, position, payloads)
//        } else {
//            val str = payloads[0] as String
//            //更改view的tag
//            if (str == "change_position") {
//                if (getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER) {
//                    val itemHolder = holder as Adapter_ExpandRecyclerview.ChapterViewHolder
//                    itemHolder.itemView.tag = position
//                    itemHolder.tvNumber.tag = position
//                    //改变箭头方向
//                    if (curExpandChapterIndex == position) {
//                        itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_up)
//                    } else {
//                        itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_down)
//                    }
//                } else {
//                    val itemSectionHolder = holder as Adapter_ExpandRecyclerview.ItemSectionHolder
//                    itemSectionHolder.tvName.tag = position
//                }
//            }
//        }
//    }
//
//
//    override fun getItemId(i: Int): Long {
//        return i.toLong()
//    }
//
//    override fun getItemCount(): Int {
//        return dataInfos?.size ?: 0
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        if (dataInfos[position] is ChapterInfo) {
//            return Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER
//        } else if (dataInfos[position] is SectionInfo) {
//            return Adapter_ExpandRecyclerview.VIEW_TYPE_SECTION
//        }
//        return super.getItemViewType(position)
//    }
//    companion object{
//        const val TAG = "Adapter_UndertakeTAG"
//
//    }
//
////    以下为item点击处理///
//
//    //
//    //    以下为item点击处理///
//    private var mOnItemClickListener: OnRecyclerViewItemClickListener? = null
//
//
//    /** item里面有多个控件可以点击  */
//    enum class ViewName {
//        CHAPTER_ITEM, CHAPTER_ITEM_PRACTISE, SECTION_ITEM
//    }
//
//    interface OnRecyclerViewItemClickListener {
//        fun onClick(view: View?, viewName: ViewName?, chapterIndex: Int, sectionIndex: Int)
//    }
//
//    //
//    override fun onClick(v: View) {
//        if (mOnItemClickListener != null) {
//            //注意这里使用getTag方法获取数据
//            val position = v.tag as Int
//            var viewName = ViewName.CHAPTER_ITEM
//            var chapterIndex = -1
//            var sectionIndex = -1
//            if (getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_CHAPTER) {
//                val chapterInfo = dataInfos[position] as ChapterInfo
//                chapterIndex = chapterInfo.chapterIndex
//                Log.d(TAG, "" + chapterIndex)
//                sectionIndex = -1
//                if (v.id == R.id.tv_item_chapter_number) {
//                    viewName = ViewName.CHAPTER_ITEM_PRACTISE
//                } else {
//                    viewName = ViewName.CHAPTER_ITEM
//                    if (chapterInfo.list.size > 0) {
//                        if (chapterIndex == curExpandChapterIndex) {
//                            narrow(curExpandChapterIndex)
//                        } else {
//                            narrow(curExpandChapterIndex)
//                            expand(chapterIndex)
//                        }
//                    }
//                }
//            } else if (getItemViewType(position) == Adapter_ExpandRecyclerview.VIEW_TYPE_SECTION) {
//                val sectionInfo = dataInfos[position] as SectionInfo
//                viewName = ViewName.SECTION_ITEM
//                chapterIndex = sectionInfo.chapterIndex
//                sectionIndex = sectionInfo.sectionIndex
//                val controller = findNavController(v)
//                val bundle = Bundle()
//                bundle.putString("videoData", sectionInfo.url)
//                Log.d(TAG, "")
//                controller.navigate(R.id.action_undertake_to_web_video, bundle)
//            }
//            mOnItemClickListener!!.onClick(v, viewName, chapterIndex, sectionIndex)
//        }
//    }
//
//    /**
//     * 展开某个item
//     * @param chapterIndex
//     */
//    private fun expand(chapterIndex: Int) {
////        dataInfos.addAll(chapterIndex+1, courseInfo.list.get(chapterIndex).list);
////        curExpandChapterIndex = chapterIndex;
////        Log.v(TAG,"---expand---"+(chapterIndex+1)+", "+courseInfo.list.get(chapterIndex).list.size());
////        //增
////        notifyItemRangeInserted(chapterIndex+1, courseInfo.list.get(chapterIndex).list.size());
//
//        /*notifyItemRangeChanged(chapterIndex + 1 + courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size(),
//                getItemCount() - chapterIndex - 1, "change_position");*/
//        notifyItemRangeChanged(0, itemCount, "change_position")
//    }
//
//    /**
//     * 收起某个item
//     * @param chapterIndex
//     */
//    private fun narrow(chapterIndex: Int) {
//        if (chapterIndex != -1) {
//            val removeStart = chapterIndex + 1
//            var removeCount = 0
//            var i = removeStart
//            while (i < dataInfos.size && getItemViewType(i) == Adapter_ExpandRecyclerview.VIEW_TYPE_SECTION) {
//                removeCount++
//                i++
//            }
//            //            dataInfos.removeAll(courseInfo.list.get(chapterIndex).list);
//            curExpandChapterIndex = -1
//            Log.v(TAG, "---narrow---$removeStart, $removeCount")
//            notifyItemRangeRemoved(removeStart, removeCount)
//
//            //notifyItemRangeChanged(removeStart, getItemCount() - removeStart, "change_position");
//            notifyItemRangeChanged(0, itemCount, "change_position")
//        }
//    }
//}