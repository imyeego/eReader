package com.a16lao.wyh.bean.city;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * date:   2018/7/23 0023 上午 9:42
 * author: caoyan
 * description:
 */

public class BookCatalogGroupBean extends Observable implements Observer{
    private String groupName;
    private List<BookCatalogChildBean> childs;
    private boolean isSelected;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<BookCatalogChildBean> getChilds() {
        return childs;
    }

    public void setChilds(List<BookCatalogChildBean> childs) {
        this.childs = childs;
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
        notifyObservers(isSelected);
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean flag=true;
        for (BookCatalogChildBean child: childs) {
            if(!child.isSelected()){
                flag=false;
            }
        }
        isSelected=flag;
    }
}
