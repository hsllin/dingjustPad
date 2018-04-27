package com.dingjust.pad.ui.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import com.dingjust.pad.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class BannerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater mInflater;
    private List<String> mUrlList;

    //建構子
//    public BannerAdapter(Context context, List<String> mUrlList) {
    public BannerAdapter(Context context, List<String> mUrlList) {
        //如果把這個寫在instantiateItem的話，每一個Item都會呼叫一次，很吃資源
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mUrlList = mUrlList;
    }

    //看你這ViewPager要有幾頁，靠著List的大小擴充
    @Override
    public int getCount() {
        //如果陣列為空，返回0 防呆機制
        //  return mUrlList == null ? 0 : mUrlList.size();
        return mUrlList == null ? 0 : mUrlList.size();
    }

    //用來判斷目前的畫面是否和instantiateItem創建的為同一個
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.video_item, container, false);
        //每一個Item都創建View
        JZVideoPlayerStandard jzVideoPlayerStandard = (JZVideoPlayerStandard) view.findViewById(R.id.video_player);
        ImageView imageView = view.findViewById(R.id.video_image);
//        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        用了网上开源的视频播放器饺子播放器

        String uri = mUrlList.get(position);
        int index = uri.lastIndexOf(".");
        String size = uri.substring(index + 1, uri.length());
        if (size.equals("mp4")) {
            jzVideoPlayerStandard.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            jzVideoPlayerStandard.setUp(mUrlList.get(position)
                    , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "实例视频");
            JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        } else {
//        用Glide下載圖片
            jzVideoPlayerStandard.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Picasso.get().load(mUrlList.get(position)).into(imageView);
//            Glide.with(context)
//                    .load(mUrlList.get(position))
//                    .error(R.drawable.image_loading)
//                    .placeholder(R.drawable.image_loading)
//                    .centerCrop()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                    .into(imageView);
        }


        //注销掉的是原来用来进行图片轮播的代码
//        View view = mInflater.inflate(R.layout.main_viewpager, container, false);
//        用Glide下載圖片
//        ImageView imageView = (ImageView) view.findViewById(R.id.image);
//            Glide.with(context)
//                    .load(mUrlList.get(position))
//                    .error(R.drawable.image_loading)
//                    .placeholder(R.drawable.image_loading)
//                    .centerCrop()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                    .into(imageView);
//            container.addView(view);
//            return view;
        container.addView(view);
        return view;
    }

    //移除ViewPager內所對應的視圖
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

