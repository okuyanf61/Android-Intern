package com.mehmetfatih.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView ımageViewFilmResim;
    private TextView textViewFilmAd,textViewYil,textViewYonetmen;
    private Filmler film;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        ımageViewFilmResim = findViewById(R.id.imageViewResim);
        textViewFilmAd = findViewById(R.id.textViewFilmAd);
        textViewYil = findViewById(R.id.textViewYil);
        textViewYonetmen = findViewById(R.id.textViewYonetmen);

        film = (Filmler) getIntent().getSerializableExtra("nesne");

        textViewFilmAd.setText(film.getFilmAd());
        textViewYil.setText(String.valueOf(film.getFilmYil()));
        textViewYonetmen.setText(film.getYonetmen().getYonetmenAd());

        String url = "https://mfoandroid.000webhostapp.com/filmler/resimler/"+film.getFilmResim();

        Picasso.get().load(url).resize(400,600).into(ımageViewFilmResim);

    }
}
