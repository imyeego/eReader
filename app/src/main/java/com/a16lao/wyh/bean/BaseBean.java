package com.a16lao.wyh.bean;

/**
 * date:   2018/5/15 0015 下午 3:56
 * author: caoyan
 * description:
 */

public class BaseBean {
    private String token;
    private long time;
    private int status;
    private String results;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
