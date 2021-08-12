package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChineseMainTheorem extends AppCompatActivity {

    public static class CRT
    {
        public static int[] euclidean(int a, int b)
        {
            if(b > a)
            {
                int[] coeffs = euclidean(b, a);
                int[] output = {coeffs[1], coeffs[0]};
                return output;
            }

            int q = a/b;
            int r = a -q*b;

            if(r == 0)
            {
                int[] output = {0, 1};
                return output;
            }

            int[] next = euclidean(b, r);

            int[] output = {next[1], next[0] - q*next[1]};
            return output;
        }

        public static int leastPosEquiv(int a, int m)
        {
            if(m < 0)
                return leastPosEquiv(a, -1*m);
            if(a >= 0 && a < m)
                return a;

            if(a < 0)
                return -1*leastPosEquiv(-1*a, m) + m;

            int q = a/m;

            return a - q*m;
        }

    }

    private Button btnSubmit;
    private EditText etA1,etA2,etA3, etM1, etM2, etM3;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_main_theorem);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        etM1 = (EditText) findViewById(R.id.etC1);
        etM2 = (EditText) findViewById(R.id.etC2);
        etM3 = (EditText) findViewById(R.id.etC3);
        etA1 = (EditText) findViewById(R.id.etM1);
        etA2 = (EditText) findViewById(R.id.etM2);
        etA3 = (EditText) findViewById(R.id.etM3);
        tvRes = (TextView) findViewById(R.id.tvRes);

        tvRes.setText("");
        StringBuilder s = new StringBuilder();

        btnSubmit.setOnClickListener(f->{
            if (etM1.getText().toString().trim().length()>0 && etM1.getText().toString().trim().length()>0 && etM2.getText().toString().trim().length()>0 && etM3.getText().toString().trim().length()>0 && etA1.getText().toString().trim().length()>0 && etA2.getText().toString().trim().length()>0 && etA3.getText().toString().trim().length()>0){
                tvRes.setText("");
                s.setLength(0);

                int m1 = Integer.parseInt(etM1.getText().toString());
                int m2 = Integer.parseInt(etM2.getText().toString());
                int m3 = Integer.parseInt(etM3.getText().toString());
                int a1 = Integer.parseInt(etA1.getText().toString());
                int a2 = Integer.parseInt(etA2.getText().toString());
                int a3 = Integer.parseInt(etA3.getText().toString());

                s.append("m1= " + etM1.getText().toString() + ", m2= " + etM2.getText().toString() + ", m3= " + etM3.getText().toString()+ "\n" );
                s.append("a1= " + etA1.getText().toString() + ", a2= " + etA2.getText().toString() + ", a3= " + etA3.getText().toString()+ "\n" );

                int M = m1*m2*m3;

                s.append("M=m1*m2*m3= " + Integer.valueOf(M)+"\n");

                int M1 = m2*m3;
                int M2 = m1*m3;
                int M3 = m2*m1;

                s.append("M1 = m2*m3 = " + Integer.valueOf(M1) + ", M2 = m1*m3 = " + Integer.valueOf(M2) + ", M3 = m1*m2 = " + Integer.valueOf(M3) + "\n");

                long M1V = gcd(M1,m1);

                if (M1V==1){
                    M1V = ext_gcd(M1,m1);
                    if (M1V<0){
                        M1V+=m1;
                    }
                }
                else{
                    tvRes.setText("");
                    Toast.makeText(ChineseMainTheorem.this,"Not exists revert modulo",Toast.LENGTH_SHORT).show();
                }

                s.append("1/M1 mod m1 = " + Long.valueOf(M1V) + "\n");

                long M2V = gcd(M2,m2);

                if (M2V==1){
                    M2V = ext_gcd(M2,m2);
                    if (M2V<0){
                        M2V+=m2;
                    }
                }
                else{
                    tvRes.setText("");
                    Toast.makeText(ChineseMainTheorem.this,"Not exists revert modulo",Toast.LENGTH_SHORT).show();
                }

                s.append("1/M2 mod m2 = " + Long.valueOf(M2V) + "\n");

                long M3V = gcd(M3,m3);

                if (M3V==1){
                    M3V = ext_gcd(M3,m3);
                    if (M3V<0){
                        M3V+=m3;
                    }
                }
                else{
                    tvRes.setText("");
                    Toast.makeText(ChineseMainTheorem.this,"Not exists revert modulo",Toast.LENGTH_SHORT).show();
                }

                s.append("1/M3 mod m3 = " + Long.valueOf(M3V) + "\n");

                long c1 = M1*M1V;
                long c2 = M2*M2V;
                long c3 = M3*M3V;

                s.append("c1 = M1 * (1/M1 mod m1) = " + Long.valueOf(c1)+ "\n");
                s.append("c2 = M2 * (1/M2 mod m2) = " + Long.valueOf(c2)+ "\n");
                s.append("c3 = M3 * (1/M3 mod m3) = " + Long.valueOf(c3)+ "\n");

                long x = (a1*c1+a2*c2+a3*c3) % M;
                s.append("x=(a1*c1+a2*c2+a3+c2) mod M = " + Long.valueOf(x));

                tvRes.setText(s.toString());
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
        }

        if (flag){
            return xm;
        }

        return ym;
    }

}