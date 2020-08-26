package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Button BtnNewGame = findViewById(R.id.buttonNewGame);
        Button BtnExit = findViewById(R.id.buttonExit);
        TextView scoreView = findViewById(R.id.p1Score);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        scoreView.setText(extras.getString("Score"));
        BtnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        BtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

}