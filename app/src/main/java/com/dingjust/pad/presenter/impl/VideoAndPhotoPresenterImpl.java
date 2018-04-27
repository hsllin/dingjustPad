package com.dingjust.pad.presenter.impl;

import com.dingjust.pad.interactor.VideoAndPhotoInteractor;
import com.dingjust.pad.presenter.VideoAndPhotoPresenter;
import com.dingjust.pad.view.VideoAndPhotoView;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class VideoAndPhotoPresenterImpl implements VideoAndPhotoPresenter, VideoAndPhotoInteractor.OnLoadVideoFinised {
    private VideoAndPhotoView videoAndPhotoView;
    private VideoAndPhotoInteractor videoAndPhotoInteractor;

    public VideoAndPhotoPresenterImpl(VideoAndPhotoView videoAndPhotoView, VideoAndPhotoInteractor videoAndPhotoInteractor){
        this.videoAndPhotoView = videoAndPhotoView;
        this.videoAndPhotoInteractor = videoAndPhotoInteractor;
    }
    @Override
    public void resume() {
        if (videoAndPhotoView != null) {
            videoAndPhotoInteractor.findVideoAndPhotoUri(this);
        }
    }

    @Override
    public void destroy() {
        videoAndPhotoView = null;
    }

    @Override
    public void onSuccess(List<String> uriList) {
        videoAndPhotoView.setVideoAndPhotoUri(uriList);
    }
}
