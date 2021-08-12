package com.example.modulo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TreeMap;

public class FactPrime extends AppCompatActivity {

    private Button btnSubmit;
    private EditText etN;
    private TextView tvRes;
    private StringBuilder res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fact_prime);

        res = new StringBuilder();
        btnSubmit = (Button)findViewById(R.id.btnFactPrime);
        etN = (EditText)findViewById(R.id.etN);
        tvRes = (TextView)findViewById(R.id.tvRes1);
        tvRes.setVisibility(View.GONE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvRes.setVisibility(View.VISIBLE);

                if (etN.getText().toString().trim().length()>0){
                    tvRes.setText(getFact(Long.parseLong(etN.getText().toString())));
                }
                else{
                    Toast.makeText(FactPrime.this,"Not exists revert modulo",Toast.LENGTH_SHORT).show();
                    tvRes.setText("");
                }
            }
        });

    }

    private String getFact(long n){
        if (isPrime(n)){
            return "IS PRIME";
        }

        int j = 2;
        TreeMap<Integer,Integer> map = new TreeMap<>();

        while (n>1){
            while (n%j==0){
                n/=j;
                if (map.containsKey(j)){
                    map.put(j,map.get(j)+1);
                }
                else{
                    map.put(j,1);
                }
            }
            j++;
        }

        return map.keySet().toString();
    }


    private boolean isPrime(long n){
        if (n<=1){
            return false;
        }

        if (n<=3){
            return true;
        }

        if (n%2==0 || n%3==0){
            return false;
        }

        for (long i=5; i*i<n; i+=6){
            if (n%i==0 || n%(i+2)==0){
                return false;
            }
        }

        return true;
    }
}