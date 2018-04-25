package com.dingjust.pad.interactor;

import com.dingjust.pad.bean.Material;

import java.util.List;

public interface PhotoInteractor {
    interface OnPhotoFinishedListener {
        void onFinished(List<Material> items);
    }

    void findItems(OnPhotoFinishedListener listener);
}
