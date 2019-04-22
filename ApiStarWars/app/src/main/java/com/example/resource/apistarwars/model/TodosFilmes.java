package com.example.resource.apistarwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TodosFilmes {

    @SerializedName("results")
    private List<Filmes> lista;

    public List<Filmes> getLista() {
        return lista;
    }

}
