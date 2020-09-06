package com.mehmetfatih.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView filmlerRv;
    private ArrayList<Filmler> filmlerArrayList;
    private FilmlerAdapter adapter;
    private Kategoriler kategori;
    private FilmlerDaoInterface filmlerDaoInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmler);

        toolbar = findViewById(R.id.toolbar);
        filmlerRv = findViewById(R.id.filmlerRv);

        kategori = (Kategoriler) getIntent().getSerializableExtra("kategoriNesne");

        filmlerDaoInterface = ApiUtils.getFilmlerDaoInterface();

        toolbar.setTitle(kategori.getKategoriAd());
        setSupportActionBar(toolbar);

        filmlerRv.setHasFixedSize(true);
        filmlerRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        filmlerDaoInterface.tumFilmlerByKategoriID(Integer.parseInt(kategori.getKategoriId())).enqueue(new Callback<FilmCevap>() {
            @Override
            public void onResponse(Call<FilmCevap> call, Response<FilmCevap> response) {
                List<Filmler> filmler = response.body().getFilmler();

                adapter = new FilmlerAdapter(MovieActivity.this,filmler);

                filmlerRv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<FilmCevap> call, Throwable t) {

            }
        });



    }
}
