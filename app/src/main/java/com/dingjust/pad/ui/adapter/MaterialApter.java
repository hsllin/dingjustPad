package com.dingjust.pad.ui.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dingjust.pad.R;
import com.dingjust.pad.bean.Material;
import com.dingjust.pad.ui.activity.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class MaterialApter extends RecyclerView.Adapter<MaterialApter.ViewHolder> {
    private List<Material> lists;
    private Activity context;
    private int width;
    private int height;
    private int imageWidth;
    private int imageHeight;
    private int textHeight;


    static class ViewHolder extends RecyclerView.ViewHolder {
        View materialView;
        ImageView imageUrlView;
        TextView materialCodeView;
        TextView materialNameView;

        public ViewHolder(View view) {
            super(view);
            materialView = view;
            imageUrlView = view.findViewById(R.id.material_image);
            materialCodeView = view.findViewById(R.id.material_code);
            materialNameView = view.findViewById(R.id.material_name);
        }

    }

    public MaterialApter(Activity context, List<Material> materialList) {
        this.context = context;
        this.lists = materialList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_item, parent, false);
        //获取屏幕尺寸
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.materialView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Material material = lists.get(position);
                Toast.makeText(view.getContext(), "you click" + material.getCode(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.materialNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Material material = lists.get(position);
                Toast.makeText(view.getContext(), "you click" + material.getMaterialName(), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        //TODO:手机和平板的图片列表还没适配
//        imageHeight = (int) (height * (1 / 2.6) * 0.7);
        imageHeight = (int) (height * (1 / 2.6) * 0.75);
        imageWidth = (int) (width * (0.6 / 1.4) * 0.5);
        textHeight = (int) (height * (1 / 2.6) * 0.1);
        Material material = lists.get(position);
        //使用Picasso插件加载图片
        ViewGroup.LayoutParams photoParams = viewHolder.imageUrlView.getLayoutParams();

        photoParams.height = imageHeight;
        photoParams.width = imageWidth;
        viewHolder.imageUrlView.setLayoutParams(photoParams);

        Picasso.get().load(material.getImageUrl()).into(viewHolder.imageUrlView);

        viewHolder.materialCodeView.setText(material.getCode());
        viewHolder.materialNameView.setText(material.getMaterialName());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
