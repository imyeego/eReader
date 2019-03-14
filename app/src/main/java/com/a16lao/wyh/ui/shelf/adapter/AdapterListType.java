package com.a16lao.wyh.ui.shelf.adapter;

import com.koolearn.kooreader.book.MultiSelectableBook;

import java.util.List;

public interface AdapterListType<T extends MultiSelectableBook> {
    List<T> getList();
    void notifyDataChanged();
}
