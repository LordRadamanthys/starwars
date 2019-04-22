package com.example.resource.apistarwars.presenter;

import com.example.resource.apistarwars.model.ServiceApi.IServiceApiStarWars;
import com.example.resource.apistarwars.model.infra.BuildApi;
import com.example.resource.apistarwars.model.Filmes;
import com.example.resource.apistarwars.model.Personagens;
import com.example.resource.apistarwars.model.TodosFilmes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarWarsPresenter implements IStarWarsPresenter.starPresenter {
    private IServiceApiStarWars serviceApiStarWars;
    private IStarWarsPresenter.starView view;
    private Throwable erro;

    public StarWarsPresenter(IStarWarsPresenter.starView  view) {
        serviceApiStarWars = new BuildApi().getBuild();
        this.view = view;
    }

    @Override
    public void getAllFilms() {
        serviceApiStarWars.getAllFilms().enqueue(new Callback<TodosFilmes>() {
            @Override
            public void onResponse(Call<TodosFilmes> call, Response<TodosFilmes> response) {

                if(!response.isSuccessful()){
                    erro = new Exception("Erro nao sucesso");
                    view.onFailure(erro);
                }

                if(response.body() == null){
                    erro = new Exception("Erro vazio");
                    view.onFailure(erro);
                }

                view.carregarFilmes(response.body());
            }

            @Override
            public void onFailure(Call<TodosFilmes> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    @Override
    public void getFilm(int id) {
        serviceApiStarWars.getFilm(id).enqueue(new Callback<Filmes>() {
            @Override
            public void onResponse(Call<Filmes> call, Response<Filmes> response) {


                if (!response.isSuccessful()){
                    erro = new Exception("Erro: "+response.code());
                    view.onFailure(erro);
                }

                if(response.body() == null){
                    erro = new Exception("Erro vazio");
                    view.onFailure(erro);
                }
                view.carregarUmFilme(response.body());
            }

            @Override
            public void onFailure(Call<Filmes> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    @Override
    public void getPersonagem(int id) {
        serviceApiStarWars.getPersonagens(id).enqueue(new Callback<Personagens>() {
            @Override
            public void onResponse(Call<Personagens> call, Response<Personagens> response) {

                if (!response.isSuccessful()){
                    erro = new Exception("Erro ao buscar personagens");
                    view.onFailure(erro);
                }

                if(response.body() == null){
                    erro = new Exception("Erro ao buscar personagens: body vazio");
                    view.onFailure(erro);
                }
                view.carregarPersonagens(response.body());
            }

            @Override
            public void onFailure(Call<Personagens> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }
}
