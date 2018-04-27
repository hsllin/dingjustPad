package com.dingjust.pad.interactor.impl;

import com.dingjust.pad.interactor.VideoAndPhotoInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public class VideoAndPhotoInteractorImpl implements VideoAndPhotoInteractor {
    private List<String> mUrlList;

    @Override
    public void findVideoAndPhotoUri(OnLoadVideoFinised onLoadVideoFinised) {
        onLoadVideoFinised.onSuccess(getUri());
    }

    public List<String> getUri() {
        mUrlList = new ArrayList<>();
        mUrlList.add("http://oebqxz1zs.bkt.clouddn.com/shoutao.jpg");
        mUrlList.add("http://oebqxz1zs.bkt.clouddn.com/gongyi.mp4");
        mUrlList.add("http://oebqxz1zs.bkt.clouddn.com/wazi.jpg");
        mUrlList.add("http://oebqxz1zs.bkt.clouddn.com/test.mp4");
        return mUrlList;
    }
}
