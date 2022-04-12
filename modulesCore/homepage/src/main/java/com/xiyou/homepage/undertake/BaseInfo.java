package com.xiyou.homepage.undertake;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseInfo implements Serializable {

}

class CourseInfo extends BaseInfo {
    public int id;
    public String name;

    public List<ChapterInfo> chapterInfos = new ArrayList<>();
}

class ChapterInfo extends BaseInfo {

    public String name;
    public int chapterIndex;

    public List<SectionInfo> sectionInfos = new ArrayList<>();
}

class SectionInfo extends BaseInfo {

    public String name;
    public int chapterIndex;
    public int sectionIndex;
}