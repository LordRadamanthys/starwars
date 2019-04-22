package com.example.resource.apistarwars.view.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resource.apistarwars.R;
import com.example.resource.apistarwars.model.Filmes;
import com.example.resource.apistarwars.model.Personagens;
import com.example.resource.apistarwars.model.TodosFilmes;
import com.example.resource.apistarwars.presenter.IStarWarsPresenter;
import com.example.resource.apistarwars.presenter.StarWarsPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IStarWarsPresenter.starView {
    private Spinner spinner;
    private TextView textResult;
    private int idFilme = 0;
    private ProgressDialog dialogSpinner;
    private List<String> lista = new ArrayList<>();
    private List<String> listaIdFilmes = new ArrayList<>();
    private String s, personagensString = "", nomeFilme = "";
    private StarWarsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogSpinner = new ProgressDialog(MainActivity.this);

        spinner = findViewById(R.id.spinnerLista);
        textResult = findViewById(R.id.textView);
        Button btnPersonagens = findViewById(R.id.btnPersonagens);


        presenter = new StarWarsPresenter(this);
        presenter.getAllFilms();

        //açoes do spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, final long id) {
                dialogSpinner = ProgressDialog.show(MainActivity.this,
                        "", "Aguarde", true);

                s = listaIdFilmes.get(position);
                idFilme = Integer.parseInt(s.replace("/", ""));

                presenter.getFilm(idFilme);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnPersonagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPersonagens();
            }
        });
    }

    private void mostrarPersonagens() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Lista de personagens: " + nomeFilme)
                .setMessage(personagensString)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    @Override
    public void carregarFilmes(Object obj) {
        TodosFilmes todosFilmes = (TodosFilmes) obj;

        for (Filmes filmes : todosFilmes.getLista()) {
            lista.add(filmes.getTitle());
            listaIdFilmes.add(filmes.getUrl());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, lista);
        spinner.setAdapter(arrayAdapter);

    }

    @Override
    public void carregarPersonagens(Object obj) {

        Personagens personagem = (Personagens) obj;
        personagensString += personagem.getName() + "\n\n";
        dialogSpinner.dismiss();
    }

    @Override
    public void carregarUmFilme(Object obj) {
        String texto = "";
        personagensString="";
        Filmes filme = (Filmes) obj;

        texto += "Titulo: " + filme.getTitle() + "\n\n";
        texto += "Diretor: " + filme.getDirector() + "\n\n";
        texto += "Lançamento: " + filme.getRelease_date() + "\n\n";
        texto += "Abertura: " + filme.getOpening_crawl() + "\n\n";
        nomeFilme = filme.getTitle();
        textResult.setText(texto);

        for (String s1 : filme.getCharacters()) {
            presenter.getPersonagem(Integer.parseInt(s1));
        }

    }

    @Override
    public void onFailure(Object obj) {
        Throwable erro = (Throwable) obj;
        Toast.makeText(this, erro.getMessage(), Toast.LENGTH_LONG).show();
        dialogSpinner.dismiss();
    }
}
