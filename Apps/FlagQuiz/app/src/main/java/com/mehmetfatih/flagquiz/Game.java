package com.mehmetfatih.flagquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;

public class Game extends AppCompatActivity {


    private Button buttonA, buttonB, buttonC, buttonD;
    private FloatingActionButton fab;
    private ImageView imageView;
    private Random random = new Random();
    private List<Flag> questions;
    private List<Flag> wrongChoices;
    private int ID;
    private int randomButton;
    private int questionCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        fab = findViewById(R.id.floatingActionButton);
        imageView = findViewById(R.id.imageView);


        questionCounter = 1;

        questions = FlagRoomDatabase.getDatabase(getBaseContext()).flagDAO().fiveQuestions();
        randomButton = random.nextInt(3);

        ID = questions.get(0).flagId;
        Log.e("5", String.valueOf(ID) + " --- " + questions.get(0).flagName.toString());
        wrongChoices = FlagRoomDatabase.getDatabase(getBaseContext()).flagDAO().randomThreeWrong(ID);

        Log.e("6", wrongChoices.toString());
        Log.e("7", String.valueOf(randomButton));
        imageView.setImageResource(getResources().getIdentifier(questions.get(0).flagPicture, "drawable", getPackageName()));
        switch (randomButton) {
            case 0:
                buttonA.setText(questions.get(0).flagName);
                buttonB.setText(wrongChoices.get(0).flagName);
                buttonC.setText(wrongChoices.get(1).flagName);
                buttonD.setText(wrongChoices.get(2).flagName);
                break;
            case 1:
                buttonB.setText(questions.get(0).flagName);
                buttonA.setText(wrongChoices.get(0).flagName);
                buttonC.setText(wrongChoices.get(1).flagName);
                buttonD.setText(wrongChoices.get(2).flagName);
                break;
            case 2:
                buttonC.setText(questions.get(0).flagName);
                buttonB.setText(wrongChoices.get(0).flagName);
                buttonA.setText(wrongChoices.get(1).flagName);
                buttonD.setText(wrongChoices.get(2).flagName);
                break;
            case 3:
                buttonD.setText(questions.get(0).flagName);
                buttonB.setText(wrongChoices.get(0).flagName);
                buttonC.setText(wrongChoices.get(1).flagName);
                buttonA.setText(wrongChoices.get(2).flagName);
                break;
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, Result.class);
                finish();
                startActivity(intent);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, Recycler.class);
                startActivity(intent);
            }
        });

    }

}