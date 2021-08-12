package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EulerNumber extends AppCompatActivity {

    private EditText etN;
    private Button btnSubmit;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_euler_number);

        etN = (EditText) findViewById(R.id.etN);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvRes = (TextView) findViewById(R.id.tvRes);

        btnSubmit.setOnClickListener(e->{
            if (etN.getText().toString().trim().length()>0){
                long res = phi(Long.parseLong(etN.getText().toString()));
                tvRes.setText(String.valueOf(res));
            }
        });
    }

    private long phi(long n) {
        long  res = n;
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                res -= res / i;
            }
        }
        if (n != 1) {
            res -= res / n;
        }

        return res;
    }
}