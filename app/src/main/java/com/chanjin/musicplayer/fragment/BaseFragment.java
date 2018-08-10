package com.chanjin.musicplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.chanjin.musicplayer.application.AppCache;
import com.chanjin.musicplayer.service.PlayerService;

/**
 * Created by chanjin on 2017/8/25.
 */
public abstract class BaseFragment extends Fragment {

    /*
    * 先加载onCreateView，才加载 onViewCreated
    * */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    protected abstract void initView(View view);

    protected PlayerService getPlayerService(){
        if (AppCache.getPlayService() == null){
            throw new NullPointerException("BaseFragment PlayerService is null");
        }
        return AppCache.getPlayService();
    }

    public void refreshMusicList(){
        if (isAdded()){
            refreshListView();
        }
    }

    protected abstract void refreshListView();

}
