package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.TreeSet;

public class PrimitiveRoot extends AppCompatActivity {

    private EditText etN,etAA;
    private Button btnSubmit;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primitive_root);

        etN = (EditText) findViewById(R.id.etN);
        etAA = (EditText) findViewById(R.id.etAA);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvRes = (TextView) findViewById(R.id.tvRes);

        btnSubmit.setOnClickListener(e->{
            int n = Integer.parseInt(etN.getText().toString());
            int a = Integer.parseInt(etAA.getText().toString());
            int euler = (int) phi((long)n);

            TreeSet<Integer> divisor = divisor(euler);
            StringBuilder s = new StringBuilder();

            boolean flag = true;

            s.append("euler = " + String.valueOf(euler) + "\n");
            for (Integer i:divisor){
                long tmp = getModulo(n,i,a);

                if (tmp == 1 && i!=euler){
                    s.append(String.valueOf(a) + "^" + String.valueOf(i) + " mod " + String.valueOf(n) +" = " + String.valueOf(tmp) + "\n");
                    s.append("NOT PRIMITIVE ROOT");
                    flag = false;
                    break;
                }
                else{
                    s.append(String.valueOf(a) + "^" + String.valueOf(i) + " mod " + String.valueOf(n) +" = " + String.valueOf(tmp) + "\n");
                }
            }

            if (flag){
                s.append("PRIMITIVE ROOT");
            }

            tvRes.setText(s.toString());
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

    private TreeSet<Integer> divisor(int n){
        TreeSet<Integer> set = new TreeSet<Integer>();

        for (int i=1; i*i<=n; i++){
            if (n%i==0){
                set.add(i);
                set.add(n/i);
            }
        }

        return set;
    }
}