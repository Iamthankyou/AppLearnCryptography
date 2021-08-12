package com.example.modulo;

    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

public class FenceRail extends AppCompatActivity {

    public interface Cipher{
        public String encode(String message, int key);
        public String decode(String message, int key);
    }

    public class RailFence implements Cipher{

        private int getTerm(int iteration, int row, int size) {
            if ((size == 0) || (size == 1)) {
                return 1;
            }
            if((row == 0) || (row == size-1)) { // Max. distance is achieved at the ends and equally (size-1)*2
                return (size-1)*2;
            }

            if (iteration % 2 == 0) { // In the description of the method above this identity is demonstrated
                return (size-1-row)*2;
            }
            return 2*row;
        }

        public String encode(String message, int key) {
            if (key < 0) {
                throw new ArithmeticException("Negative key value");
            } else if (key == 0) {
                key = 1;
            }
            String encodedMessage = "";

            for(int row = 0; row < key; row++) { // Look rows
                int iter = 0; // The number of the character in the row
                for(int i = row; i < message.length(); i += getTerm(iter++, row, key)) {

                    encodedMessage += message.charAt(i); // "Add characters line by row"
                }
            }

            return encodedMessage;
        }

        public String decode(String message, int key) {
            if (key < 0) {
                throw new ArithmeticException("Negative key value");
            }
            StringBuilder decodedMessage = new StringBuilder(message);
            int currPosition = 0; // Position in source string
            for(int row = 0; row < key; row++) { // Look rows
                int iter = 0; // The number of the character in the row
                for(int i = row; i < message.length(); i += getTerm(iter++, row, key)) {
                    decodedMessage.setCharAt(i, message.charAt(currPosition++));
                }
            }

            return decodedMessage.toString();
        }

    }

    private Button btnSubmit1, btnSubmit2;
    private EditText etEn1,etEn2,etKey1,etKey2;
    private TextView tvRes1, tvRes2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fence_rail);


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
                RailFence railFence = new RailFence();
                tvRes1.setText(railFence.encode(etEn1.getText().toString(),Integer.parseInt(etKey1.getText().toString())));
            }
        });

        btnSubmit2.setOnClickListener(f->{
            if (etEn2.getText().toString().trim().length()>0 && etKey2.getText().toString().length() >0){
                RailFence railFence = new RailFence();
                tvRes2.setText(railFence.decode(etEn2.getText().toString(),Integer.parseInt(etKey2.getText().toString())));
            }
        });
    }
}