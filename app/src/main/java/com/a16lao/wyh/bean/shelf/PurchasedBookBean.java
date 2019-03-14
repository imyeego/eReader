package com.a16lao.wyh.bean.shelf;

public class PurchasedBookBean {

    private String imgUrl;
    private String bookName;
    private String isFinished;
    private String tag;
    private String progress;
    private boolean isAddedShelf;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public boolean isAddedShelf() {
        return isAddedShelf;
    }

    public void setAddedShelf(boolean addedShelf) {
        isAddedShelf = addedShelf;
    }
}
