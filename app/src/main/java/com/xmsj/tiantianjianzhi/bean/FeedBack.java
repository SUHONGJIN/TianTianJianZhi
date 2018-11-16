package com.xmsj.tiantianjianzhi.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by SuHongJin on 2018/7/21.
 */

public class FeedBack extends BmobObject {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
