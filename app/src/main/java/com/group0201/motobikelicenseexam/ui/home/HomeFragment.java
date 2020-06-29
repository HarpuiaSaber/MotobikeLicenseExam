package com.group0201.motobikelicenseexam.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.group0201.motobikelicenseexam.AchievementActivites;
import com.group0201.motobikelicenseexam.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private WebView webView;
    private ImageButton exam, theory, law, fail, sign, achievment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        webView = root.findViewById(R.id.webView);
        exam = root.findViewById(R.id.exam);
        theory = root.findViewById(R.id.theory);
        law = root.findViewById(R.id.law);
        fail = root.findViewById(R.id.fail);
        sign = root.findViewById(R.id.sign);
        achievment = root.findViewById(R.id.achievment);

        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webView.loadUrl(getString(R.string.baseUrl) + "Home/Infor");

        exam.setOnClickListener((v) -> {
        });
        theory.setOnClickListener((v) -> {
        });
        law.setOnClickListener((v) -> {
        });
        fail.setOnClickListener((v) -> {
        });
        sign.setOnClickListener((v) -> {
        });
        achievment.setOnClickListener((v) -> {
            startActivity(new Intent(root.getContext(), AchievementActivites.class));
        });

        return root;
    }
}
