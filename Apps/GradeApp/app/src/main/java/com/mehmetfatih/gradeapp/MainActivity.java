package com.mehmetfatih.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private NotlarAdapter adapter;
    private ArrayList<Notlar> notlarArrayList;
    private NotlarInterface notlardıf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        notlardıf = ApiUtils.getNotlarDaoInterfeace();

        notlardıf.tumNotlar().enqueue(new Callback<NotlarCevap>() {
            @Override
            public void onResponse(Call<NotlarCevap> call, Response<NotlarCevap> response) {

                double toplam = 0 ;

                List<Notlar> liste = response.body().getNotlar();

                for (Notlar n:liste){
                    toplam = toplam +(Integer.parseInt(n.getNot1())+ Integer.parseInt(n.getNot2()))/2;
                }


                adapter = new NotlarAdapter(MainActivity.this,liste);

                rv.setAdapter(adapter);

                toolbar.setSubtitle("Average : "+toplam/liste.size());
            }

            @Override
            public void onFailure(Call<NotlarCevap> call, Throwable t) {

            }
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SaveGradeActivity.class));
            }
        });
    }
}
