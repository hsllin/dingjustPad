package com.dingjust.pad.presenter.impl;

import com.dingjust.pad.bean.Material;
import com.dingjust.pad.interactor.PhotoInteractor;
import com.dingjust.pad.interactor.impl.PhotoInteractorImpl;
import com.dingjust.pad.presenter.PhotoPresenter;
import com.dingjust.pad.view.PhotoView;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class PhotoPresenterImpl implements PhotoPresenter, PhotoInteractor.OnPhotoFinishedListener {
    private PhotoView photoView;
    private PhotoInteractor photoInteractor;

    public PhotoPresenterImpl(PhotoView photoView, PhotoInteractor photoInteractor) {
        this.photoView = photoView;
        this.photoInteractor = photoInteractor;
    }

    @Override
    public void onResume() {
        if (photoView != null) {
            photoView.showProgress();
        }
        photoInteractor.findItems(this);
    }

    @Override
    public void destroy() {
        if (photoView != null) {
            photoView = null;
        }
    }

    @Override
    public void onFinished(List<Material> items) {
        if (photoView != null) {
            photoView.setItems(items);
            photoView.hideProgress();
        }

    }


}
