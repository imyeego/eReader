package com.a16lao.wyh.ui.shelf.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a16lao.wyh.R;
import com.a16lao.wyh.bean.shelf.BookList;
import com.a16lao.wyh.bean.shelf.Essay;
import com.bumptech.glide.Glide;
import com.koolearn.android.kooreader.util.AndroidImageSynchronizer;
import com.koolearn.android.util.LogUtil;
import com.koolearn.klibrary.core.image.ZLImage;
import com.koolearn.klibrary.core.image.ZLImageProxy;
import com.koolearn.klibrary.ui.android.image.ZLAndroidImageData;
import com.koolearn.klibrary.ui.android.image.ZLAndroidImageManager;
import com.koolearn.kooreader.Paths;
import com.koolearn.kooreader.book.Book;
import com.koolearn.kooreader.book.CoverUtil;
import com.koolearn.kooreader.book.MultiSelectableBook;
import com.koolearn.kooreader.formats.PluginCollection;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyHolder> implements AdapterListType<Book>{

    private List<Book> list;
    private Context context;
    private String cacheDir;
    private DataChangedListener listener;


    private static String uri = "https://img3.doubanio.com/view/subject/l/public/s29751733.jpg";

    public BookAdapter(Context context, List<Book> list){
        this.context = context;
        this.list = list;
        cacheDir = getExternalCacheDirPath();
    }

    public interface DataChangedListener{
        void dataSetChanged();
    }

    public void setListener(DataChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public void notifyDataChanged() {
        listener.dataSetChanged();
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_shelf_item, parent, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookAdapter.MyHolder holder, int position) {
        Book item = list.get(position);
        holder.tvBookName.setText(item.getTitle());

        if (item.isSelected()){

            holder.ivSelected.setVisibility(View.VISIBLE);
            if (item.isClicked()){
                holder.ivSelected.setBackgroundResource(R.drawable.selected);
            }else{
                holder.ivSelected.setBackgroundResource(R.drawable.unselected);
            }
        }else{
            holder.ivSelected.setVisibility(View.GONE);
        }

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.book_cover)
                .showImageOnFail(R.drawable.book_cover)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage("file://" + cacheDir + "/" + item.getSortKey() + ".png", holder.ivBookCover, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                setCoverCache(item);
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });


    }

    public List<Book> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Book getBook(int pos) {
        return list.get(pos);
    }

    public void updateItems(List<Book> books, boolean animated) {
        list.addAll(books);
        notifyDataChanged();
    }

    public void clearItems() {
        list.clear();
        notifyDataChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvBookName;
        ImageView ivBookCover, ivSelected;

        MyHolder(View itemView){
            super(itemView);
            tvBookName = itemView.findViewById(R.id.tv_name);
            ivBookCover = itemView.findViewById(R.id.iv_bookCover);
            ivSelected = itemView.findViewById(R.id.img_selected);
        }

    }

    private String getExternalCacheDirPath() {
        File d = context.getExternalCacheDir();
        if (d != null) {
            d.mkdirs();
            if (d.exists() && d.isDirectory()) {
                return d.getPath();
            }
        }
        return null;
    }

    private void setCoverCache(final Book book){
        final String fileName = getExternalCacheDirPath() + "/" + book.getSortKey() + ".png";
        File file = new File(fileName);
        if (file.exists()) {
            return;
        }
        AndroidImageSynchronizer myImageSynchronizer = new AndroidImageSynchronizer((Activity) context);
        PluginCollection pluginCollection = PluginCollection.Instance(Paths.systemInfo(context));
        final ZLImage image = CoverUtil.getCover(book, pluginCollection);
        if (image instanceof ZLImageProxy) {
            ((ZLImageProxy) image).startSynchronization(myImageSynchronizer, new Runnable() {
                public void run() {
                    ((Activity)context).runOnUiThread(() -> {
                        ZLAndroidImageData data = ((ZLAndroidImageManager) ZLAndroidImageManager.Instance()).getImageData(image);
                        if (data != null) {
                            DisplayMetrics metrics = new DisplayMetrics();
                            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
                            int maxHeight = metrics.heightPixels * 1 / 3;
                            int maxWidth = maxHeight * 1 / 3;
                            Bitmap coverBitmap = data.getBitmap(maxWidth, maxHeight);
                            try {
                                saveMyBitmap(fileName, coverBitmap, book);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            });
        }
    }

    private void saveMyBitmap(String fileName, Bitmap mBitmap, Book book) throws IOException {
        File file = new File(fileName);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 50, fOut);
        notifyDataSetChanged();
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
