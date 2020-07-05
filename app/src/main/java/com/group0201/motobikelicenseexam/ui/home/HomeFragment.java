package com.group0201.motobikelicenseexam.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.AchievementActivites;
import com.group0201.motobikelicenseexam.ExamListActivity;
import com.group0201.motobikelicenseexam.FailActivity;
import com.group0201.motobikelicenseexam.LawViewModel;
import com.group0201.motobikelicenseexam.PractiseListActivity;
import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.TraficSignViewModel;
import com.group0201.motobikelicenseexam.model.User;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private WebView webView;
    private ImageButton exam, theory, law, fail, sign, achievment;
    private SharedPreferences sharedPreferences;


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

        sharedPreferences = root.getContext().getSharedPreferences("group0201", root.getContext().MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", null);
        exam.setOnClickListener((v) -> {
            if (userJson != null) {
                startActivity(new Intent(root.getContext(), ExamListActivity.class));
            } else {
                Toast.makeText(root.getContext(), "Phải đăng nhập trước", Toast.LENGTH_SHORT).show();
            }
        });
        theory.setOnClickListener((v) -> {
            startActivity(new Intent(root.getContext(), PractiseListActivity.class));
        });
        law.setOnClickListener((v) -> {
            startActivity(new Intent(root.getContext(), LawViewModel.class));
        });
        fail.setOnClickListener((v) -> {
            if (userJson != null) {
                startActivity(new Intent(root.getContext(), FailActivity.class));
            } else {
                Toast.makeText(root.getContext(), "Phải đăng nhập trước", Toast.LENGTH_SHORT).show();
            }
        });
        sign.setOnClickListener((v) -> {
            startActivity(new Intent(root.getContext(), TraficSignViewModel.class));
        });
        achievment.setOnClickListener((v) -> {
            if (userJson != null) {
                startActivity(new Intent(root.getContext(), AchievementActivites.class));
            } else {
                Toast.makeText(root.getContext(), "Phải đăng nhập trước", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
