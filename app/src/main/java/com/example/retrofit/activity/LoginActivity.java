package com.example.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.retrofit.R;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;

    private String usernameIpt;
    private String passwordIpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameIpt = username.getText().toString();
                passwordIpt = password.getText().toString();

                // checks for username and password

                
            }
        });
    }


}