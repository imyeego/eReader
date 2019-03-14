package com.a16lao.wyh.ui.shelf.adapter;


import android.content.Context;
import android.os.AsyncTask;

import android.widget.ImageButton;



import com.a16lao.wyh.R;
import com.a16lao.wyh.base.SimpleBaseAdapter;
import com.a16lao.wyh.base.SimpleBaseViewHolder;
import com.a16lao.wyh.bean.shelf.BookList;
import com.a16lao.wyh.ui.shelf.widget.DragGridListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * date:   2018/5/21 0021 下午 3:47
 * author: caoyan
 * description:
 */

public class ShelfAdapter extends SimpleBaseAdapter<BookList> implements DragGridListener {
    private int mHidePosition = -1;

    public ShelfAdapter(Context mContext,List<BookList> list) {
        super(mContext, R.layout.book_shelf_item,list);
    }




    @Override
    protected void bindView(SimpleBaseViewHolder holder, BookList bookList, int position) {
        holder.setImageResource(R.id.icon_close, bookList.getImage());
        holder.setText(R.id.tv_name, bookList.getBookname());
        if (mShelfCallBack != null) {
            mShelfCallBack.onShelfCallBack( holder.getViewById(R.id.icon_close));
        }
    }

    protected List<AsyncTask<Void, Void, Boolean>> myAsyncTasks = new ArrayList<>();


    ShelfCallBack mShelfCallBack;

    public interface ShelfCallBack {
        void onShelfCallBack(ImageButton imageButton);
    }

    public void setonShelfCallBack(ShelfCallBack mShelfCallBack) {
        this.mShelfCallBack = mShelfCallBack;
    }


    /**
     * Drag移动时item交换数据,并在数据库中更新交换后的位置数据
     *
     * @param oldPosition
     * @param newPosition
     */
    @Override
    public void reorderItems(int oldPosition, int newPosition) {

        BookList temp = mList.get(oldPosition);
        List<BookList> bookLists = new ArrayList<>();
//        bookLists = DataSupport.findAll(BookList.class);

        int tempId = bookLists.get(newPosition).getId();

        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                //获得交换前的ID,必须是数据库的真正的ID，如果使用bilist获取id是错误的，因为bilist交换后id是跟着交换的
                List<BookList> bookList = new ArrayList<>();
//                bookLists = DataSupport.findAll(BookList.class);
                int dataBasesId = bookList.get(i).getId();
                Collections.swap(mList, i, i + 1);

//                updateBookPosition(i, dataBasesId, mList);

            }
        } else if (oldPosition > newPosition) {
            for (int i = oldPosition; i > newPosition; i--) {
                List<BookList> bookList = new ArrayList<>();
//                bookLists = DataSupport.findAll(BookList.class);
                int dataBasesId = bookList.get(i).getId();

                Collections.swap(mList, i, i - 1);

//                updateBookPosition(i, dataBasesId, mList);

            }
        }

        mList.set(newPosition, temp);
//        updateBookPosition(newPosition, tempId, mList);

    }

    /**
     * 两个item数据交换结束后，把不需要再交换的item更新到数据库中
     *
     * @param position
     * @param bookLists
     */
    public void updateBookPosition(int position, int databaseId, List<BookList> bookLists) {
        BookList bookList = new BookList();
        String bookPath = bookLists.get(position).getBookpath();
        String bookName = bookLists.get(position).getBookname();
        bookList.setBookpath(bookPath);
        bookList.setBookname(bookName);
        bookList.setBegin(bookLists.get(position).getBegin());
        bookList.setCharset(bookLists.get(position).getCharset());
        //开线程保存改动的数据到数据库
        //使用litepal数据库框架update时每次只能update一个id中的一条信息，如果相同则不更新。
//        upDateBookToSqlite3(databaseId, bookList);
    }

    /**
     * 隐藏item
     *
     * @param hidePosition
     */
    @Override
    public void setHideItem(int hidePosition) {
        this.mHidePosition = hidePosition;
        notifyDataSetChanged();
    }

    /**
     * 删除书本
     *
     * @param deletePosition
     */
    @Override
    public void removeItem(int deletePosition) {

        String bookpath = mList.get(deletePosition).getBookpath();
//        DataSupport.deleteAll(BookList.class, "bookpath = ?", bookpath);
        mList.remove(deletePosition);
        notifyDataSetChanged();

    }

    public void setBookList(List<BookList> bookLists) {
        this.mList = bookLists;
        notifyDataSetChanged();
    }

    /**
     * Book打开后位置移动到第一位
     *
     * @param openPosition
     */
    @Override
    public void setItemToFirst(int openPosition) {

//        List<BookList> bookLists1 = new ArrayList<>();
        List<BookList> bookLists1 = mList;
//        bookLists1 = DataSupport.findAll(BookList.class);
        int tempId = bookLists1.get(0).getId();
        BookList temp = bookLists1.get(openPosition);
        // Log.d("setitem adapter ",""+openPosition);
        if (openPosition != 0) {
            for (int i = openPosition; i > 0; i--) {
//                List<BookList> bookListsList = new ArrayList<>();
                List<BookList> bookListsList = mList;
//                bookListsList = DataSupport.findAll(BookList.class);
                int dataBasesId = bookListsList.get(i).getId();

                Collections.swap(bookLists1, i, i - 1);
                updateBookPosition(i, dataBasesId, bookLists1);
            }

            bookLists1.set(0, temp);
            updateBookPosition(0, tempId, bookLists1);
            for (int j = 0; j < bookLists1.size(); j++) {
                String bookpath = bookLists1.get(j).getBookpath();
                //  Log.d("移动到第一位",bookpath);
            }
        }
        notifyDataSetChanged();
    }

    public void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
        myAsyncTasks.add(asyncTask.execute());
    }

    /**
     * 数据库书本信息更新
     *
     * @param databaseId 要更新的数据库的书本ID
     * @param bookList
     */
    public void upDateBookToSqlite3(final int databaseId, final BookList bookList) {

//        putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
//            @Override
//            protected void onPreExecute() {
//
//            }
//
//            @Override
//            protected Boolean doInBackground(Void... params) {
////                try {
////                    bookList.update(databaseId);
////                } catch (DataSupportException e) {
////                    return false;
////                }
//                return true;
//            }
//
//            @Override
//            protected void onPostExecute(Boolean result) {
//                if (result) {
//
//                } else {
//                    Log.d("保存到数据库结果-->", "失败");
//                }
//            }
//        });
    }


}