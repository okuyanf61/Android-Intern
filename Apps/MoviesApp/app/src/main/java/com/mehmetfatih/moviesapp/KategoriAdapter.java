package com.mehmetfatih.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Kategoriler> kategorilerListe;

    public KategoriAdapter(Context mContext, List<Kategoriler> kategorilerListe) {
        this.mContext = mContext;
        this.kategorilerListe = kategorilerListe;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView kategori_card;
        private TextView textViewKategoriAd;
        public CardTasarimTutucu(View itemView) {
            super(itemView);
            kategori_card = itemView.findViewById(R.id.kategori_card);
            textViewKategoriAd = itemView.findViewById(R.id.textViewKategoriAd);
        }
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {
        final Kategoriler kategori = kategorilerListe.get(position);

        holder.textViewKategoriAd.setText(kategori.getKategoriAd());

        holder.kategori_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ıntent = new Intent(mContext, MovieActivity.class);

                ıntent.putExtra("kategoriNesne",kategori);

                mContext.startActivity(ıntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return kategorilerListe.size();
    }




}
