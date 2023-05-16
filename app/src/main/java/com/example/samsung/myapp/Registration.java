package com.example.samsung.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MapActivity.class);
        setContentView(R.layout.activity_registration);
        Button b = findViewById(R.id.button);
        EditText login = findViewById(R.id.editTextDesc);
        EditText password = findViewById(R.id.editTextSecondName);
        EditText phone = findViewById(R.id.editTextPhone3);
        EditText tg = findViewById(R.id.editTextTG);
        EditText email = findViewById(R.id.editTextEmail);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("login",login.getText().toString());
                intent.putExtra("password",password.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("email",email.getText().toString());
                intent.putExtra("tg",tg.getText().toString());
                startActivity(intent);
            }
        });
    }
}