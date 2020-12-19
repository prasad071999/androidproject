package com.example.e_kethi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class firstpage extends AppCompatActivity {
    Button btnprice,btnabt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        btnprice = findViewById(R.id.ppp);
        btnprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(firstpage.this,pricepriduction.class);
                startActivity(i);
            }

        });
        btnabt = findViewById(R.id.about);
        btnabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent(firstpage.this,about.class);
                startActivity(a);
            }
        });
    }
}