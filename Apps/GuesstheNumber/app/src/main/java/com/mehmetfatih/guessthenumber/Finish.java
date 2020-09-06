package com.mehmetfatih.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends AppCompatActivity {

    private TextView congratulationsText, timesText, timesText3, timesText4;
    private Button revealButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        congratulationsText = findViewById(R.id.congratulationsText);
        timesText = findViewById(R.id.timesText);
        timesText3 = findViewById(R.id.timesText3);
        timesText4 = findViewById(R.id.timesText4);
        revealButton = findViewById(R.id.revealButton);
        String result, times, number;


        if (getIntent().hasExtra("Result")){
            if (getIntent().getStringExtra("Result").equals("Won")) {
                timesText3.setText(getIntent().getStringExtra("Times"));
            } else if (getIntent().getStringExtra("Result").equals("Lost")) {
                timesText3.setText(getIntent().getStringExtra("Number"));
                congratulationsText.setText(R.string.oops);
                timesText.setText(R.string.number_was);
                timesText4.setText(R.string.you_lost);
            }
        } else {
            throw new IllegalArgumentException("Cant get the extra: Difficulty");
        }

        revealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Finish.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}