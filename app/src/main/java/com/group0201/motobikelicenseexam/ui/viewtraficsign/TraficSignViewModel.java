package com.group0201.motobikelicenseexam.ui.viewtraficsign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.group0201.motobikelicenseexam.R;

public class TraficSignViewModel extends AppCompatActivity {
    ImageButton btn1, btn2, btn3, btn4, btn5, btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_sign_view_model);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TraficSignViewModel.this, TraficSignViewModelDetail.class);
                intent.putExtra("type", 1);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TraficSignViewModel.this, TraficSignViewModelDetail.class);
                intent.putExtra("type", 2);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TraficSignViewModel.this, TraficSignViewModelDetail.class);
                intent.putExtra("type", 3);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TraficSignViewModel.this, TraficSignViewModelDetail.class);
                intent.putExtra("type", 4);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TraficSignViewModel.this, TraficSignViewModelDetail.class);
                intent.putExtra("type", 5);
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(TraficSignViewModel.this, TraficSignViewModelDetail.class);
                intent.putExtra("type", 6);
                startActivity(intent);
            }
        });
    }
}
