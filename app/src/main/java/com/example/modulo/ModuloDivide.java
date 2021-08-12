package com.example.modulo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.github.kexanie.library.MathView;

public class ModuloDivide extends AppCompatActivity {

    private EditText etN,etA,etM;
    private Button btnSubmit;
    private MathView tvRes,textView;
    private StringBuilder res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_divide);

        res = new StringBuilder();
        etN = (EditText)findViewById(R.id.etN);
        etM = (EditText)findViewById(R.id.etM);
        etA = (EditText)findViewById(R.id.etA);
        btnSubmit = (Button)findViewById(R.id.btnFactPrime);
        tvRes = (MathView)findViewById(R.id.tvRes1);
        textView = (MathView)findViewById(R.id.Monoalphabetic);

        textView.setText("$$\\color{white}{Modulo\\;B\\;=\\;A^M\\;mod\\;N}$$");

//        tvRes.setVisibility(View.GONE);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etN.getText().toString().trim().length()>0 && etM.getText().toString().trim().length()>0 && etA.getText().toString().trim().length()>0){
                    long N = Long.parseLong(etN.getText().toString());
                    long M = Long.parseLong(etM.getText().toString());
                    long A = Long.parseLong(etA.getText().toString());

                    res = new StringBuilder();
                    long result = getModulo(N,M,A);
                    tvRes.refreshDrawableState();
//                    tvRes.clearComposingText();
//                    tvRes.setVisibility(View.VISIBLE);
//                    tvRes.setLatex("B="+String.valueOf(result)+"\n"+res.toString());
                    tvRes.setText("$$\\color{red}{B="+String.valueOf(result)+"}$$"+"\n"+res.toString());
                }
                else{
                    tvRes.setText("");
                    Toast.makeText(ModuloDivide.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        tvRes.setMovementMethod(new ScrollingMovementMethod());



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
            res.append("$$\\color{white}{"+String.valueOf(a)+"^{"+String.valueOf(m)+"}"+"\\;m\\;"+String.valueOf(n)+"\\;=(" + String.valueOf(a)+"^{"+String.valueOf(x1)+"}\\;\\times\\;"+ String.valueOf(a)+"^{"+String.valueOf(x1)+"})\\;m\\;"+ String.valueOf(n)+"\\;=\\;"+String.valueOf(tmp)+"}$$");
//            res.append(String.valueOf(a)+"\\{^}"+String.valueOf(m)+" mod "+String.valueOf(n)+"= ("+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+"x"+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+")"+"%"+String.valueOf(n)+"="+String.valueOf(tmp)+"\n");
        }
        else{
            long x1 = m/2;
            tmp = getModulo(n,m/2,a);
            long tmp1 = tmp;
            tmp = (a*tmp*tmp)%n;
            res.append("$$\\color{white}{"+String.valueOf(a)+"^{"+String.valueOf(m)+"}"+"\\;m\\;"+String.valueOf(n)+"\\;=(" + String.valueOf(a)+"^{"+String.valueOf(x1)+"}\\;\\times\\;"+ String.valueOf(a)+"^{"+String.valueOf(x1)+"}\\;\\times\\;"+String.valueOf(a)+")\\;m\\;"+ String.valueOf(n)+"\\;=\\;"+String.valueOf(tmp)+"}$$");
//            res.append("$$\\color{white}{x^2}$$");
//            res.append("$$\\color{white}{"+String.valueOf(a)+"^"+String.valueOf(m)+" mod "+String.valueOf(n)+"= ("+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+"x"+String.valueOf(tmp1)+"^"+String.valueOf(m/2)+"x"+String.valueOf(a)+")"+"%"+String.valueOf(n)+"="+String.valueOf(tmp)+"\n"+"}$$");
        }


        return tmp;
    }
}