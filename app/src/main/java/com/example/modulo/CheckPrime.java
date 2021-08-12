package com.example.modulo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckPrime extends AppCompatActivity {

    private Button btnSubmit;
    private EditText etN;
    private TextView tvRes;
    private StringBuilder res;
    private long tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_prime);

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
                    long n = Long.parseLong(etN.getText().toString());

                    if (isPrime(n)){
                        tvRes.setText("IS PRIME");
                    }
                    else{
                        tvRes.setText("IS'NT PRIME: "+String.valueOf(tmp));
                    }
                }
                else{
                    Toast.makeText(CheckPrime.this,"Not exists revert modulo",Toast.LENGTH_SHORT).show();
                    tvRes.setText("");
                }
            }
        });

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
                if (n%i==0){
                    tmp = i;
                }
                else{
                    tmp = i+2;
                }
                return false;
            }
        }

        return true;
    }
}