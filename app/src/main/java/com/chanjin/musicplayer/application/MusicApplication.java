package com.chanjin.musicplayer.application;


import android.app.Application;
import com.chanjin.musicplayer.database.DatabaseClient;
import com.chanjin.musicplayer.http.HttpClient;
import com.chanjin.musicplayer.util.ToastTool;
import com.chanjin.musicplayer.util.Util;

/**
 * Created by chanjin on 2017/8/31.
 */
public class MusicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCache.init(this);
        Util.init(this);
        HttpClient.init();
        DatabaseClient.init(this);
        ToastTool.init(this);

    }
}
