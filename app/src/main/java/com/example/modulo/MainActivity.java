package com.example.modulo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnModuloDivide;
    private Button btnModuloRevert;
    private Button btnCheckPrime,btnFactPrime,btnCaesar,btnVigenere,btnMonoalphabetic,btnFairPlay,btnDSA,btnRailFence ;
    private Button btnEquation,btnChinese, btnEuler, btnPrimitiveRoot, btnLogarithm, btnDiffieHellman, btnRSA, btnElGamal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnModuloDivide = (Button)findViewById(R.id.btnModuloDivide);
        btnMonoalphabetic = (Button)findViewById(R.id.btnMonoalphabetic);
        btnModuloRevert = (Button)findViewById(R.id.btnModuloRevert);
        btnCheckPrime = (Button)findViewById(R.id.btnCheckPrime);
        btnFactPrime = (Button)findViewById(R.id.btnFactPrime);
        btnCaesar = (Button)findViewById(R.id.btnCaesar);
        btnVigenere = (Button)findViewById(R.id.btnVigenere);
        btnFairPlay = (Button)findViewById(R.id.btnPlayFair);
        btnRailFence = (Button)findViewById(R.id.btnRailFence);
        btnEquation = (Button)findViewById(R.id.btnEquation);
        btnChinese = (Button)findViewById(R.id.btnChinese);
        btnEuler = (Button) findViewById(R.id.btnEuler);
        btnPrimitiveRoot = (Button) findViewById(R.id.btnPrimitive);
        btnLogarithm = (Button) findViewById(R.id.btnLogarithm);
        btnDiffieHellman = (Button) findViewById(R.id.btnDiffieHellman);
        btnRSA = (Button) findViewById(R.id.btnRSA);
        btnElGamal = (Button) findViewById(R.id.btnElGamal);
        btnDSA = (Button) findViewById(R.id.btnDSA);

        btnPrimitiveRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PrimitiveRoot.class);
                startActivity(intent);
            }
        });

        btnModuloDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ModuloDivide.class);
                startActivity(intent);
            }
        });

        btnEquation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChineseMainTheorem.class);
                startActivity(intent);
            }
        });


        btnModuloRevert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RevertModulo.class);
                startActivity(intent);
            }
        });

        btnChinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Chinese.class);
                startActivity(intent);
            }
        });

        btnCheckPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CheckPrime.class);
                startActivity(intent);
            }
        });

        btnLogarithm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Logarit.class);
                startActivity(intent);
            }
        });

        btnFactPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FactPrime.class);
                startActivity(intent);
            }
        });


        btnDiffieHellman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DiffieHellman.class);
                startActivity(intent);
            }
        });


        btnMonoalphabetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Monoalphabetic.class);
                startActivity(intent);
            }
        });

        btnCaesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Caesar.class);
                startActivity(intent);
            }
        });

        btnVigenere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Vigenere.class);
                startActivity(intent);
            }
        });

        btnFairPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FairPlay.class);
                startActivity(intent);
            }
        });

        btnRailFence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FenceRail.class);
                startActivity(intent);
            }
        });

        btnEuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EulerNumber.class);
                startActivity(intent);
            }
        });

        btnRSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RSA.class);
                startActivity(intent);
            }
        });

        btnElGamal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ElGamal.class);
                startActivity(intent);
            }
        });


        btnDSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DSA.class);
                startActivity(intent);
            }
        });
    }
}