package com.mehmetfatih.loginapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }

        setContentView(R.layout.activity_register);

        final FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        Button registerRegisterButton = findViewById(R.id.registerRegisterButton);
        TextView registerLoginText = findViewById(R.id.registerLoginText);
        registerLoginText.setClickable(true);
        final EditText registerUserName = findViewById(R.id.registerUserName);
        final EditText registerPassword = findViewById(R.id.registerPassword);
        final EditText registerPasswordAgain = findViewById(R.id.registerPasswordAgain);

        registerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eMail = registerUserName.getText().toString();
                String password = registerPassword.getText().toString();
                String passwordAgain = registerPasswordAgain.getText().toString();

                if (eMail.isEmpty() || password.isEmpty() || passwordAgain.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.fill_blanks, Toast.LENGTH_LONG);
                    toast.show();
                } else if (!password.equals(passwordAgain)){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.passwords_doesnt_match, Toast.LENGTH_LONG);
                    toast.show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(eMail).matches()){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.email_error, Toast.LENGTH_LONG);
                    toast.show();
                    registerUserName.requestFocus();
                } else if (password.length() < 8){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.short_password, Toast.LENGTH_LONG);
                    toast.show();
                    registerPassword.requestFocus();
                } else {
                    registerUserName.setFocusable(false);
                    registerPassword.setFocusable(false);
                    registerPasswordAgain.setFocusable(false);
                    firebaseAuth.createUserWithEmailAndPassword(eMail, registerPassword.getText().toString()).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                registerUserName.setFocusable(true);
                                registerPassword.setFocusable(true);
                                registerPasswordAgain.setFocusable(true);
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_LONG);
                                toast.show();
                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), R.string.register_successful, Toast.LENGTH_LONG);
                                toast.show();
                                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(registerIntent);
                            }

                        }
                    });
                }
            }
        });


        registerLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerLoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerLoginIntent);
            }
        });

    }
}
