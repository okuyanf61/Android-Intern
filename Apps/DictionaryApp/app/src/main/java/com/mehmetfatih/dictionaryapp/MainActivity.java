package com.mehmetfatih.dictionaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Kelimeler> kelimelerListe;
    private KelimelerAdapter adapter;
    private KelimelerDaoInterface kelimelerDIF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        toolbar.setTitle("Dictionary App");
        setSupportActionBar(toolbar);

        kelimelerDIF = ApiUtils.getKisilerDaoInterfeace();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        tumKisiler();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Gönderilen arama",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("Harf girdikçe",newText);
        kelimeAra(newText);
        return false;
    }

    public void tumKisiler(){

        kelimelerDIF.tumKelimeler().enqueue(new Callback<KelimelerCevap>() {
            @Override
            public void onResponse(Call<KelimelerCevap> call, Response<KelimelerCevap> response) {

                List<Kelimeler> listeTemp = response.body().getKelimeler();

                adapter = new KelimelerAdapter(MainActivity.this,listeTemp);

                rv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<KelimelerCevap> call, Throwable t) {

            }
        });


    }

    public void kelimeAra(String aramaKelime){

        kelimelerDIF.kelimeAra(aramaKelime).enqueue(new Callback<KelimelerCevap>() {
            @Override
            public void onResponse(Call<KelimelerCevap> call, Response<KelimelerCevap> response) {

                List<Kelimeler> listeTemp = response.body().getKelimeler();

                adapter = new KelimelerAdapter(MainActivity.this,listeTemp);

                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KelimelerCevap> call, Throwable t) {

            }
        });


    }



}
