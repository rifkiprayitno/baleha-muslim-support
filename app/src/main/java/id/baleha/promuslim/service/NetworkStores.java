package id.baleha.promuslim.service;

import id.baleha.promuslim.model.praytime.PrayTimeResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkStores {

//    @GET("v1/timings/{timestamp}?latitude={lat}&longitude={lng}&method={useMethod}")
    @GET("v1/timings/{timestamp}")
    Observable<PrayTimeResponse> getPrayTime(@Path("timestamp") String timestamp, @Query("latitude") String lat, @Query("longitude") String lng, @Query("method") Integer useMethod);

}
