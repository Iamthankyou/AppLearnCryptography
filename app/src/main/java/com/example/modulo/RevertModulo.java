package com.example.modulo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RevertModulo extends AppCompatActivity {
    private EditText etN,etA;
    private Button btnSubmit;
    private TextView tvRes;
    private StringBuilder res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revert_modulo);

        res = new StringBuilder();
        etN = (EditText)findViewById(R.id.etN);
        etA = (EditText)findViewById(R.id.etA);
        btnSubmit = (Button)findViewById(R.id.btnFactPrime);
        tvRes = (TextView)findViewById(R.id.tvRes1);

        tvRes.setVisibility(View.GONE);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etN.getText().toString().trim().length()>0 && etA.getText().toString().length()>0){
                    long N = Long.parseLong(etN.getText().toString());
                    long A = Long.parseLong(etA.getText().toString());

                    res = new StringBuilder();

                    long xgcd = gcd(N,A);

                    if (xgcd==1){
                        long result = ext_gcd(A,N);
                        tvRes.setVisibility(View.VISIBLE);
                        if (result<0){
                            result+=N;
                        }
                        tvRes.setText("X="+String.valueOf(result)+"\n"+res);
                    }
                    else{
                        tvRes.setText("");
                        Toast.makeText(RevertModulo.this,"Not exists revert modulo",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    tvRes.setText("");
                    Toast.makeText(RevertModulo.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private long gcd(long a, long b){
        while (b!=0){
            long r = a%b;
            a=b;
            b=r;
        }

        return a;
    }

    private long ext_gcd(long a, long b){
        boolean flag = false;
        if (a<b){
            flag = true;
            long t = a;
            a = b;
            b = t;
        }

        long m =a,n=b,xm=0,ym=1,xn=1,yn=0;
        res.append("ri="+String.valueOf(m)+", qi=null, yi="+String.valueOf(ym)+", xi="+String.valueOf(xm)+"\n");
        res.append("ri="+String.valueOf(n)+", qi=null, yi="+String.valueOf(yn)+", xi="+String.valueOf(xn)+"\n");

        while (n!=0){
            long q = m/n;
            long r = m%n;
            long xr = xm-q*xn;
            long yr = ym-q*yn;
            m=n;
            n=r;
            xm=xn;
            ym=yn;
            xn=xr;
            yn=yr;
            res.append("ri="+String.valueOf(n)+", qi="+String.valueOf(q)+", yi="+String.valueOf(yn)+", xi="+String.valueOf(xn)+"\n");
        }

        if (flag){
            return xm;
        }

        return ym;
    }
}