package com.example.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class drink extends AppCompatActivity {

    CardView airmineral , jusmangga, jusapel , jusalpukat;

    Button myorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        airmineral      = findViewById(R.id.airmineral);
        jusmangga       = findViewById(R.id.jusmangga);
        jusapel         = findViewById(R.id.jusapel);
        jusalpukat      = findViewById(R.id.jusalpukat);
        myorder         = findViewById(R.id.myorder);
        Intent intent = new Intent(getBaseContext() , OrderActivity.class);
        airmineral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Air mineral");
                intent.putExtra("price" , 2500);
                startActivity(intent);
            }
        });
        jusmangga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Jus Mangga");
                intent.putExtra("price" , 8000);
                startActivity(intent);
            }
        });
        jusapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Jus Apel");
                intent.putExtra("price" , 10000);
                startActivity(intent);
            }
        });
        jusalpukat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("drink" , "Jus Alpukat");
                intent.putExtra("price" , 9000);
                startActivity(intent);
            }
        });
        myorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , MyOrder.class);
                startActivity(intent);
            }
        });
    }
}