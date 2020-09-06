package com.mehmetfatih.loginapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        }

        setContentView(R.layout.activity_login);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent loginIntent = new Intent(LoginActivity.this, HiddenActivity.class);
            startActivity(loginIntent);
        } else {
            setContentView(R.layout.activity_login);
            final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            final Button loginLoginButton = findViewById(R.id.loginLoginButton);
            final EditText loginUserName = findViewById(R.id.loginUserName);
            final EditText loginPassword = findViewById(R.id.loginPassword);


            loginLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String eMail = loginUserName.getText().toString();
                    String password = loginPassword.getText().toString();

                    if (eMail.isEmpty() || password.isEmpty()){
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.fill_blanks, Toast.LENGTH_LONG);
                        toast.show();
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(eMail).matches()){
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.email_error, Toast.LENGTH_LONG);
                        toast.show();
                        loginUserName.requestFocus();
                    } else if (password.length() < 8){
                        Toast toast = Toast.makeText(getApplicationContext(), R.string.short_password, Toast.LENGTH_LONG);
                        toast.show();
                        loginPassword.requestFocus();
                    } else {
                        firebaseAuth.signInWithEmailAndPassword(loginUserName.getText().toString(), loginPassword.getText().toString());
                        if (firebaseAuth.getCurrentUser() != null) {
                            Intent loginIntent = new Intent(LoginActivity.this, HiddenActivity.class);
                            startActivity(loginIntent);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), R.string.server_error, Toast.LENGTH_LONG);
                            toast.show();
                        }

                    }

                }
            });

            final TextView loginRegisterText = findViewById(R.id.loginRegisterText);
            loginRegisterText.setClickable(true);
            loginRegisterText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent loginRegisterIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(loginRegisterIntent);
                }
            });
        }


    }
}
