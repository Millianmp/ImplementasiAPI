package com.example.implementasiapisportdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1/teams")
    Call<TeamResponse> getAllTeams(@Query("l") String league);  // "l" adalah parameter untuk league (misalnya: PL)
}
