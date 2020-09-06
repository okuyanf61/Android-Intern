package com.mehmetfatih.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView kategoriRv;
    private ArrayList<Kategoriler> kategorilerArrayList;
    private KategoriAdapter adapter;
    private KategorilerDaoInterface kategorilerDaoInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        kategoriRv = findViewById(R.id.kategoriRv);

        toolbar.setTitle("Categories");
        setSupportActionBar(toolbar);

        kategoriRv.setHasFixedSize(true);
        kategoriRv.setLayoutManager(new LinearLayoutManager(this));

        kategorilerDaoInterface = ApiUtils.getKategorilerDaoInterface();

        kategorilerDaoInterface.tumKategoriler().enqueue(new Callback<KategoriCevap>() {
            @Override
            public void onResponse(Call<KategoriCevap> call, Response<KategoriCevap> response) {
                List<Kategoriler> kategoriler = response.body().getKategoriler();

                adapter = new KategoriAdapter(MainActivity.this,kategoriler);

                kategoriRv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KategoriCevap> call, Throwable t) {

            }
        });

    }
}
