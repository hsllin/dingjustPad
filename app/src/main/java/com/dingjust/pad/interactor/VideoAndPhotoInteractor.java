package com.dingjust.pad.interactor;

import java.util.List;

/**
 * @author: haungsonglin
 * @version: 1.0
 */
public interface VideoAndPhotoInteractor {
    interface OnLoadVideoFinised {
        void onSuccess(List<String> uriList);
    }

    void findVideoAndPhotoUri(OnLoadVideoFinised onLoadVideoFinised);
}
