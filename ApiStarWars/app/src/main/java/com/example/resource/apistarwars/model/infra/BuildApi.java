package com.example.resource.apistarwars.model.infra;

import com.example.resource.apistarwars.model.ServiceApi.IServiceApiStarWars;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildApi {

    private Retrofit retrofit;

    public BuildApi(){
        this.retrofit = new Retrofit.Builder().baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


    public IServiceApiStarWars getBuild(){
        return this.retrofit.create(IServiceApiStarWars.class);
    }
}
