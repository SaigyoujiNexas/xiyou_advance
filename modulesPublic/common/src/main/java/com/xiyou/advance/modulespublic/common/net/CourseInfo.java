package com.xiyou.advance.modulespublic.common.net;

import java.util.ArrayList;
import java.util.List;

public class CourseInfo extends BaseInfo{
    public int id;
    public String title;
    public String cover;
    public List<ChapterInfo> list = new ArrayList<>();
}
