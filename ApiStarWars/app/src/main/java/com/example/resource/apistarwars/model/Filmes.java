package com.example.resource.apistarwars.model;

import java.util.List;

public class Filmes {
    private String title;
    private String opening_crawl;
    private String director;
    private String url;
    private String release_date;
    private List<String> characters;

    public List<String> getCharacters() {
        for (int i = 0; i < characters.size(); i++) {
            characters.set(i, characters.get(i).substring(28, 30).replace("/", ""));
        }
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getRelease_date() {
        String arr[] = release_date.split("-");

        return release_date = arr[2] + "-" + arr[1] + "-" + arr[0];
    }

    public String getUrl() {
        return url.substring(26, 29);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public String getDirector() {
        return director;
    }

}
