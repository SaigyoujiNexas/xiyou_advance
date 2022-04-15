package com.xiyou.advance.modulespublic.common.net;

import java.util.ArrayList;
import java.util.List;

public class ChapterInfo extends BaseInfo{
    String title;
    public List<SectionInfo> list = new ArrayList<>();

    public String name;
    public int chapterIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<SectionInfo> getList() {
        return list;
    }

    public void setList(List<SectionInfo> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }
}
