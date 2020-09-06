package com.mehmetfatih.contactsapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KisilerAdapter extends RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Kisiler> kisilerListe;
    private KisilerdaoInterface kisilerdaoInterface;

    public KisilerAdapter(Context mContext, List<Kisiler> kisilerListe, KisilerdaoInterface kisilerdaoInterface) {
        this.mContext = mContext;
        this.kisilerListe = kisilerListe;
        this.kisilerdaoInterface = kisilerdaoInterface;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{

        private TextView textViewKisiBilgi;
        private ImageView ımageViewNokta;

        public CardTasarimTutucu(View itemView) {
            super(itemView);

            textViewKisiBilgi = itemView.findViewById(R.id.textViewKisiBilgi);
            ımageViewNokta = itemView.findViewById(R.id.imageViewNokta);
        }
    }


    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kisi_card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(final CardTasarimTutucu holder, int position) {
        final Kisiler kisi = kisilerListe.get(position);

        holder.textViewKisiBilgi.setText(kisi.getKisiAd()+" - "+kisi.getKisiTel());

        holder.ımageViewNokta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(mContext,holder.ımageViewNokta);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){
                            case R.id.action_sil:
                                Snackbar.make(holder.ımageViewNokta,"Delete it?",Snackbar.LENGTH_SHORT)
                                        .setAction("Yes", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                kisilerdaoInterface.kisiSil(Integer.parseInt(kisi.getKisiId())).enqueue(new Callback<CRUDCevap>() {
                                                    @Override
                                                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                                                        tumKisiler();
                                                    }

                                                    @Override
                                                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                                                    }
                                                });
                                            }
                                        }).show();
                                return  true;
                            case R.id.action_guncelle:
                                alertGoster(kisi);
                                return  true;
                                default:
                                    return false;
                        }



                    }
                });

                popupMenu.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return kisilerListe.size();
    }


    public void alertGoster(final Kisiler kisi){

        LayoutInflater layout = LayoutInflater.from(mContext);

        View tasarim = layout.inflate(R.layout.alert_tasarim,null);

        final EditText editTextAd = tasarim.findViewById(R.id.editTextAd);
        final EditText editTextTel = tasarim.findViewById(R.id.editTextTel);

        editTextAd.setText(kisi.getKisiAd());
        editTextTel.setText(kisi.getKisiTel());

        AlertDialog.Builder ad = new AlertDialog.Builder(mContext);
        ad.setTitle("Update Contact");
        ad.setView(tasarim);
        ad.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String kisi_ad = editTextAd.getText().toString().trim();
                String kisi_tel = editTextTel.getText().toString().trim();


                kisilerdaoInterface.kisiGuncelle(Integer.parseInt(kisi.getKisiId()),kisi_ad,kisi_tel).enqueue(new Callback<CRUDCevap>() {
                    @Override
                    public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                        tumKisiler();
                    }

                    @Override
                    public void onFailure(Call<CRUDCevap> call, Throwable t) {

                    }
                });

                Toast.makeText(mContext,kisi_ad+" - "+kisi_tel, Toast.LENGTH_SHORT).show();
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
                 kisilerListe = response.body().getKisiler();

                 notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<KisilerCevap> call, Throwable t) {

            }
        });

    }


}
