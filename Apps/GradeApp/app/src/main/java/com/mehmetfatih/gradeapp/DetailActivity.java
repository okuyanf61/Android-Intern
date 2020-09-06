package com.mehmetfatih.gradeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText editTextDers,editTextNot1,editTextNot2;
    private Notlar not;
    private NotlarInterface notlardif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        editTextDers = findViewById(R.id.editTextDers);
        editTextNot1 = findViewById(R.id.editTextNot1);
        editTextNot2 = findViewById(R.id.editTextNot2);

        toolbar.setTitle("Grade Detail");
        setSupportActionBar(toolbar);

        notlardif = ApiUtils.getNotlarDaoInterfeace();

        not  = (Notlar) getIntent().getSerializableExtra("nesne");

        editTextDers.setText(not.getDersAdi());
        editTextNot1.setText(not.getNot1());
        editTextNot2.setText(not.getNot2());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbar,"Delete it ?",Snackbar.LENGTH_SHORT)
                        .setAction("Yes", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                notlardif.notSil(Integer.parseInt(not.getNotId())).enqueue(new Callback<CRUDCevap>() {
                                    @Override
                                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                                    }
                                });



                                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                                finish();
                            }
                        })
                        .show();
                return true;
            case  R.id.action_duzenle:

                String ders_adi = editTextDers.getText().toString().trim();
                String not1 = editTextNot1.getText().toString().trim();
                String not2 = editTextNot2.getText().toString().trim();


                notlardif.notGuncelle(Integer.parseInt(not.getNotId()),ders_adi, Integer.parseInt(not1), Integer.parseInt(not2)).enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {

                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });


                startActivity(new Intent(DetailActivity.this,MainActivity.class));
                finish();
                return true;
            default:
                return false;
        }
    }
}
