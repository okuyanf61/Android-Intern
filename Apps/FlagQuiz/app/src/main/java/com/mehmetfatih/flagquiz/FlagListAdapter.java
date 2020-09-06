package com.mehmetfatih.flagquiz;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FlagListAdapter extends RecyclerView.Adapter<FlagListAdapter.FlagViewHolder> {

    class FlagViewHolder extends RecyclerView.ViewHolder {
        private final TextView flagItemView;
        private  Button buttonA, buttonB, buttonC, buttonD;
        private Random random = new Random();
        ArrayList<Button> buttons = new ArrayList<>();


        private FlagViewHolder(View itemView) {
            super(itemView);
            buttonA = itemView.findViewById(R.id.buttonA);
            buttonB = itemView.findViewById(R.id.buttonB);
            buttonC = itemView.findViewById(R.id.buttonC);
            buttonD = itemView.findViewById(R.id.buttonD);
            buttons.add(buttonA);
            buttons.add(buttonB);
            buttons.add(buttonC);
            buttons.add(buttonD);
            //flagItemView = buttons.get(random.nextInt(3))
            flagItemView = itemView.findViewById(R.id.textViewRC);
        }
    }

    private final LayoutInflater mInflater;
    private List<Flag> mFlags; // Cached copy of flags

    FlagListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public FlagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new FlagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FlagViewHolder holder, int position) {
        if (mFlags != null) {
            Flag current = mFlags.get(position);
            holder.flagItemView.setText(current.flagName);
        } else {
            // Covers the case of data not being ready yet.
            holder.flagItemView.setText("No Flag");
        }
    }

    void setFlags(List<Flag> flags){
        mFlags = flags;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mFlags has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mFlags != null)
            return mFlags.size();
        else return 0;
    }
}