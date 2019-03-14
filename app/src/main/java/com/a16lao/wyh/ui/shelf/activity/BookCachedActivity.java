package com.a16lao.wyh.ui.shelf.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.base.BaseActivity;
import com.a16lao.wyh.base.BasePresenter;
import com.a16lao.wyh.bean.shelf.BookCachedBean;
import com.a16lao.wyh.bean.shelf.PurchasedBookBean;
import com.a16lao.wyh.ui.shelf.adapter.BookCachedAdapter;
import com.a16lao.wyh.ui.shelf.fragment.DividerItemDecoration;
import com.a16lao.wyh.utils.dimen.DimenUtils;
import com.a16lao.wyh.widget.CommonDialog;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.a16lao.wyh.widget.pageindecator.ScrollState.SCROLL_STATE_IDLE;


public class BookCachedActivity extends BaseActivity implements View.OnClickListener, BookCachedAdapter.OnItemClickLisener {

    private Context context;
    private RecyclerView recyclerView;
    private RelativeLayout rlRoot, rlDeleteRoot;
    private List<BookCachedBean> list = new ArrayList<>();
    private BookCachedAdapter adapter;
    private TextView tvDeleteCount;
    private CommonDialog dialog;
    private Map<Integer, BookCachedBean> selectedMap = new HashMap<>();
    private boolean isEdited = false;

    @Override
    protected int setLayout() {
        return R.layout.activity_book_cached;
    }

    @Override
    protected void getFromIntentData() {

    }

    @Override
    protected void initView() {
        context = this;
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(context, R.color.white), true);

        setTitleMiddleText((String) getResources().getText(R.string.text_shelf_book_cached));
        setTitleRightText((String) getResources().getText(R.string.text_shelf_multioption), this);

        recyclerView = findViewById(R.id.rv_book_cached);
        rlRoot = findViewById(R.id.rl_root);
        TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    @Override
    protected void initData() {

        String[] imgUrls = new String[]{"https://img1.doubanio.com/view/subject/m/public/s29774838.jpg"
                ,"https://img3.doubanio.com/view/subject/m/public/s29767895.jpg"
                ,"https://img3.doubanio.com/view/subject/m/public/s29767895.jpg"
                ,"https://img3.doubanio.com/view/subject/m/public/s29816532.jpg"
                ,"https://img1.doubanio.com/view/subject/m/public/s29779138.jpg"
                ,"https://img1.doubanio.com/view/subject/m/public/s28982109.jpg"};

        String[] bookNames = new String[]{"道岳独尊", "荣耀法兰西","娱乐春秋","致命亲爱的","生活有泪", "步履不停"};
        String[] loadingStates = new String[]{"连载","完结","连载","完结","连载", "完结"};
        String[] tags = new String[]{"言情", "历史架空", "言情", "现代军事", "言情", "文学"};
        String[] downloadStates = new String[]{"下载中","已下载", "已下载", "已下载", "已下载", "已下载"};
        boolean[] isChapter = {false, true, true, false, false, false};
        String[] downloadTimes = new String[]{"2018-05-10", "2018-05-01", "2018-04-20","2018.07.02", "2018.06.15", "2018.03.26"};

        for (int i = 0; i < 6; ++i){
            BookCachedBean bean = new BookCachedBean();
            bean.setImgUrl(imgUrls[i]);
            bean.setBookName(bookNames[i]);
            bean.setIsFinished(loadingStates[i]);
            bean.setTag(tags[i]);
            bean.setDownloadState(downloadStates[i]);
            bean.setChapter(isChapter[i]);
            bean.setDownloadTime(downloadTimes[i]);
            bean.setSelected(false);
            bean.setClicked(false);
            list.add(bean);
        }

        adapter = new BookCachedAdapter(context, list);
        adapter.setOnItemClickLister(this);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == SCROLL_STATE_IDLE) { // 滚动静止时才加载图片资源
                    adapter.setScrolling(false);
                    adapter.notifyDataSetChanged(); // notify调用后onBindViewHolder会响应调用
                } else
                    adapter.setScrolling(true);
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemViewCacheSize(8);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void setListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_toolbar_right:
                if (!isEdited){
                    for (BookCachedBean bean : list){
                        bean.setSelected(true);
                    }

                    adapter.notifyDataSetChanged();
                    addDeleteView();
                }else{

                    for (BookCachedBean bean : list){
                        bean.setSelected(false);
                    }
                    adapter.notifyDataSetChanged();
                    removeDeleteView();
                }

                break;

            case R.id.text_toolbar_left:
                for (int i = 0; i < list.size(); ++i){
                    list.get(i).setClicked(true);
                    selectedMap.put(i, list.get(i));
                }
                adapter.notifyDataSetChanged();
                updateDeleteCount();
                break;

            case R.id.image_toolbar_back:
                finish();
                break;

            case R.id.rl_delete_root:
                CommonDialog.Builder builder = new CommonDialog.Builder(context).setTitle("提示")
                        .setContent("删除所选缓存书籍？(" + selectedMap.size() + ")")
                        .setLeftClickListener("取消", () -> dialog.dismiss())
                        .setRightClickListener("确定", () -> {
                            Iterator it = selectedMap.entrySet().iterator();
                            while (it.hasNext()){
                                Map.Entry entry = (Map.Entry) it.next();
                                list.remove(entry.getValue());
                            }
                            dialog.dismiss();
                            adapter.notifyDataSetChanged();
                            selectedMap.clear();
                            updateDeleteCount();

                        });
                dialog = builder.create();
                dialog.show();

                break;
        }
    }

    @Override
    public void onItemClick(View view, int pos) {
        if (list.get(pos).isClicked()){
            list.get(pos).setClicked(false);
            selectedMap.remove(pos);
        }else{
            list.get(pos).setClicked(true);
            selectedMap.put(pos, list.get(pos));
        }
        updateDeleteCount();
        adapter.notifyItemChanged(pos);
    }

    @Override
    public void onItemLongClick(View view, int pos) {

    }

    private void addDeleteView(){
        setTitleMiddleText("批量管理");
        setTitleRightText("完成", this);
        setTitleLeftText("全选", this);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_delete, null, false);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.height = DimenUtils.dip2px(context, 48);

        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlRoot.addView(view, lp);
        rlDeleteRoot = view.findViewById(R.id.rl_delete_root);
        rlDeleteRoot.setOnClickListener(this);
        tvDeleteCount = view.findViewById(R.id.tv_delete_count);
        isEdited = true;

    }

    private void updateDeleteCount(){
        if (selectedMap.size() == 0){
            tvDeleteCount.setText("删除");
        }else{
            tvDeleteCount.setText("删除 (" + selectedMap.size() + " )");
        }
    }

    private void removeDeleteView(){
        setTitleLeftNavigator(R.drawable.ic_arrow_left, this);
        setTitleMiddleText("书籍缓存");
        setTitleRightText("批量管理", this);
        rlRoot.removeViewAt(rlRoot.getChildCount() - 1);
        isEdited = false;

    }
}
