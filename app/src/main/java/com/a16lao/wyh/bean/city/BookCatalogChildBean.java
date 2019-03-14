package com.a16lao.wyh.bean.city;

import java.util.Observable;
import java.util.Observer;

/**
 * date:   2018/7/23 0023 上午 9:44
 * author: caoyan
 * description:
 */

public class BookCatalogChildBean extends Observable implements Observer {
    private String childName;
    private boolean isSelected;

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public void changeChecked(){
        isSelected=!isSelected;
        setChanged();
        notifyObservers();
    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Boolean) {
            isSelected = (Boolean) arg;
        }
    }
}
