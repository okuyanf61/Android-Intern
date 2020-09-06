package com.mehmetfatih.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RadioButton radioButtonEasy, radioButtonMedium, radioButtonHard;
    private RadioGroup radioGroupDifficulty;
    private Button buttonStart;
    private TextView difficultyNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButtonEasy = findViewById(R.id.radioButtonEasy);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        radioButtonHard = findViewById(R.id.radioButtonHard);
        radioGroupDifficulty = findViewById(R.id.radioGroupDifficulty);
        buttonStart = findViewById(R.id.loginButton);
        difficultyNumber = findViewById(R.id.difficultyNumber);

        radioGroupDifficulty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioButtonEasy.isChecked()) {
                    radioButtonEasy.setAlpha(1);
                    radioButtonMedium.setAlpha((float) 0.5);
                    radioButtonHard.setAlpha((float) 0.5);
                    difficultyNumber.setText("1 - 10");
                } else if (radioButtonMedium.isChecked()){
                    radioButtonEasy.setAlpha((float) 0.5);
                    radioButtonMedium.setAlpha(1);
                    radioButtonHard.setAlpha((float) 0.5);
                    difficultyNumber.setText("1 - 100");
                } else if (radioButtonHard.isChecked()){
                    radioButtonEasy.setAlpha((float) 0.5);
                    radioButtonMedium.setAlpha((float) 0.5);
                    radioButtonHard.setAlpha(1);
                    difficultyNumber.setText("1 - 1000");
                }

            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String difficulty = null;
                
                if (radioButtonEasy.isChecked()) {
                    difficulty = "Easy";
                } else if (radioButtonMedium.isChecked()){
                    difficulty = "Medium";
                } else if (radioButtonHard.isChecked()){
                    difficulty = "Hard";
                }
                
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("Difficulty", difficulty);
                startActivity(intent);

            }
        });

    }
}