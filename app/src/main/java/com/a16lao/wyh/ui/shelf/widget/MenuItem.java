package com.a16lao.wyh.ui.shelf.widget;

public class MenuItem {
    private String id;
    private int icon;
    private String text;

    public MenuItem(String text) {
        this.text = text;
    }

    public MenuItem(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
