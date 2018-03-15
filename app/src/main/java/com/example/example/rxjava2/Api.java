package com.example.example.rxjava2;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/11$ 10:57$
 * <p/>
 */
public interface Api {
    @GET("citys")
    Observable<AllCity> getAllCity(@Query("key") String key);
}
