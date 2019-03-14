package com.a16lao.wyh.ui.shelf.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.ui.shelf.adapter.BookAdapter;
import com.a16lao.wyh.ui.shelf.event.AllSelected;
import com.a16lao.wyh.ui.shelf.event.BookMultiSelector;
import com.a16lao.wyh.ui.shelf.event.ItemSelected;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.widget.MultiOptionView.MultiOptionView;
import com.koolearn.android.kooreader.KooReader;
import com.koolearn.android.kooreader.RecyclerItemClickListener;
import com.koolearn.android.kooreader.libraryService.BookCollectionShadow;
import com.koolearn.kooreader.book.Book;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class BookSetFragment extends LazyFragment implements AdapterView.OnItemClickListener, BookAdapter.DataChangedListener {

    private Context context;
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    List<Book> list = new CopyOnWriteArrayList<>();
    private final BookCollectionShadow bookCollectionShadow = new BookCollectionShadow();

    private TextView tvBookSum;
    //点击书本的位置
    private int itemPosition;
    private RelativeLayout layout_title;
    //flag=true在编辑状态
    private boolean flag = false, type = true;
    private Toolbar toolbar;
    private MultiOptionView optionView;


    @Override
    public Object setLayout() {
        return R.layout.fragment_bookset;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        context = getContext();
        EventBus.getDefault().register(this);
        recyclerView = rootView.findViewById(R.id.rv_bookSet);
//        bookShelf = rootView.findViewById(R.id.bookShelf);
        tvBookSum = rootView.findViewById(R.id.tv_book_sum);
    }

    @Override
    protected void initData() {

        adapter = new BookAdapter(context, list);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager glm = new GridLayoutManager(context, 3);
        glm.setSmoothScrollbarEnabled(true);
        glm.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(glm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, onItemClickListener));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void getBooks(){
        bookCollectionShadow.bindToService(getActivity(), () -> {
            list.clear();
            list = bookCollectionShadow.recentlyOpenedBooks(15);
            for (Book book : list){
                book.setSelected(false);
                book.setClicked(false);
            }
            adapter.clearItems();
            adapter.updateItems(list, true);
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMultiSelected(BookMultiSelector selector){

        for (Book book : list){
            book.setSelected(selector.isSelected());
        }
        adapter.notifyDataSetChanged();

        if (selector.isSelected()){
            Activity activity = getActivity();
            MultiOptionView view = new MultiOptionView(activity, getContext());
            view.setAdapter(adapter);
            RxBus.getInstance().postSticky(view);
        }

    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onAllSelected(AllSelected selected){
        for (Book book : list){
            book.setClicked(true);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!flag){
            getBooks();
            flag = true;
        }

    }

    @Override
    public void dataSetChanged() {
        tvBookSum.setText(String.format("共%d本", adapter.getItemCount()));

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            if (list.get(position).isSelected()){
                if (list.get(position).isClicked()){
                    list.get(position).setClicked(false);
                }else{
                    list.get(position).setClicked(true);
                }
                adapter.notifyDataSetChanged();
                RxBus.getInstance().postSticky(new ItemSelected());
            }else
                KooReader.openBookActivity(getActivity(), adapter.getBook(position), null);
        }

        @Override
        public void onItemLongClick(final View view, final int position) {

        }
    };

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
