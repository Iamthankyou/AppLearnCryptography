package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Vigenere extends AppCompatActivity {

    private String generateKey1(String str, String key)
    {
        int x = str.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key+=(key.charAt(i));
        }
        return key;
    }

    private String generateKey2(String str, String key)
    {
        int x = str.length();

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key+=(str.charAt(i));
        }
        return key;

    }

    private String cipherText(String str, String key)
    {
        String cipher_text="";

        for (int i = 0; i < str.length(); i++)
        {
            int x = (str.charAt(i) + key.charAt(i)) %26;

            x += 'a';

            cipher_text+=(char)(x);
        }
        return cipher_text;
    }

    static String originalText(String cipher_text, String key)
    {
        String orig_text="";

        for (int i = 0 ; i < cipher_text.length() &&
                i < key.length(); i++)
        {
            int x = (cipher_text.charAt(i) -
                    key.charAt(i) + 26) %26;

            x += 'a';
            orig_text+=(char)(x);
        }
        return orig_text;
    }

    private Button btnSubmit1, btnSubmit2, btnGen1, btnGen2, btnGen12,btnGen22;
    private EditText etEn1,etEn2, etKey1, etKey2;
    private TextView tvRes1, tvRes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere);

        btnSubmit1 = (Button) findViewById(R.id.btnSub11);
        btnSubmit2 = (Button) findViewById(R.id.btnSub22);
        etEn1 = (EditText) findViewById(R.id.etEn1);
        etEn2 = (EditText) findViewById(R.id.etEn2);
        etKey1 = (EditText) findViewById(R.id.etN);
        etKey2 = (EditText) findViewById(R.id.etKey2);
        tvRes1 = (TextView) findViewById(R.id.tvRes1);
        tvRes2 = (TextView) findViewById(R.id.tvRes2);
        btnGen1 = (Button) findViewById(R.id.btnGen1);
        btnGen2 = (Button) findViewById(R.id.btnGen2);
        btnGen12 = (Button) findViewById(R.id.btnGen12);
        btnGen22 = (Button) findViewById(R.id.btnGen22);

        btnGen1.setOnClickListener(f->{
            if (etEn1.getText().toString().trim().length()>0 && etKey1.getText().toString().trim().length()>0){
                etKey1.setText(generateKey1(etEn1.getText().toString(),etKey1.getText().toString()));
            }
        });

        btnGen2.setOnClickListener(f->{
            if (etEn2.getText().toString().trim().length()>0 && etKey2.getText().toString().trim().length()>0){
                etKey2.setText(generateKey1(etEn2.getText().toString(),etKey2.getText().toString()));
            }
        });

        btnGen12.setOnClickListener(f->{
            if (etEn1.getText().toString().trim().length()>0 && etKey1.getText().toString().trim().length()>0){
                etKey1.setText(generateKey2(etEn1.getText().toString(),etKey1.getText().toString()));
            }
        });

        btnGen22.setOnClickListener(f->{
            if (etEn2.getText().toString().trim().length()>0 && etKey2.getText().toString().trim().length()>0){
                etKey2.setText(generateKey2(etEn2.getText().toString(),etKey2.getText().toString()));
            }
        });


        btnSubmit1.setOnClickListener(f->{
            if (etEn1.getText().toString().trim().length()>0 && etKey1.getText().toString().trim().length()>0){
                tvRes1.setText(cipherText(etEn1.getText().toString(),etKey1.getText().toString()));
            }
        });

        btnSubmit2.setOnClickListener(f->{
            if (etEn2.getText().toString().trim().length()>0 && etKey2.getText().toString().trim().length()>0){
                tvRes2.setText(originalText(etEn2.getText().toString(),etKey2.getText().toString()));
            }
        });

    }
}