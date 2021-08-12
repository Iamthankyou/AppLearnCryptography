package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Logarit extends AppCompatActivity {

    private EditText etA, etM, etN;
    private TextView tvResult;
    private Button btSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logarit);

        etA = (EditText) findViewById(R.id.etA);
        etM = (EditText) findViewById(R.id.etM);
        etN = (EditText) findViewById(R.id.etN);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btSub = (Button) findViewById(R.id.btnSub);

        // Loga(M) mod N

        btSub.setOnClickListener(e->{
            if (etA.getText().toString().trim().length()>0 && etM.getText().toString().trim().length()>0 && etN.getText().toString().trim().length()>0){
                int a = Integer.parseInt(etA.getText().toString());
                int m = Integer.parseInt(etM.getText().toString());
                int n = Integer.parseInt(etN.getText().toString());

                int phi = (int)phi(n);

                StringBuilder s = new StringBuilder();

                for (int i=1; i<=phi; i++){
                    int tmp = (int) getModulo(n,i,a);

                    if (tmp == 1 && i!=phi){
                        s.append(String.valueOf(a) + "^" + String.valueOf(i) + " mod " + String.valueOf(n) + " = " + String.valueOf(tmp) + "\n");
                        s.append("NOT LOG" + "\n");
                        break;
                    }
                    else if (tmp == m){
                        s.append(String.valueOf(a) + "^" + String.valueOf(i) + " mod " + String.valueOf(n) + " = " + String.valueOf(tmp) + "\n");
                        s.append("Log is + " + String.valueOf(i));
                        break;
                    }
                    else{
                        s.append(String.valueOf(a) + "^" + String.valueOf(i) + " mod " + String.valueOf(n) + " = " + String.valueOf(tmp) + "\n");
                    }
                }

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