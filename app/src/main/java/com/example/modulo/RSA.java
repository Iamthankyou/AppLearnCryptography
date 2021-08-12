package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RSA extends AppCompatActivity {

    private EditText etPR,etQR,etER,etMR;
    private Button btnSub;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_s);

        etPR = (EditText) findViewById(R.id.etPR);
        etQR = (EditText) findViewById(R.id.etQR);
        etER = (EditText) findViewById(R.id.etER);
        etMR = (EditText) findViewById(R.id.etMR);
        btnSub = (Button) findViewById(R.id.btnSub);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnSub.setOnClickListener(ee->{
            if (etPR.getText().toString().trim().length()>0 && etQR.getText().toString().trim().length()>0 && etER.getText().toString().trim().length()>0 && etMR.getText().toString().trim().length()>0 ){
                int p = Integer.parseInt(etPR.getText().toString());
                int q = Integer.parseInt(etQR.getText().toString());
                int e = Integer.parseInt(etER.getText().toString());
                int m = Integer.parseInt(etMR.getText().toString());

                StringBuilder s = new StringBuilder();

                s.append("Sinh khóa" + "\n");
                int n = p*q;
                s.append("n=p*q="+String.valueOf(p) + "*" + String.valueOf(q) + "=" + String.valueOf(n) + "\n");

                int phi = (int) phi(n);
                s.append("phi(n) = " + String.valueOf(phi) + "\n");

                int d = (int) ext_gcd(e,phi);

                if (d<0){
                    d+=phi;
                }

                s.append("d=1/e mod phi(n) = " + "1/" + String.valueOf(e) + " mod " + "phi(" + String.valueOf(n) + ")= " + String.valueOf(d) + "\n");

                s.append("PU = {e,n} = " + String.valueOf(e) + ", " + String.valueOf(n) + "\n");
                s.append("PR = {d,n} = " + String.valueOf(d) + ", " + String.valueOf(n) + "\n");

                s.append("An mã hóa" + "\n");

                s.append("M = " + String.valueOf(m) + "\n");

                int C1 = (int)getModulo(n,d,m);
                s.append("C1 = M^d mod n = " + String.valueOf(m) + "^" + String.valueOf(d) + " mod " + String.valueOf(n) +" = " + String.valueOf(C1) + "\n");

                s.append("Ba giải mã" + "\n");

                int M = (int)getModulo(n,e,C1);
                s.append("M = C1^e mod n = " + String.valueOf(C1) + "^" + String.valueOf(e) + " mod " + String.valueOf(n) +" = " + String.valueOf(M) + "\n");

                s.append("\n");

                int C2 = (int)getModulo(n,e,m);
                s.append("C2 = M^e mod n = " + String.valueOf(m) + "^" + String.valueOf(e) + " mod " + String.valueOf(n) +" = " + String.valueOf(C2) + "\n");

                M = (int)getModulo(n,d,C2);
                s.append("M = C2^d mod n = " + String.valueOf(C2) + "^" + String.valueOf(d) + " mod " + String.valueOf(n) +" = " + String.valueOf(M) + "\n");

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

    private long ext_gcd(long a, long b){
        boolean flag = false;
        if (a<b){
            flag = true;
            long t = a;
            a = b;
            b = t;
        }

        long m =a,n=b,xm=0,ym=1,xn=1,yn=0;
//        res.append("ri="+String.valueOf(m)+", qi=null, yi="+String.valueOf(ym)+", xi="+String.valueOf(xm)+"\n");
//        res.append("ri="+String.valueOf(n)+", qi=null, yi="+String.valueOf(yn)+", xi="+String.valueOf(xn)+"\n");

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
//            res.append("ri="+String.valueOf(n)+", qi="+String.valueOf(q)+", yi="+String.valueOf(yn)+", xi="+String.valueOf(xn)+"\n");
        }

        if (flag){
            return xm;
        }

        return ym;
    }


}