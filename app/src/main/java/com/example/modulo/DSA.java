package com.example.modulo;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DSA extends AppCompatActivity {

    private EditText etPD, etQD, etHD, etXAD, etKD, etHMD, etGD;
    private Button btnSub;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_s);

        etPD = (EditText) findViewById(R.id.etPD);
        etQD = (EditText) findViewById(R.id.etQD);
        etHD = (EditText) findViewById(R.id.etHD);
        etXAD = (EditText) findViewById(R.id.etXAD);
        etKD = (EditText) findViewById(R.id.etKD);
        etHMD = (EditText) findViewById(R.id.etHMD);
        etGD = (EditText) findViewById(R.id.etGD);
        btnSub = (Button) findViewById(R.id.btnSub);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnSub.setOnClickListener(e->{
            int p = Integer.parseInt(etPD.getText().toString());
            int q = Integer.parseInt(etQD.getText().toString());
            int h = Integer.parseInt(etHD.getText().toString());
            int xa = Integer.parseInt(etXAD.getText().toString());
            int k  = Integer.parseInt(etKD.getText().toString());
            int hm = Integer.parseInt(etHMD.getText().toString());
            int g = Integer.parseInt(etGD.getText().toString());

            StringBuilder s = new StringBuilder();

            g = (int) getModulo(p,(p-1)/q,h);

            s.append("g = h^((p-1)/q) mod h = " + String.valueOf(h) + "^" + String.valueOf((p-1)/q) + " mod " + String.valueOf(q) + " = " + String.valueOf(g) + "\n");

            s.append("Người dùng \n");
            s.append("Private key: " + String.valueOf(xa) + "\n");



            int y = (int) getModulo(p,xa,g);
            s.append("Public key: y = g^x mod p " + String.valueOf(y)+"\n");
            s.append("Số bí mật k: " + String.valueOf(k) + "\n");

            s.append("Ký chữ ký số: " + "\n");

            int r = (int) (getModulo(p,k,g));
            r= r%q;

            s.append("r = (g^k mod p) mod q = " + String.valueOf(r) + "\n");

            int ps1 = (int) ext_gcd(k,q);
            if (ps1<0){
                ps1+=q;
            }

            int ps2 = (hm + xa*r) % q;

            int ss = (ps1*ps2) % q;

            s.append("s=[k^-1(HM + xr)] mod q  = " + String.valueOf(ss) + "\n" );

            s.append("Chữ ký số: (r,s) " + String.valueOf(r) + ", " + String.valueOf(ss) + "\n");

            s.append("Xác minh chữ ký " + "\n");
            int w = (int) ext_gcd(ss,q);
            if (w<0){
                w+=q;
            }

            s.append("w = (s')^-1 mod q = " + " "  + String.valueOf(w) + "\n");

            int u1 = (hm*w) %q;
            int u2 = (r*w) %q;

            int v1 = (int) getModulo(p,u1,g);
            int v2 = (int) getModulo(p,u2,y);

            int vv = (v1*v2) %p;
            int v = vv%q;

            s.append("u1= hm * w mod q = " + String.valueOf(u1) + "\n");
            s.append("u2= (r* w) mod q = " + String.valueOf(u2) + "\n");

            s.append("v = (g^u1*y^u2 mod p) mod q = " + String.valueOf(v) + "\n");

            tvResult.setMovementMethod(new ScrollingMovementMethod());

            tvResult.setText(s.toString());



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