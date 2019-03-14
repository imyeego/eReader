package com.a16lao.wyh.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * date:   2018/6/14 0014 上午 10:19
 * author: caoyan
 * description:
 */

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 0;
    private int pos;
    private StaggeredGridLayoutManager.LayoutParams mLayoutParams;

    public RecyclerViewItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        mLayoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        pos = mLayoutParams.getSpanIndex();
        if (mLayoutParams.getViewLayoutPosition() == 0 || mLayoutParams.getViewLayoutPosition() == 1) {
            outRect.top = 0;
        } else {
//            if (mLayoutParams.getViewLayoutPosition() == 2) {
//                outRect.top = 40;
//            } else {
//            }
            outRect.top = space;

        }
        if (pos % 2 == 1) {
            outRect.left = space / 2;
            outRect.right = 24;
        }

        if (pos % 2 == 0) {
            outRect.left = 24;
            outRect.right = space / 2;
        }
    }
}
