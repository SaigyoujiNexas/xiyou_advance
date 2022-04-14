package com.xiyou.homepage.undertake;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.advance.modulespublic.common.net.BaseInfo;
import com.xiyou.advance.modulespublic.common.net.ChapterInfo;
import com.xiyou.advance.modulespublic.common.net.CourseInfo;
import com.xiyou.advance.modulespublic.common.net.SectionInfo;
import com.xiyou.homepage.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ExpandRecyclerview extends RecyclerView.Adapter implements View.OnClickListener{
    final String TAG = "Adapter_UndertakeTAG";

    public static final int VIEW_TYPE_CHAPTER = 1;
    public static final int VIEW_TYPE_SECTION = 2;

    //传进来的课程信息
    private CourseInfo courseInfo;

    //显示的数据集
    private List<BaseInfo> dataInfos = new ArrayList<>();
    //当前展开的课时，-1代表没有任何展开
    private int curExpandChapterIndex = -1;

    public Adapter_ExpandRecyclerview(CourseInfo _courseInfo) {
        this.courseInfo = _courseInfo;
        for(BaseInfo info : courseInfo.chapterInfos){
            dataInfos.add(info);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if(viewType == VIEW_TYPE_CHAPTER){
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chapter_undertake, parent, false);
            return new ChapterViewHolder(itemView);
        }else{
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_expand, parent, false);
            return new ItemSectionHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Timber.v("---onBindViewHolder---position = "+position);
        Log.d(TAG,"onBindViewHolder0");
        if(getItemViewType(position) == VIEW_TYPE_CHAPTER){//VIEW_TYPE_CHAPTER的onBindViewHolder
            ChapterViewHolder itemHolder = (ChapterViewHolder) holder;
            itemHolder.itemView.setTag(position);
            ChapterInfo chapterInfo = (ChapterInfo) dataInfos.get(position);
            if(chapterInfo.sectionInfos.size() > 0){
                itemHolder.ivArrow.setVisibility(View.VISIBLE);
                if(curExpandChapterIndex == position){
                    itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_up);
                }else{
                    itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_down);
                }
            }else{
                itemHolder.ivArrow.setVisibility(View.INVISIBLE);
            }
        }else{//VIEW_TYPE_SECTIONR的onBindViewHolder
            ItemSectionHolder itemSectionHolder = (ItemSectionHolder) holder;
            itemSectionHolder.tvName.setTag(position);
            SectionInfo sectionInfo = (SectionInfo) dataInfos.get(position);
            itemSectionHolder.tvName.setText(sectionInfo.name);
        }
    }


    //该方法只更改itemView的部分信息，不全部刷新
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        Log.d(TAG,"onBindViewHolder1");
        //Timber.v("---onBindViewHolder---payloads = "+payloads + ", "+position);
        if(payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads);
        }else{
            String str = (String) payloads.get(0);
            //更改view的tag
            if(str.equals("change_position")){
                if(getItemViewType(position) == VIEW_TYPE_CHAPTER){
                    ChapterViewHolder itemHolder = (ChapterViewHolder) holder;
                    itemHolder.itemView.setTag(position);
                    itemHolder.tvPractise.setTag(position);
                    //改变箭头方向
                    if(curExpandChapterIndex == position){
                        itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_up);
                    }else{
                        itemHolder.ivArrow.setBackgroundResource(com.advance.modulespublic.common.R.drawable.arrow_down);
                    }
                }else{
                    ItemSectionHolder itemSectionHolder = (ItemSectionHolder) holder;
                    itemSectionHolder.tvName.setTag(position);
                }
            }
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        if(dataInfos == null){
            return 0;
        }else{
            return dataInfos.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(dataInfos.get(position) instanceof ChapterInfo){
            return VIEW_TYPE_CHAPTER;
        }else if(dataInfos.get(position) instanceof SectionInfo){
            return VIEW_TYPE_SECTION;
        }
        return super.getItemViewType(position);
    }
