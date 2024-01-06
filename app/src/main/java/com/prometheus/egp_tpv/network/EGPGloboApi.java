package com.prometheus.egp_tpv.network;

import com.prometheus.egp_tpv.model.ProgrammeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EGPGloboApi {
    @GET("/programmes/1337/")
    Call<ProgrammeResponse> getProgramByDate(
            @Query("date") String date
    );
}
