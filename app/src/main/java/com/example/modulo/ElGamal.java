package com.example.modulo;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ElGamal extends AppCompatActivity {

    private EditText qE, aE, xA, xB,mE, kE;
    private Button btnSub;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_gamal);

        qE = (EditText) findViewById(R.id.etQE);
        aE = (EditText) findViewById(R.id.etAE);
        xA= (EditText) findViewById(R.id.etXA);
        xB = (EditText) findViewById(R.id.etXB);
        mE = (EditText) findViewById(R.id.etME);
        kE = (EditText) findViewById(R.id.etKE);
        btnSub = (Button) findViewById(R.id.btnSub);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnSub.setOnClickListener(ee->{
            if (qE.getText().toString().trim().length()>0 && aE.getText().toString().trim().length()>0 && xB.getText().toString().trim().length()>0 && xA.getText().toString().trim().length()>0 && mE.getText().toString().trim().length()>0 && kE.getText().toString().trim().length()>0){
                int q = Integer.parseInt(qE.getText().toString());
                int a = Integer.parseInt(aE.getText().toString());
                int xa = Integer.parseInt(xA.getText().toString());
                int xb = Integer.parseInt(xB.getText().toString());
                int m = Integer.parseInt(mE.getText().toString());
                int k = Integer.parseInt(kE.getText().toString());

                StringBuilder s = new StringBuilder();

                s.append("An tạo khóa \n");
                int ya = (int) getModulo(q,xa,a);
                s.append("ya = a^xa mod q = " + String.valueOf(a) + "^" + String.valueOf(xa) + " mod " + String.valueOf(q) + " = " + String.valueOf(ya) + "\n");
                s.append("Public key: PU = {q,a,ya} = " + String.valueOf(q) + ", " + String.valueOf(a) + ", " + String.valueOf(ya) + "\n");
                s.append("Private key: " + String.valueOf(xa)+"\n");

                s.append("Ba tạo khóa \n");
                int yb = (int) getModulo(q,xb,a);
                s.append("yb = a^xb mod q = " + String.valueOf(a) + "^" + String.valueOf(xb) + " mod " + String.valueOf(q) + " = " + String.valueOf(yb) + "\n");
                s.append("Public key: PU = {q,a,yb} = " + String.valueOf(q) + ", " + String.valueOf(a) + ", " + String.valueOf(yb) + "\n");
                s.append("Private key: " + String.valueOf(xb)+"\n");



                s.append("Ba gửi tin nhắn \n");
                int K = (int) getModulo(q,k,ya);
                s.append("K=ya^k mod q =  " + String.valueOf(ya) + "^" + String.valueOf(k) + " mod " + String.valueOf(q) +" = " + String.valueOf(K)+"\n");

                int C1 = (int) getModulo(q,k,a);
                int C2 = (int) getModulo(q,1,K*m);
                s.append("C1 = a^k mod q = " + String.valueOf(a) + "^" + String.valueOf(k) + " mod " + String.valueOf(q) + " = " + String.valueOf(C1) + "\n");
                s.append("C2 = KM mod q = " + String.valueOf(K*m) + " mod " + String.valueOf(q) + " = " + String.valueOf(C2) + "\n");

                s.append("Bản mã: (C1,C2) = " + String.valueOf(C1) + "," + String.valueOf(C2) + "\n");

                s.append("An giải mã \n");
                s.append("Bản mã: (C1,C2) = " + String.valueOf(C1) + "," + String.valueOf(C2) + "\n");

                K = (int) getModulo(q,xa,C1);
                s.append("K = C1^xa mod q = " + String.valueOf(C1) + "^" + String.valueOf(xa) + " mod " + String.valueOf(q) + " = " + String.valueOf(K) + "\n");

                int KV = (int)ext_gcd(K,q);

                if (KV<0){
                    KV+=q;
                }

                s.append("K^-1 mod q = " + String.valueOf(KV) + "\n");

                int CC2 = C2 % q;
                int M = (KV*CC2) % q;
                s.append("Giải mã: M = (C2*K^-1) % q = " + String.valueOf(M)+ "\n");

                s.append("::An gửi tin nhắn \n");
                K = (int) getModulo(q,k,yb);
                s.append("K=yb^k mod q =  " + String.valueOf(yb) + "^" + String.valueOf(k) + " mod " + String.valueOf(q) +" = " + String.valueOf(K)+"\n");

                C1 = (int) getModulo(q,k,a);
                C2 = (int) getModulo(q,1,K*m);
                s.append("C1 = a^k mod q = " + String.valueOf(a) + "^" + String.valueOf(k) + " mod " + String.valueOf(q) + " = " + String.valueOf(C1) + "\n");
                s.append("C2 = KM mod q = " + String.valueOf(K*m) + " mod " + String.valueOf(q) + " = " + String.valueOf(C2) + "\n");

                s.append("Bản mã: (C1,C2) = " + String.valueOf(C1) + "," + String.valueOf(C2) + "\n");


                s.append("Ba giải mã \n");
                s.append("Bản mã: (C1,C2) = " + String.valueOf(C1) + "," + String.valueOf(C2) + "\n");

                K = (int) getModulo(q,xb,C1);
                s.append("K = C1^xa mod q = " + String.valueOf(C1) + "^" + String.valueOf(xb) + " mod " + String.valueOf(q) + " = " + String.valueOf(K) + "\n");

                KV = (int)ext_gcd(K,q);

                if (KV<0){
                    KV+=q;
                }

                s.append("K^-1 mod q = " + String.valueOf(KV) + "\n");

                CC2 = C2 % q;
                M = (KV*CC2) % q;
                s.append("Giải mã: M = (C2*K^-1) % q = " + String.valueOf(M)+ "\n");

                tvResult.setMovementMethod(new ScrollingMovementMethod());


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