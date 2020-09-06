package com.mehmetfatih.contactsapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private Toolbar toolbar;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private ArrayList<Kisiler> kisilerArrayList;
    private KisilerAdapter adapter;
    private KisilerdaoInterface kisilerdaoInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        kisilerdaoInterface = ApiUtils.getKisilerDaoInterfeace();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        toolbar.setTitle("Contacts");
        setSupportActionBar(toolbar);


        tumKisiler();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGoster();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("onQueryTextSubmit",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("onQueryTextChange",newText);
        arama(newText);
        return false;
    }


    public void alertGoster(){

        LayoutInflater layout = LayoutInflater.from(this);

        View tasarim = layout.inflate(R.layout.alert_tasarim,null);

        final EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        final EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Add Contact");
        ad.setView(tasarim);
        ad.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String kisi_ad = editTextAd.getText().toString().trim();
                String kisi_tel = editTextTel.getText().toString().trim();

                kisilerdaoInterface.kisiEkle(kisi_ad,kisi_tel).enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                        tumKisiler();
                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });

                Toast.makeText(getApplicationContext(),kisi_ad+" - "+kisi_tel, Toast.LENGTH_SHORT).show();
            }
        });

        ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        ad.create().show();
    }


    public void tumKisiler(){

        kisilerdaoInterface.tumKisiler().enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {
                List<Kisiler> liste = response.body().getKisiler();

                adapter = new KisilerAdapter(MainActivity.this,liste,kisilerdaoInterface);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) {

            }
        });

    }

    public void arama(String kelime){

        kisilerdaoInterface.kisiArama(kelime).enqueue(new Callback<KisilerCevap>() {
            @Override
            public void onResponse(Call<KisilerCevap> call, Response<KisilerCevap> response) {
                List<Kisiler> liste = response.body().getKisiler();

                adapter = new KisilerAdapter(MainActivity.this,liste,kisilerdaoInterface);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) {

            }
        });

    }
}
