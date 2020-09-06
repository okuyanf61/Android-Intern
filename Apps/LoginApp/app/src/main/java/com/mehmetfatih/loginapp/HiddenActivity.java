package com.mehmetfatih.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class HiddenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);
        final TextView number = (TextView) findViewById(R.id.numberText);
        Random random = new Random();
        int randomNumber = random.nextInt(11) + 10;
        number.setText(Integer.toString(randomNumber));

        final Button reveal = findViewById(R.id.revealButton);
        final TextView congrats = findViewById(R.id.congratulationsText);
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        reveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentNumber = Integer.parseInt((String) number.getText());
                if (currentNumber > 1){
                    number.setText(Integer.toString(currentNumber - 1));
                }
                else {
                    if (currentNumber != 0){
                        number.setText(Integer.toString(currentNumber - 1));
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.congratulations, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                        congrats.setTextColor(getResources().getColor(R.color.white));
                        reveal.setText(R.string.logout);
                    } else if (reveal.getText().toString().equals("Log Out")){
                        firebaseAuth.signOut();
                        reveal.setText("Quit");
                    } else {
                        System.exit(1);
                    }

                }
            }
        });
    }
}