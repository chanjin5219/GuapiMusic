package com.chanjin.musicplayer.http;

import com.chanjin.musicplayer.bean.Lrc;
import com.chanjin.musicplayer.bean.MusicLink;
import com.chanjin.musicplayer.bean.OnLineMusicList;
import com.chanjin.musicplayer.bean.SearchMusic;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by chanjin on 2017/8/23.
 */
public interface ApiService {

    String BASE_URL_BEHIND = "v1/restserver/ting";

    @GET(BASE_URL_BEHIND)
    @Headers("User-Agent:Firefox/25.0")
    Call<OnLineMusicList> getOnLineMusicList(@Query("type")String type, @Query("size") String size,
                                              @Query("offset") String offset, @Query("method") String method);

    @GET(BASE_URL_BEHIND)
    @Headers("User-Agent:Firefox/25.0")
    Call<MusicLink> getMusicLink(@Query("songid")String songId, @Query("method") String method);

    @GET
    @Headers("User-Agent:Firefox/25.0")
    Call<ResponseBody> download(@Url String url);

    @GET(BASE_URL_BEHIND)
    @Headers("User-Agent:Firefox/25.0")
    Call<SearchMusic> getSearchMusic(@Query("method") String method, @Query("query")String keyword);

    @GET(BASE_URL_BEHIND)
    @Headers("User-Agent:Firefox/25.0")
    Call<Lrc> getLrc(@Query("method") String method, @Query("songid")String id);

}
