package com.a16lao.wyh.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.base.LazyFragment;
import com.a16lao.wyh.bean.shelf.BookList;
import com.a16lao.wyh.bean.shelf.TabGoneBean;
import com.a16lao.wyh.config.Latte;
import com.a16lao.wyh.ui.main.CallBack;
import com.a16lao.wyh.ui.mine.activity.RechargeActivity;
import com.a16lao.wyh.ui.shelf.activity.BookCachedActivity;
import com.a16lao.wyh.ui.shelf.activity.CommentHistoryActivity;
import com.a16lao.wyh.ui.shelf.activity.PurchasedActivity;
import com.a16lao.wyh.ui.shelf.activity.RecentlyReadActivity;
import com.a16lao.wyh.ui.shelf.adapter.ShelfAdapter;
import com.a16lao.wyh.ui.shelf.adapter.ShelfPagerAdapter;
import com.a16lao.wyh.ui.shelf.event.BookMultiSelector;
import com.a16lao.wyh.ui.shelf.event.EssayMultiSelector;
import com.a16lao.wyh.ui.shelf.fragment.BookSetFragment;
import com.a16lao.wyh.ui.shelf.fragment.EssaySetFragment;
import com.a16lao.wyh.ui.shelf.popupwindow.MorePopUpWindow;
import com.a16lao.wyh.ui.shelf.widget.DragGridView;
import com.a16lao.wyh.ui.shelf.widget.MenuItem;
import com.a16lao.wyh.ui.shelf.widget.MoreMenu;
import com.a16lao.wyh.utils.RxBus;
import com.a16lao.wyh.utils.ToastUtils;
import com.a16lao.wyh.utils.dimen.DimenUtils;
import com.a16lao.wyh.widget.CustomViewPager;
import com.a16lao.wyh.widget.MultiOptionView.MultiOptionView;
import com.a16lao.wyh.widget.pageindecator.CommonNavigator;
import com.a16lao.wyh.widget.pageindecator.IPagerIndicator;
import com.a16lao.wyh.widget.pageindecator.ITitleView;
import com.a16lao.wyh.widget.pageindecator.NavigatorAdapter;
import com.a16lao.wyh.widget.pageindecator.TitleView;
import com.a16lao.wyh.widget.pageindecator.ViewPagerHelper;
import com.a16lao.wyh.widget.pageindecator.ViewPagerIndicator;
import com.a16lao.wyh.widget.pageindecator.WrapPagerIndicator;
import com.a16lao.wyh.zxing.android.CaptureActivity;
import com.githang.statusbar.StatusBarCompat;
import com.koolearn.android.kooreader.KooReader;
import com.koolearn.android.kooreader.libraryService.BookCollectionShadow;
import com.koolearn.kooreader.book.Book;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * date:   2018/5/18 0018 下午 3:32
 * author: caoyan
 * description:
 */

public class BookShelfFragment extends LazyFragment implements CallBack, View.OnClickListener, ShelfAdapter.ShelfCallBack, AdapterView.OnItemClickListener, MoreMenu.OnMenuItemClickListener {
    private Context context;
    private TextView books, article, text_book_delete, text_toolbar_left, text_toolbar_right;
    private ImageView history, more;
    private DragGridView bookShelf;
    private CustomViewPager vpBookShelf;
    private ViewPagerIndicator vpi;
    private List<String> nDataList = Arrays.asList(new String[]{"书籍", "短文"});
    private List<String> itemTitles = Arrays.asList(new String[]{"扫一扫", "书架管理", "已购书籍", "下载管理","历史评论", "夜间模式"});
    private List<Fragment> fragments = new ArrayList<>();
    private MoreMenu moreMenu;
    private boolean showIcon = false;
    private boolean dimBg = true;
    private boolean needAnim = true;
    private MorePopUpWindow et;
    private List<BookList> mBookLists;
    private ShelfAdapter adapter;
    //点击书本的位置
    private int itemPosition;
    private RelativeLayout layout_title;
    //flag=true在编辑状态
    private boolean flag = true, type = true, isLoad = false;
    private Toolbar toolbar;

    private final BookCollectionShadow myCollection = new BookCollectionShadow();


    @Override
    public Object setLayout() {
        return R.layout.fragment_book_shelf;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        context = getContext();
        EventBus.getDefault().register(this);
//        books = rootView.findViewById(R.id.books);
//        article = rootView.findViewById(R.id.article);
        history = rootView.findViewById(R.id.history);
        more = rootView.findViewById(R.id.more);
        vpBookShelf = rootView.findViewById(R.id.vp_bookshelf);
        vpBookShelf.setPagingEnabled(true);
        vpi = rootView.findViewById(R.id.vpi_bookshelf);
        layout_title = rootView.findViewById(R.id.layout_title);
        text_book_delete = rootView.findViewById(R.id.text_book_delete);
        toolbar = rootView.findViewById(R.id.view_toolbar);
        StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(context, R.color.white) , true);
    }

    @Override
    protected void initData() {
        flag = false;
        mBookLists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BookList book = new BookList();
            book.setBegin(1);
            book.setBookname("书籍" + i);
            book.setBookpath("");
            book.setCharset("UTF-8");
            book.setImage(R.mipmap.ic_launcher);
            mBookLists.add(book);
        }
