package com.xiyou.advance.modulespublic.common.net;

import java.util.ArrayList;
import java.util.List;

public class ChapterInfo extends BaseInfo{
    String title;
    String url;
    public List<SectionInfo> sectionInfos = new ArrayList<>();

    public String name;
    public int chapterIndex;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SectionInfo> getSectionInfos() {
        return sectionInfos;
    }

    public void setSectionInfos(List<SectionInfo> sectionInfos) {
        this.sectionInfos = sectionInfos;
    }
}
