package com.mehmetfatih.flagquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Recycler extends AppCompatActivity {

    private FlagViewModel mFlagViewModel;
    private FloatingActionButton fab;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FlagListAdapter adapter = new FlagListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Recycler.this, NewFlagActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        mFlagViewModel = new ViewModelProvider(this).get(FlagViewModel.class);
        mFlagViewModel.getAllFlags().observe(this, new Observer<List<Flag>>() {
            @Override
            public void onChanged(@Nullable final List<Flag> flags) {

                adapter.setFlags(flags);

            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Flag word = new Flag(data.getIntExtra(NewFlagActivity.ID, 11), data.getStringExtra(NewFlagActivity.NAME), data.getStringExtra(NewFlagActivity.PICTURE));
            mFlagViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Not Saved!",
                    Toast.LENGTH_LONG).show();
        }
    }
}