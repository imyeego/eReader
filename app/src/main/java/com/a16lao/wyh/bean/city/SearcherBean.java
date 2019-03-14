package com.a16lao.wyh.bean.city;

/**
 * date:   2018/6/22 0022 上午 9:38
 * author: caoyan
 * description:
 */

public class SearcherBean {
    private String keyWord;

    public SearcherBean(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
