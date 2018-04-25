package com.dingjust.pad.interactor.impl;

import android.os.Handler;
import android.util.Log;
import com.dingjust.pad.bean.Material;
import com.dingjust.pad.interactor.PhotoInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class PhotoInteractorImpl implements PhotoInteractor {
    private List<Material> materialList = new ArrayList<>();

    @Override
    public void findItems(final OnPhotoFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getLists());
            }
        }, 3000);

    }

    public List<Material> getLists() {
        for (int i = 0; i < 2; i++) {
            Material material1 = new Material("http://oebqxz1zs.bkt.clouddn.com/wazi.jpg", "100000", "袜子");
            materialList.add(material1);
            Material material2 = new Material("http://oebqxz1zs.bkt.clouddn.com/shoutao.jpg", "100001", "手套");
            materialList.add(material2);
            Material material3 = new Material("http://oebqxz1zs.bkt.clouddn.com/kouzi.png", "100002", "纽扣");
            materialList.add(material3);

        }
        return materialList;
    }
}
