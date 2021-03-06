package com.chanjin.musicplayer.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.chanjin.musicplayer.R;
import com.chanjin.musicplayer.application.AppCache;
import com.chanjin.musicplayer.service.PlayerService;
import com.chanjin.musicplayer.service.ScanCallBack;
import com.chanjin.musicplayer.util.LogTool;


public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkService();
    }

    private void checkService() {
        if (AppCache.getPlayService() == null){
            startPlayService();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bindPlayerService();
                }
            }, 2000);
        }else {
            startMainActivity();
            finish();
        }
    }

    private void bindPlayerService() {
        Intent intent = new Intent(this, PlayerService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }


    private void startMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void startPlayService() {
        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
    }

    PlayerServiceConnection conn = new PlayerServiceConnection();
    public class PlayerServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PlayerService playerService = ((PlayerService.PlayerBinder) service).getService();
            AppCache.setPlayService(playerService);

            playerService.scanLocalMusic(new ScanCallBack() {
                @Override
                public void onFail(String msg) {
                    LogTool.i(TAG,"scanLocalMusic onFail " + msg );
                }

                @Override
                public void onFinish() {
                    LogTool.i(TAG,"onSuccess " + AppCache.getLocalMusicList().size());
                    startMainActivity();
                    finish();
                }
            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogTool.i(TAG,"onServiceDisconnected name: "+name);
        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        if (conn != null){
            unbindService(conn);
        }
        super.onDestroy();
    }

}
