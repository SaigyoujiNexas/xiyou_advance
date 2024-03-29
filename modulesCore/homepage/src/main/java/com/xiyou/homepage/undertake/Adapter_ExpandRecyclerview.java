package com.xiyou.homepage.undertake;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.xiyou.advance.modulespublic.common.bean.BaseInfo;
import com.xiyou.advance.modulespublic.common.bean.ChapterInfo;
import com.xiyou.advance.modulespublic.common.bean.CourseInfo;
import com.xiyou.advance.modulespublic.common.bean.SectionInfo;
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

    private int curExpandChapterIndex = -1; //当前展开的课时，-1代表没有任何展开
    private int chapterCount = 1;
    public Adapter_ExpandRecyclerview(CourseInfo _courseInfo,List<ChapterInfo> dataInfos) {
        this.courseInfo = _courseInfo;
        Log.d(TAG,dataInfos.size()+"");
        for(BaseInfo info : dataInfos){
            this.dataInfos.add(info);
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
            itemHolder.tvNumber.setText("第"+chapterCount+++"章");
            itemHolder.tvName.setText(chapterInfo.title1);
            if(chapterInfo.sectionList.size() > 0){
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
            itemSectionHolder.tvName.setText(sectionInfo.url);
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
                    itemHolder.tvNumber.setTag(position);
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
        public TextView tvNumber;
        public LinearLayout llSection;
        public GridView gvSection;

        public ChapterViewHolder(View itemView) {
            super(itemView);
            ivArrow = (ImageView) itemView.findViewById(R.id.iv_item_chapter_arrow);
            tvName = (TextView) itemView.findViewById(R.id.tv_item_chapter_name);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_item_chapter_number);

            //将创建的View注册点击事件
            itemView.setOnClickListener(Adapter_ExpandRecyclerview.this);
            tvNumber.setOnClickListener(Adapter_ExpandRecyclerview.this);
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
                Log.d(TAG,""+chapterIndex);
                sectionIndex = -1;
                if(v.getId() == R.id.tv_item_chapter_number){
                    viewName = ViewName.CHAPTER_ITEM_PRACTISE;
                    Log.d(TAG,"1,");
                }else{
                    viewName = ViewName.CHAPTER_ITEM;
                    //if(chapterInfo.list.size() > 0){
                    if(chapterInfo.sectionList.size() > 0){
                        if(chapterIndex == curExpandChapterIndex){
                            narrow(curExpandChapterIndex);
                            Log.d(TAG,"2,"+chapterIndex+curExpandChapterIndex);
                        }else{
                            narrow(curExpandChapterIndex);
                            expand(chapterIndex);
                            Log.d(TAG,"3,"+chapterIndex+curExpandChapterIndex);
                        }
                    }
                }
            }else if(getItemViewType(position) == VIEW_TYPE_SECTION){
                SectionInfo sectionInfo = (SectionInfo) dataInfos.get(position);
                viewName = ViewName.SECTION_ITEM;
                chapterIndex = sectionInfo.chapterIndex;
                sectionIndex = sectionInfo.sectionIndex;
                NavController controller= Navigation.findNavController(v);
                Bundle bundle=new Bundle();
                bundle.putString("videoData",sectionInfo.url);
                Log.d(TAG,"4,"+sectionIndex+chapterIndex);
                controller.navigate(R.id.action_undertake_to_web_video,bundle);
            }
            mOnItemClickListener.onClick(v, viewName, chapterIndex, sectionIndex);
        }
    }

    /**
     * 展开某个item
     * @param chapterIndex
     */
    private void expand(int chapterIndex){
//        dataInfos.addAll(chapterIndex+1, courseInfo.list.get(chapterIndex).list);
//        curExpandChapterIndex = chapterIndex;
//        Log.v(TAG,"---expand---"+(chapterIndex+1)+", "+courseInfo.list.get(chapterIndex).list.size());
//        //增
//        notifyItemRangeInserted(chapterIndex+1, courseInfo.list.get(chapterIndex).list.size());
        ChapterInfo chapterInfo = (ChapterInfo) dataInfos.get(chapterIndex);
        List<SectionInfo> sectionList = chapterInfo.sectionList;
        dataInfos.removeAll(sectionList);
        dataInfos.addAll(chapterIndex+1,sectionList);
        curExpandChapterIndex = chapterIndex;
        Log.v(TAG,"---expand---"+(chapterIndex+1)+", "+sectionList.size());
        //增
        notifyItemRangeInserted(chapterIndex+1, sectionList.size());

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
            ChapterInfo chapterInfo = (ChapterInfo) dataInfos.get(chapterIndex);
            List<SectionInfo> sectionList = chapterInfo.sectionList;
            dataInfos.removeAll(sectionList);
            curExpandChapterIndex = -1;
            Log.v(TAG,"---narrow---"+removeStart+", "+removeCount);
            notifyItemRangeRemoved(removeStart, removeCount);

            //notifyItemRangeChanged(removeStart, getItemCount() - removeStart, "change_position");
            notifyItemRangeChanged(0, getItemCount(), "change_position");
        }
    }
}
