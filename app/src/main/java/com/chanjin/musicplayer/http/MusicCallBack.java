package com.chanjin.musicplayer.http;

/**
 * Created by chanjin on 2017/9/12.
 */
public interface MusicCallBack<T> {

    void onSuccess(T response);

    void onFail(Throwable t);

}
