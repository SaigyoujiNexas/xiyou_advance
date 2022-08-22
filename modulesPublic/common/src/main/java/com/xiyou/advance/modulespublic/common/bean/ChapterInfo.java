package com.xiyou.advance.modulespublic.common.bean;

import java.util.ArrayList;
import java.util.List;

public class ChapterInfo extends BaseInfo{
    public int contentId;
    String title1;

    //String cover;
    public String name;
    public List<SectionInfo> list = new ArrayList<>();

    public int chapterIndex;
}
