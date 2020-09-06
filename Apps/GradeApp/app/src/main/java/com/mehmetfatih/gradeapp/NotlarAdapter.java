package com.mehmetfatih.gradeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Notlar> notlarListe;

    public NotlarAdapter(Context mContext, List<Notlar> notlarListe) {
        this.mContext = mContext;
        this.notlarListe = notlarListe;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {
        final Notlar not = notlarListe.get(position);

        holder.textViewDers.setText(not.getDersAdi());
        holder.textViewNot1.setText(String.valueOf(not.getNot1()));
        holder.textViewNot2.setText(String.valueOf(not.getNot2()));

        holder.not_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext, DetailActivity.class);

                intent.putExtra("nesne",not);

                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notlarListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewDers;
        private TextView textViewNot1;
        private TextView textViewNot2;
        private CardView not_card;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewDers = itemView.findViewById(R.id.textViewDers);
            textViewNot1 = itemView.findViewById(R.id.textViewNot1);
            textViewNot2 = itemView.findViewById(R.id.textViewNot2);
            not_card = itemView.findViewById(R.id.not_card);
        }
    }
}
