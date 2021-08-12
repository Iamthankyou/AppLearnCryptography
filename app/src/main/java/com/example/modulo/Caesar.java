package com.example.modulo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Caesar extends AppCompatActivity {

    private Button btnEn,btnDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar);

        btnEn = (Button) findViewById(R.id.btnEn);
        btnDe = (Button) findViewById(R.id.btnDe);

        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EncryptCaesar.class);
                startActivity(intent);
            }
        });

        btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DecryptCaesar.class);
                startActivity(intent);
            }
        });


    }
}