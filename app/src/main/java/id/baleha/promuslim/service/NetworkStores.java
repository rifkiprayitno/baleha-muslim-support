package id.baleha.promuslim.service;

import id.baleha.promuslim.model.praytime.PrayTimeResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkStores {

    @GET("v1/timings/{timestamp}?latitude={lat}&longitude={lng}&method={useMethod}")
    Observable<PrayTimeResponse> getPrayTime(@Path("timestamp") String timestamp, @Path("lat") Long lat, @Path("lng") Long lng, @Path("") Integer useMethod);

}
