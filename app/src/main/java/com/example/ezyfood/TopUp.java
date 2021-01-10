package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ezyfood.sharedpref.SaldoPref;

public class TopUp extends AppCompatActivity {

    private SaldoPref saldoPref;
    private Button confirm;
    private EditText topup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        saldoPref = new SaldoPref(getBaseContext());
        confirm = findViewById(R.id.confirm);
        topup   = findViewById(R.id.topup);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int saldo = Integer.parseInt(topup.getText().toString());
                saldoPref.setSaldo(saldo);
                Toast.makeText(getBaseContext() , "Saldo bertambah menjadi " + Integer.toString(saldoPref.getSaldo()), Toast.LENGTH_SHORT).show();
                topup.setText("");
            }
        });
    }
}