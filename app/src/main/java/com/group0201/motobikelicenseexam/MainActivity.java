package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button exam, lookup, theory, fail, achivement;
    private WebView webView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exam = findViewById(R.id.exam);
        lookup = findViewById(R.id.lookup);
        theory = findViewById(R.id.theory);
        fail = findViewById(R.id.fail);
        achivement = findViewById(R.id.achivement);
        webView = findViewById(R.id.webView);
        intent = new Intent();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.google.com");

        exam.setOnClickListener((v) -> {

        });

        lookup.setOnClickListener((v) -> {

        });

        theory.setOnClickListener((v) -> {

        });

        fail.setOnClickListener((v) -> {

        });

        achivement.setOnClickListener((v) -> {

        });
    }
}
