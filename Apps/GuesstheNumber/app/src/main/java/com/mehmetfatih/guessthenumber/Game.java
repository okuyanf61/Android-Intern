package com.mehmetfatih.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {

    private ImageView imageView;
    private EditText loginUserName;
    private Button loginLoginButton;
    private TextView loginRegisterText;
    private int number = 0;
    private int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageView = findViewById(R.id.arrow);
        loginUserName = findViewById(R.id.loginUserName);
        loginLoginButton = findViewById(R.id.loginLoginButton);
        loginRegisterText = findViewById(R.id.loginRegisterText);
        String difficulty;
        Random random = new Random();

        if (getIntent().hasExtra("Difficulty")){
            difficulty = getIntent().getStringExtra("Difficulty");
        } else {
            throw new IllegalArgumentException("Cant get the extra: Difficulty");
        }

        switch (difficulty) {
            case "Easy":
                Log.e("1", difficulty);
                number = random.nextInt(9) + 1;
                Log.e("1", String.valueOf(number));
                break;
            case "Medium":
                Log.e("2", difficulty);
                number = random.nextInt(99) + 1;
                Log.e("2", String.valueOf(number));
                break;
            case "Hard":
                Log.e("3", difficulty);
                number = random.nextInt(999) + 1;
                Log.e("3", String.valueOf(number));
                break;
        }


        loginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loginUserName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.fill_blanks, Toast.LENGTH_SHORT).show();
                } else {
                    int guess = Integer.parseInt(loginUserName.getText().toString());

                    if ((1 < count) && (count <= 5)) {
                        count--;
                        if (guess == number) {
                            Intent intent = new Intent(Game.this, Finish.class);
                            intent.putExtra("Result", "Won");
                            intent.putExtra("Times", String.valueOf(5 - count));
                            finish();
                            startActivity(intent);
                        } else if (guess > number) {
                            imageView.setImageResource(R.drawable.ic_round_keyboard_arrow_down_24);
                            imageView.setVisibility(View.VISIBLE);
                            loginRegisterText.setText(String.valueOf(count));
                        } else {
                            imageView.setImageResource(R.drawable.ic_round_keyboard_arrow_up_24);
                            imageView.setVisibility(View.VISIBLE);
                            loginRegisterText.setText(String.valueOf(count));
                        }
                    } else if ((count == 1) && (guess == number)) {
                        Intent intent = new Intent(Game.this, Finish.class);
                        intent.putExtra("Result", "Won");
                        intent.putExtra("Times", String.valueOf(5));
                        finish();
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Game.this, Finish.class);
                        intent.putExtra("Result", "Lost");
                        intent.putExtra("Number", String.valueOf(number));
                        finish();
                        startActivity(intent);
                    }
                }

            }
        });



    }
}