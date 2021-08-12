package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Monoalphabetic extends AppCompatActivity {

    private Button btnSubmit1, btnSubmit2;
    private EditText etEn1,etEn2,etKey1,etKey2;
    private TextView tvRes1, tvRes2;

    private  StringBuffer encrypt(String text, String key)
    {
        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = key.charAt(((int)text.charAt(i) -
                        65) % 26);
                result.append(ch);
            }
            else
            {
                char ch = key.charAt(((int)text.charAt(i) -
                        97) % 26);
                result.append(ch);
            }
        }
        return result;
    }

    private  StringBuffer decrypt(String text, String key)
    {
        HashMap<Character,Character> map = new HashMap<>();
        String dict = "abcdefghijklmnopqrstuvwxyz";

        for (int i=0; i<key.length(); i++) {
            map.put(key.charAt(i), dict.charAt(i));
        }

        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            result.append(map.get(text.charAt(i)));
        }

        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monoalphabetic);

        btnSubmit1 = (Button) findViewById(R.id.btnSub1);
        btnSubmit2 = (Button) findViewById(R.id.btnSub2);
        etEn1 = (EditText) findViewById(R.id.etEn1);
        etEn2 = (EditText) findViewById(R.id.etEn2);
        etKey1 = (EditText) findViewById(R.id.etN);
        etKey2 = (EditText) findViewById(R.id.etKey2);
        tvRes1 = (TextView) findViewById(R.id.tvRes1);
        tvRes2 = (TextView) findViewById(R.id.tvRes2);

        btnSubmit1.setOnClickListener(f->{
            if (etEn1.getText().toString().trim().length()>0 && etKey1.getText().toString().length() == 26){
                tvRes1.setText(encrypt(etEn1.getText().toString(),etKey1.getText().toString()));
            }
        });

        btnSubmit2.setOnClickListener(f->{
            if (etEn2.getText().toString().trim().length()>0 && etKey2.getText().toString().length() == 26){
                tvRes2.setText(decrypt(etEn2.getText().toString(),etKey2.getText().toString()));
            }
        });



    }
}