//
    public class ChapterViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout llBg;
        public ImageView ivArrow;
        public TextView tvName;
        public TextView tvPractise;
        public LinearLayout llSection;
        public GridView gvSection;

        public ChapterViewHolder(View itemView) {
            super(itemView);
            ivArrow = (ImageView) itemView.findViewById(R.id.iv_item_chapter_arrow);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_chapter_name);
            tvPractise = (TextView) itemView.findViewById(R.id.tv_item_chapter_number);

            //将创建的View注册点击事件
            itemView.setOnClickListener(Adapter_ExpandRecyclerview.this);
            tvPractise.setOnClickListener(Adapter_ExpandRecyclerview.this);
        }
    }
//

    public class ItemSectionHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ItemSectionHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.title_item_expand);

            //将创建的View注册点击事件
            tvName.setOnClickListener(Adapter_ExpandRecyclerview.this);
        }
    }
//
//    以下为item点击处理///

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /** item里面有多个控件可以点击 */
    public enum ViewName {
        CHAPTER_ITEM,
        CHAPTER_ITEM_PRACTISE,
        SECTION_ITEM
    }

    public interface OnRecyclerViewItemClickListener {
        void onClick(View view, ViewName viewName, int chapterIndex, int sectionIndex);
    }
//
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            int position = (int) v.getTag();
            ViewName viewName = ViewName.CHAPTER_ITEM;
            int chapterIndex = -1;
            int sectionIndex = -1;
            if(getItemViewType(position) == VIEW_TYPE_CHAPTER){
                ChapterInfo chapterInfo = (ChapterInfo) dataInfos.get(position);
                chapterIndex = chapterInfo.chapterIndex;
                sectionIndex = -1;
                if(v.getId() == R.id.tv_item_chapter_number){
                    viewName = ViewName.CHAPTER_ITEM_PRACTISE;
                }else{
                    viewName = ViewName.CHAPTER_ITEM;
                    if(chapterInfo.sectionInfos.size() > 0){
                        if(chapterIndex == curExpandChapterIndex){
                            narrow(curExpandChapterIndex);
                        }else{
                            narrow(curExpandChapterIndex);
                            expand(chapterIndex);
                        }
                    }
                }
            }else if(getItemViewType(position) == VIEW_TYPE_SECTION){
                SectionInfo sectionInfo = (SectionInfo) dataInfos.get(position);
                viewName = ViewName.SECTION_ITEM;
                chapterIndex = sectionInfo.chapterIndex;
                sectionIndex = sectionInfo.sectionIndex;
            }
            mOnItemClickListener.onClick(v, viewName, chapterIndex, sectionIndex);
        }
    }

    /**
     * 展开某个item
     * @param chapterIndex
     */
    private void expand(int chapterIndex){
        dataInfos.addAll(chapterIndex+1, courseInfo.chapterInfos.get(chapterIndex).sectionInfos);
        curExpandChapterIndex = chapterIndex;
        Log.v(TAG,"---expand---"+(chapterIndex+1)+", "+courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size());
        //增
        notifyItemRangeInserted(chapterIndex+1, courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size());

        /*notifyItemRangeChanged(chapterIndex + 1 + courseInfo.chapterInfos.get(chapterIndex).sectionInfos.size(),
                getItemCount() - chapterIndex - 1, "change_position");*/
        notifyItemRangeChanged(0, getItemCount(), "change_position");
    }

    /**
     * 收起某个item
     * @param chapterIndex
     */
    private void narrow(int chapterIndex){
        if(chapterIndex != -1){
            int removeStart = chapterIndex + 1;
            int removeCount = 0;
            for(int i=removeStart; i<dataInfos.size() && getItemViewType(i) == VIEW_TYPE_SECTION; i++){
                removeCount++;
            }
            dataInfos.removeAll(courseInfo.chapterInfos.get(chapterIndex).sectionInfos);
            curExpandChapterIndex = -1;
            Log.v(TAG,"---narrow---"+removeStart+", "+removeCount);
            notifyItemRangeRemoved(removeStart, removeCount);

            //notifyItemRangeChanged(removeStart, getItemCount() - removeStart, "change_position");
            notifyItemRangeChanged(0, getItemCount(), "change_position");
        }
    }
}
