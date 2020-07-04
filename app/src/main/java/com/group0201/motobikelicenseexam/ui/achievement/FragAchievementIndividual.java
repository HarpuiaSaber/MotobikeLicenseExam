package com.group0201.motobikelicenseexam.ui.achievement;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Test;
import com.group0201.motobikelicenseexam.model.User;
import com.group0201.motobikelicenseexam.model.UserResult;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragAchievementIndividual extends Fragment {
    private ListView listView;
    private PersonalAchievementViewAdapter personalAchievementViewAdapter;
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_achievement_individual, container, false);
        Gson gson = new Gson();

        listView = root.findViewById(R.id.listView);

        //SharedPreferences sharedPreferences=
        sharedPreferences = root.getContext().getSharedPreferences("group0201", Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", null);
        if (userJson != null) {
            User obj = gson.fromJson(userJson, User.class);
            //config for user logged
            RequestQueue queue = Volley.newRequestQueue(root.getContext());
            String url = getString(R.string.baseUrl) + "api/Exam/GetAllOfUser";
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                    + "?userId=" + obj.getId(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            UserResult[] testResponse = gson.fromJson(response, UserResult[].class);
                            List<UserResult> tests = Arrays.asList(testResponse);
                            //show view
                            personalAchievementViewAdapter = new PersonalAchievementViewAdapter(tests);
                            listView.setAdapter(personalAchievementViewAdapter);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse networkResponse = error.networkResponse;
                    String des = new String(networkResponse.data);
                    if (networkResponse != null && networkResponse.statusCode == 500) {
                        Toast.makeText(root.getContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }
        return root;
    }
}
