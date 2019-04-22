package com.example.resource.apistarwars.presenter;

public interface IStarWarsPresenter {

    interface starPresenter {
        void getAllFilms();

        void getFilm(int id);

        void getPersonagem(int id);
    }

    interface starView{
        void carregarFilmes( Object obj);

        void carregarPersonagens( Object obj);

        void carregarUmFilme( Object obj);

        void onFailure(Object obj);
    }
}
