package com.example.resource.apistarwars.model.ServiceApi;

import com.example.resource.apistarwars.model.Filmes;
import com.example.resource.apistarwars.model.Personagens;
import com.example.resource.apistarwars.model.TodosFilmes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IServiceApiStarWars {

    @GET("films/")
    Call<TodosFilmes> getAllFilms();

    @GET("films/{id}/")
    Call<Filmes> getFilm(@Path("id") int id);

    @GET("people/{id}/")
    Call<Personagens> getPersonagens(@Path("id") int id);
}
