package com.example.modulo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class FairPlay extends AppCompatActivity {

    private Button btnSubmit1, btnSubmit2;
    private EditText etEn1,etEn2,etKey1,etKey2;
    private TextView tvRes1, tvRes2;

    public class Playfair {
        String key;
        String plainText;
        char[][] matrix = new char[5][5];

        public Playfair(String key, String plainText)
        {
            this.key = key.toLowerCase();

            this.plainText = plainText.toLowerCase();
        }

        // Xóa ký tự duplicate
        public void cleanPlayFairKey()
        {
            LinkedHashSet<Character> set
                    = new LinkedHashSet<Character>();

            String newKey = "";

            for (int i = 0; i < key.length(); i++)
                set.add(key.charAt(i));

            Iterator<Character> it = set.iterator();

            while (it.hasNext())
                newKey += (Character)it.next();

            key = newKey;
        }

        // Tạo bảng
        public void generateCipherKey()
        {
            Set<Character> set = new HashSet<Character>();

            for (int i = 0; i < key.length(); i++)
            {
                if (key.charAt(i) == 'j')
                    continue;
                set.add(key.charAt(i));
            }

            // remove repeated characters from the cipher key
            String tempKey = new String(key);

            for (int i = 0; i < 26; i++)
            {
                char ch = (char)(i + 97);
                if (ch == 'j')
                    continue;

                if (!set.contains(ch))
                    tempKey += ch;
            }

            // create cipher key table
            for (int i = 0, idx = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    matrix[i][j] = tempKey.charAt(idx++);

            System.out.println("Playfair Cipher Key Matrix:");

            for (int i = 0; i < 5; i++)
                System.out.println(Arrays.toString(matrix[i]));
        }

        // function to preprocess plaintext
        public String formatPlainText()
        {
            String message = "";
            int len = plainText.length();

            for (int i = 0; i < len; i++)
            {
                if (plainText.charAt(i) == 'j')
                    message += 'i';
                else
                    message += plainText.charAt(i);
            }

            for (int i = 0; i < message.length(); i += 2)
            {
                if (message.charAt(i) == message.charAt(i + 1<message.length()?i+1:i))
                    message = message.substring(0, i + 1 < message.length()?i+1:i) + 'x'
                            + message.substring(i + 1<message.length()?i+1:i);
            }

            if (len % 2 == 1)
                message += 'x';

            return message;
        }

        public String[] formPairs(String message)
        {
            int len = message.length();
            String[] pairs = new String[len / 2];

            for (int i = 0, cnt = 0; i < len / 2; i++)
                pairs[i] = message.substring(cnt, cnt += 2);

            return pairs;
        }

        public int[] getCharPos(char ch)
        {
            int[] keyPos = new int[2];

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {

                    if (matrix[i][j] == ch)
                    {
                        keyPos[0] = i;
                        keyPos[1] = j;
                        break;
                    }
                }
            }
            return keyPos;
        }

        public String encryptMessage()
        {
            String message = formatPlainText();
            String[] msgPairs = formPairs(message);
            String encText = "";

            for (int i = 0; i < msgPairs.length; i++)
            {
                char ch1 = msgPairs[i].charAt(0);
                char ch2 = msgPairs[i].charAt(1);
                int[] ch1Pos = getCharPos(ch1);
                int[] ch2Pos = getCharPos(ch2);

                if (ch1Pos[0] == ch2Pos[0]) {
                    ch1Pos[1] = (ch1Pos[1] + 1) % 5;
                    ch2Pos[1] = (ch2Pos[1] + 1) % 5;
                }

                else if (ch1Pos[1] == ch2Pos[1])
                {
                    ch1Pos[0] = (ch1Pos[0] + 1) % 5;
                    ch2Pos[0] = (ch2Pos[0] + 1) % 5;
                }

                else {
                    int temp = ch1Pos[1];
                    ch1Pos[1] = ch2Pos[1];
                    ch2Pos[1] = temp;
                }

                encText = encText + matrix[ch1Pos[0]][ch1Pos[1]]
                        + matrix[ch2Pos[0]][ch2Pos[1]];
            }

            return encText;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fair_play);

        btnSubmit1 = (Button) findViewById(R.id.btnSub1);
        btnSubmit2 = (Button) findViewById(R.id.btnSub2);
        etEn1 = (EditText) findViewById(R.id.etEn1);
        etEn2 = (EditText) findViewById(R.id.etEn2);
        etKey1 = (EditText) findViewById(R.id.etN);
        etKey2 = (EditText) findViewById(R.id.etKey2);
        tvRes1 = (TextView) findViewById(R.id.tvRes1);
        tvRes2 = (TextView) findViewById(R.id.tvRes2);

        btnSubmit1.setOnClickListener(f->{
            if (etEn1.getText().toString().trim().length()>0 && etKey1.getText().toString().length() >0){
                Playfair playfair = new Playfair(etKey1.getText().toString(),etEn1.getText().toString());
                playfair.cleanPlayFairKey();
                playfair.generateCipherKey();
                tvRes1.setText(playfair.encryptMessage());
            }
        });

        btnSubmit2.setOnClickListener(f->{
            if (etEn2.getText().toString().trim().length()>0 && etKey2.getText().toString().length() == 26){

            }
        });

    }
}