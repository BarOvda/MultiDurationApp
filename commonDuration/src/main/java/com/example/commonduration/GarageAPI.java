package com.example.commonduration;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.Callback;
public interface GarageAPI {

    @GET("WypPzJCt")
    Call<Garage> loadGarages();


}