//        adapter = new ShelfAdapter(mActivity, mBookLists);
//        bookShelf.setAdapter(adapter);
        Log.e("bookShelfFragment", " initData...");
        if (!isLoad){
            fragments.add(new BookSetFragment());
            fragments.add(new EssaySetFragment());
        }

        vpBookShelf.setAdapter(new ShelfPagerAdapter(getChildFragmentManager(), nDataList, fragments));
        vpBookShelf.setCurrentItem(0);
        setupIndicator();
        isLoad = true;
        et = new MorePopUpWindow(mActivity);
        setListener();


    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        if (isVisibleToUser){
//            Log.e("bookShelfFragment", "is visible");
//            if (!isLoad){
//                vpBookShelf.setAdapter(new DefaultFrgPagerAdapter(getFragmentManager(), fragments));
//                vpBookShelf.setCurrentItem(itemPosition);
//                setupIndicator();
//                isLoad = true;
//
//            }
//        }
//    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible){
        }
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMultiSelected(BookMultiSelector selector){
        if (!selector.isSelected()) vpBookShelf.setPagingEnabled(true);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMultiSelected(EssayMultiSelector selector){
        if (!selector.isSelected()) vpBookShelf.setPagingEnabled(true);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    private void setListener() {
        et.setOnCallBack(this);
        more.setOnClickListener(this);
        history.setOnClickListener(this);
        text_book_delete.setOnClickListener(this);
//        bookShelf.setOnItemClickListener(this);
//        adapter.setonShelfCallBack(this);

//        bookShelf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                if (mBookLists.size() > position) {
//                    itemPosition = position;
////                    adapter.setItemToFirst(itemPosition);
//                    final BookList bookList = mBookLists.get(itemPosition);
//                    bookList.setId(mBookLists.get(0).getId());
//                    final String path = bookList.getBookpath();
//                    File file = new File(path);
//                    if (!file.exists()) {
//                        new AlertDialog.Builder(mActivity)
//                                .setTitle("")
//                                .setMessage(path + "文件不存在,是否删除该书本？")
//                                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
////                                        DataSupport.deleteAll(BookList.class, "bookpath = ?", path);
////                                        bookLists = DataSupport.findAll(BookList.class);
//                                        adapter.removeItem(position);
//                                    }
//                                }).setCancelable(true).show();
//                        return;
//                    }
//                    ToastUtils.showShortToast(mActivity, "打开" + bookList.getBookname());
////                    ReadActivity.openBook(bookList,mActivity);
//                }
//            }
//        });
    }

    /**
     * popupwindow
     *
     * @param position
     * @param str
     */
    @Override
    public void onCallBack(int position, String str) {
        switch (position) {
            case 0:
                ToastUtils.showToast(mActivity, 0 + "");
                break;
            case 1:
                setTitleMiddleText("批量管理");
                setTitleLeftText("全选", this);
                setTitleRightText("完成", this);
                layout_title.setVisibility(View.GONE);
                text_book_delete.setVisibility(View.VISIBLE);
                RxBus.getInstance().postSticky(new TabGoneBean(true));

                for (int i = 0; i < mBookLists.size(); i++) {
                    mBookLists.get(i).setChecked(false);
                }
                text_book_delete.setEnabled(false);

                flag = true;
                toolbar.setVisibility(View.VISIBLE);
                break;
            case 2:
                toOtherActivity(RechargeActivity.class, null, false);
                break;
            case 3:
                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
        }
        if (et.isShowing()) {
            et.dismiss();
        }
        adapter.notifyDataSetChanged();
    }

    private void setupIndicator(){
        vpi.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setScrollPivotX(0.35f);
        commonNavigator.setAdapter(new NavigatorAdapter() {
            @Override
            public int getCount() {
                return nDataList == null ? 0 : nDataList.size();
            }

            @Override
            public ITitleView getTitleView(Context context, final int index) {
                TitleView titleView = new TitleView(context);
                titleView.setText(nDataList.get(index));
                titleView.setNormalColor(ContextCompat.getColor(context, R.color.color_2));
                titleView.setSelectedColor(ContextCompat.getColor(context, R.color.white));
                titleView.setOnClickListener(v -> {
                    vpBookShelf.setCurrentItem(index);

                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(ContextCompat.getColor(context, R.color.color_1));
                return indicator;
            }
        });
        vpi.setNavigator(commonNavigator);
        ViewPagerHelper.bind(vpi, vpBookShelf);
        vpBookShelf.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                itemPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.more:
//                et.showPopupWindow(more);

                moreMenu = new MoreMenu(getActivity());
                List<MenuItem> menuItems = new ArrayList<>();
                for (String title : itemTitles){
                    menuItems.add(new MenuItem(title));
                }

                moreMenu
                        .setHeight(DimenUtils.dip2px(context, 243))
                        .setWidth(DimenUtils.dip2px(context,120))
                        .showIcon(showIcon)     //显示菜单图标，默认为true
                        .dimBackground(dimBg)           //背景变暗，默认为true
                        .needAnimationStyle(needAnim)   //显示动画，默认为true
                        .setAnimationStyle(R.style.TRM_ANIM_STYLE)  //默认为R.style.TRM_ANIM_STYLE
                        .addMenuList(menuItems)
                        .setOnMenuItemClickListener(this)
                        .showAsDropDown(more, -DimenUtils.dip2px(context, 80), DimenUtils.dip2px(context,2));
                break;
            case R.id.history:


                toOtherActivity(RecentlyReadActivity.class, null, false);
                break;
            case R.id.text_toolbar_left:
                flag = true;
                if (isAll()) {
                    setTitleLeftText("取消全选", this);
                    type = false;
                    text_book_delete.setEnabled(true);
                    text_book_delete.setText(String.format("删除(%d)", mBookLists.size()));
                } else {
                    setTitleLeftText("全选", this);
                    type = true;
                    text_book_delete.setEnabled(false);
                    text_book_delete.setText("删除");
                }
                break;
            case R.id.text_toolbar_right:
                flag = false;
                if (mBookLists.size() != 0) {
                    setTitleLeftText("全选", this);
                    type = true;
                } else {
                    setTitleLeftText(View.GONE);
                }
                text_book_delete.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
                layout_title.setVisibility(View.VISIBLE);
                RxBus.getInstance().postSticky(new TabGoneBean(false));
                break;
            case R.id.text_book_delete:

                //todo  fragment嵌套fragment，管理器
//                new IOSDialogFragment().show(getTargetFragment().getFragmentManager(), "");

                break;
        }

//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMenuItemClick(int position) {

        switch (position){

            case 0 :
                final String path = Environment.getExternalStorageDirectory() + "/ePub入门第一版.epub";
                File file = new File(path);
                if (file.exists()) {
                    Log.i("path", path);
                }else{
                    Log.i("path", path+ "文件不存在");

                }

                myCollection.bindToService(context, () -> {
                    Book book = myCollection.getBookByFile(path);
                    if (book != null) {
                        openBook(book);
                    } else {
                        Toast.makeText(context, "打开失败,请重试", Toast.LENGTH_SHORT).show();
                    }
                });
//                toOtherActivity(CaptureActivity.class, null, false);
                break;

            case 1 :
                if (itemPosition == 0){
                    EventBus.getDefault().post(new BookMultiSelector(true));
                }
                if (itemPosition == 1){
                    EventBus.getDefault().post(new EssayMultiSelector(true));
                }
                vpBookShelf.setPagingEnabled(false);
                break;

            case 2 :
                toOtherActivity(PurchasedActivity.class, null, false);

                break;

            case 3 :
                toOtherActivity(BookCachedActivity.class, null, false);

                break;

            case 4 :
                toOtherActivity(CommentHistoryActivity.class, null, false);
                break;

            case 5 :
                break;
        }

    }

    private void openBook(Book data) {
        KooReader.openBookActivity(Latte.getApplication(), data, null);
    }

    @Override
    public void onShelfCallBack(ImageButton imageButton) {
        if (flag) {
            imageButton.setVisibility(View.VISIBLE);
            for (int i = 0; i < mBookLists.size(); i++) {
                if (mBookLists.get(i).isChecked()) {
                    mBookLists.get(i).setImage(R.mipmap.ic_launcher_round);
                } else {
                    mBookLists.get(i).setImage(R.mipmap.ic_launcher);
                }
            }
        } else {
            imageButton.setVisibility(View.GONE);
        }
    }

    //按钮状态全部选中
    private boolean isAll() {
        for (int i = 0; i < mBookLists.size(); i++) {
            mBookLists.get(i).setChecked(type);
        }
        return type;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (flag) {
            mBookLists.get(position).setChecked(!mBookLists.get(position).isChecked());
            int num = 0;
            for (int i = 0; i < mBookLists.size(); i++) {
                if (mBookLists.get(i).isChecked()) {
                    num++;
                }
            }
            if (num != 0) {
                text_book_delete.setEnabled(true);
                text_book_delete.setText(String.format("删除(%d)", num));
            } else {
                text_book_delete.setEnabled(false);
                text_book_delete.setText("删除");
            }


            if (num == mBookLists.size()) {
                setTitleLeftText("取消全选", this);
                type = false;
            } else {
                setTitleLeftText("全选", this);
                type = true;
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
