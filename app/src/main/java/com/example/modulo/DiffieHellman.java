package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiffieHellman extends AppCompatActivity {

    private EditText etAD, etQD, etXA, etXB;
    private Button btnSub;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffie_hellman);

        etAD = (EditText) findViewById(R.id.etAD);
        etQD = (EditText) findViewById(R.id.etPR);
        etXA = (EditText) findViewById(R.id.etXA);
        etXB = (EditText) findViewById(R.id.etXB);
        btnSub = (Button) findViewById(R.id.btnSub);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnSub.setOnClickListener(e->{
            if (etAD.getText().toString().trim().length()>0 && etQD.getText().toString().trim().length()>0 && etXA.getText().toString().trim().length()>0&& etXB.getText().toString().trim().length()>0){
                int a  = Integer.parseInt(etAD.getText().toString());
                int q  = Integer.parseInt(etQD.getText().toString());
                int xa  = Integer.parseInt(etXA.getText().toString());
                int xb  = Integer.parseInt(etXB.getText().toString());

                int phi = (int) phi(q);
                StringBuilder s = new StringBuilder();
                s.append("phi(q) = " + String.valueOf(phi) + "\n");

                int ya = (int) getModulo(q,xa,a);
                int yb = (int) getModulo(q,xb,a);

                s.append("ya = a^xa mod q \n = " + String.valueOf(a) + "^" + String.valueOf(xa) + " mod " + String.valueOf(q) + "=" + String.valueOf(ya) + "\n");
                s.append("yb = a^xb mod q \n = " + String.valueOf(a) + "^" + String.valueOf(xb) + " mod " + String.valueOf(q) + "=" + String.valueOf(yb) + "\n");

                int kab = (int) getModulo(q,xa,yb);
                int kba = (int) getModulo(q,xb,ya);

                s.append("kab = yb^xa mod q \n = " + String.valueOf(yb) + "^" + String.valueOf(xa) + " mod " + String.valueOf(q) + "=" + String.valueOf(kab) + "\n");
                s.append("kba = ya^xb mod q \n = " + String.valueOf(yb) + "^" + String.valueOf(xb) + " mod " + String.valueOf(q) + "=" + String.valueOf(kab) + "\n");

                tvResult.setText(s.toString());
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

    private long getModulo(long n, long m, long a){
        if (m==0){
            return 1;
        }

        if (a==0){
            return 0;
        }

        long tmp;

        if (m%2==0){
            long x1 = m/2;
            tmp = getModulo(n,m/2,a);
            long tmp1 = tmp;
            tmp = (tmp*tmp)%n;
//            res.append("$$\\color{white}{"+String.valueOf(a)+"^{"+String.valueOf(m)+"}"+"\\; mod \\;"+String.valueOf(n)+"\\; = (" + String.valueOf(a)+"^{"+String.valueOf(x1)+"} \\;\\times\\;"+ String.valueOf(a)+"^{"+String.valueOf(x1)+"})\\;mod\\;"+ String.valueOf(n)+"\\;=\\;"+String.valueOf(tmp)+"}$$");
//            res.append(String.valueOf(a)+"\\{^}"+String.valueOf(m)+" mod "+String.valueOf(n)+"= ("+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+"x"+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+")"+"%"+String.valueOf(n)+"="+String.valueOf(tmp)+"\n");
        }
        else{
            long x1 = m/2;
            tmp = getModulo(n,m/2,a);
            long tmp1 = tmp;
            tmp = (a*tmp*tmp)%n;
//            res.append("$$\\color{white}{"+String.valueOf(a)+"^{"+String.valueOf(m)+"}"+"\\; mod \\;"+String.valueOf(n)+"\\; = (" + String.valueOf(a)+"^{"+String.valueOf(x1)+"} \\;\\times\\;"+ String.valueOf(a)+"^{"+String.valueOf(x1)+"}\\;\\times\\;"+String.valueOf(a)+")\\;mod\\;"+ String.valueOf(n)+"\\;=\\;"+String.valueOf(tmp)+"}$$");
//            res.append("$$\\color{white}{x^2}$$");
//            res.append("$$\\color{white}{"+String.valueOf(a)+"^"+String.valueOf(m)+" mod "+String.valueOf(n)+"= ("+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+"x"+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+"x"+String.valueOf(a)+")"+"%"+String.valueOf(n)+"="+String.valueOf(tmp)+"\n"+"}$$");
        }


        return tmp;
    }

}