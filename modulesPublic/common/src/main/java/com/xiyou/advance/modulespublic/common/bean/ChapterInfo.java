package com.xiyou.advance.modulespublic.common.bean;

import java.util.ArrayList;
import java.util.List;

public class ChapterInfo extends BaseInfo{
    public int contentId;
    public String title1;

    //String cover;
    public String name;
    public List<SectionInfo> sectionList = new ArrayList<>();

    public int chapterIndex;
}
