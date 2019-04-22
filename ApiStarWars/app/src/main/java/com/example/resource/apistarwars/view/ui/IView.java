package com.example.resource.apistarwars.view.ui;

public interface IView<T> {

    void onResponse(T obj);

    void onFailure(T obj);
}
