package com.chanjin.musicplayer.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import com.chanjin.musicplayer.R;
import com.chanjin.musicplayer.application.AppCache;
import com.chanjin.musicplayer.bean.Music;
import com.chanjin.musicplayer.database.DatabaseClient;
import com.chanjin.musicplayer.util.Util;
import java.util.List;

/**
 * Created by chanjin on 2017/9/16.
 */
public class HomePlaylistAdapter extends CommonAdapter<Music> {

    private static final String TAG = "HomePlaylistAdapter";

    public HomePlaylistAdapter(Context context, List<Music> data, int mItemLayout) {
        super(context, data, mItemLayout);
    }

    public void refreshData(){
        mData = DatabaseClient.getMusic();
        this.notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder viewHolder, final Music item, final int position) {

        viewHolder.setText(R.id.tv_title, item.getTitle());
        viewHolder.setText(R.id.tv_artist, item.getArtist());
        viewHolder.setText(R.id.tv_duration, String.valueOf(Util.formatTime(item.getDuration())));
        if (AppCache.getLocalMusicList().get(position).getAlbumArt().length() > 0){
            Bitmap bp = BitmapFactory.decodeFile(
                    AppCache.getLocalMusicList().get(position).getAlbumArt());
            viewHolder.setImageBitmap(R.id.img_music,bp);
        }else {
            viewHolder.setImageResource(R.id.img_music,R.mipmap.default_music);
        }

        if (AppCache.getPlayingMusic().getId() == item.getId()){
            viewHolder.getView(R.id.tv_playing).setVisibility(View.VISIBLE);
        }else {
            viewHolder.getView(R.id.tv_playing).setVisibility(View.INVISIBLE);
        }

        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AppCache.getPlayService().playStartMusic(item);
                AppCache.getPlayService().play(mData,position);
            }
        });

    }


}
