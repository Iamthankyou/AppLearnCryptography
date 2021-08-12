package com.example.modulo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DecryptCaesar extends AppCompatActivity {

    private Button btnSubmitDeCaesar;
    private EditText etDeCaesar, etDeKeyCaesar;
    private TextView textView;

    private  StringBuffer encrypt(String text, int s)
    {
        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                        s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                        s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_caesar);

        btnSubmitDeCaesar = (Button) findViewById(R.id.btnGen1);
        etDeCaesar = (EditText) findViewById(R.id.etEn1);
        etDeKeyCaesar = (EditText) findViewById(R.id.etN);
        textView = (TextView) findViewById(R.id.tvRes1);

        textView.setVisibility(View.GONE);

        btnSubmitDeCaesar.setOnClickListener(f->{

            if (etDeCaesar.getText().toString().trim().length()>0 && etDeKeyCaesar.getText().toString().trim().length()>0){
                textView.setVisibility(View.VISIBLE);
                int n = Integer.parseInt(etDeKeyCaesar.getText().toString()) %26;
                String res = encrypt(etDeCaesar.getText().toString(),26-n).toString();

                textView.setText(res);

            }
            else{
                Toast.makeText(this,"Vui lòng nhập tất cả giá trị", Toast.LENGTH_SHORT).show();
            }

        });
    }
}