package com.koolearn.kooreader.book;

public abstract class MultiSelectableBook {

    private boolean isSelected;
    private boolean isClicked;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }
}
