package com.example.retrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.RetroFitDatabase;
import com.example.retrofit.model.User;

public class LoginActivity extends AppCompatActivity {
    TextView invalidIpt;
    EditText username;
    EditText password;
    Button login;

    private String usernameIpt;
    private String passwordIpt;

    private RetroFitDatabase retroFitDatabase;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        retroFitDatabase = RetroFitDatabase.getInstance(this);
        retroFitDatabase.getUsersAPIData();

        invalidIpt = findViewById(R.id.invalidInp);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
                password.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
                invalidIpt.setText("");
                usernameIpt = username.getText().toString();
                passwordIpt = password.getText().toString();
                // checks for username and password
                if (usernameIpt.equals("") || passwordIpt.equals("")) {
                    if (usernameIpt.equals("")){
                        username.setError("Username cannot be blank!");
                        username.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.red));
                    }
                    if (passwordIpt.equals("")) {
                        password.setError("Password cannot be blank!");
                        password.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.red));
                    }
                    return;
                } else {
                    User user = retroFitDatabase.user().getUser(passwordIpt, usernameIpt);
                    if (user != null){
                        Log.i("LoginActivity", user.toString());
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("userId", user.getId());
                        i.putExtra("username", user.getUsername());
                        startActivity(i);
                    } else {
                        invalidIpt.setText("Wrong Credentials");
                    }
                }

            }
        });
    }
}