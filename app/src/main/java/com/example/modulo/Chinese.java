package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.TreeMap;

public class Chinese extends AppCompatActivity {

    private EditText etA, etM, etN;
    private Button btnSub;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);

        etA = (EditText) findViewById(R.id.etA);
        etM = (EditText) findViewById(R.id.etM);
        etN = (EditText) findViewById(R.id.etN);

        btnSub = (Button) findViewById(R.id.btnSub);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnSub.setOnClickListener(e->{
            if (etA.getText().toString().trim().length()>0 && etM.getText().toString().trim().length()>0 && etN.getText().toString().trim().length()>0){
                long a = Long.parseLong(etA.getText().toString());
                long m = Long.parseLong(etM.getText().toString());
                long n = Long.parseLong(etN.getText().toString());
                // a^m % n

                long M = n;
                StringBuilder s = new StringBuilder();
                s.append("M= " + Long.valueOf(M) + "\n" );

                // n m a
                //

                ArrayList<Long> mi = getFact(M);
                ArrayList<Long> ai = new ArrayList<Long>(mi.size());
//                System.out.print("===>> " + mi.size());

                for (int i=0; i<mi.size(); i++){
                    s.append("m" + Integer.valueOf(i+1) + "= " + Long.valueOf(mi.get(i)) + ", ");
                }

                s.append("\n");

                for (int i=0; i<mi.size(); i++){
                    ai.add((long)0);
                }

                System.out.println("=====> " + ai.size());
                for (int i = 0; i<mi.size(); i++){
                    ai.set(i,getModulo(mi.get(i), m,a));
                }


                for (int i=0; i<mi.size(); i++){
                    s.append("a" + Integer.valueOf(i+1) + "= "+ Long.valueOf(a)+ "^" + Long.valueOf(m) + " mod " + "m" + Integer.valueOf(i+1) + "= " + Long.valueOf(ai.get(i)) + "\n");
                }

                s.append("\n");

                ArrayList<Long> Mi = new ArrayList<Long>(ai.size());

                for (int i=0; i<mi.size(); i++){
                    Mi.add((long)0);
                }


                for (int i=0; i<Mi.size(); i++){
                    Mi.set(i,M/mi.get(i));
                    s.append("M" + Integer.valueOf(i+1) + "= M/m" + Integer.valueOf(i+1) + " = " +  Mi.get(i) + "\n");
                }

                s.append("\n");


                ArrayList<Long> MiV = new ArrayList<Long>(ai.size());

                for (int i=0; i<mi.size(); i++){
                    MiV.add((long)0);
                }


                for (int i=0; i<Mi.size(); i++){

                    long tmp = gcd(Mi.get(i), mi.get(i));

                    if (tmp==1){
                        long result = ext_gcd(Mi.get(i),mi.get(i));
                        if (result<0){
                            result+=mi.get(i);
                        }

                        MiV.set(i,result);
                    }
                    else{
                        s.append("CANNOT MODULO REVERT");
                    }

                    s.append("1/M" + Integer.valueOf(i+1) + " mod m" +Integer.valueOf(i+1) +" = " + MiV.get(i) + "\n");

                }

                s.append("\n");

                ArrayList<Long> ci = new ArrayList<Long>(ai.size());

                for (int i=0; i<mi.size(); i++){
                    ci.add((long)0);
                }


                for (int i=0; i<ci.size(); i++){
                    ci.set(i,Mi.get(i)*MiV.get(i));
                    s.append("c"+Integer.valueOf(i+1) +"= M" + Integer.valueOf(i+1) + "* (1/M" + Integer.valueOf(i+1) + " mod m" + Integer.valueOf(i+1)+")" +"= " + ci.get(i) + "\n");

                }

                s.append("\n");

                long sum = 0;
                for (int i=0; i<ci.size(); i++){
                    sum += ai.get(i) * ci.get(i);
                }

                s.append("x= (");
                for (int i=0; i<ci.size(); i++){
                    s.append("a" + Integer.valueOf(i+1) +"*"+ "c" + Integer.valueOf(i+1));
                }
                s.append(") mod M= " + Long.valueOf(sum) + " mod " + Long.valueOf(M) + "= "+ Long.valueOf(sum%M));

                tvResult.setText(s.toString());
            }
        });
    }


    private ArrayList<Long> getFact(long n){
        ArrayList<Long> res = new ArrayList<>();
        if (isPrime(n)){
            res.add(n);
            return res;
        }

        long j = 2;
        TreeMap<Long,Long> map = new TreeMap<>();

        while (n>1){
            while (n%j==0){
                n/=j;
                if (map.containsKey(j)){
                    map.put(j,map.get(j)+1);
                }
                else{
                    map.put(j, (long) 1);
                }
            }
            j++;
        }

        res.addAll(map.keySet());
        return res;
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
                return false;
            }
        }

        return true;
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