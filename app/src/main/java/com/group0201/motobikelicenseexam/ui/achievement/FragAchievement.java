package com.group0201.motobikelicenseexam.ui.achievement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.group0201.motobikelicenseexam.HomeActivity;
import com.group0201.motobikelicenseexam.R;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.Test;
import com.group0201.motobikelicenseexam.model.Ranking;
import com.group0201.motobikelicenseexam.model.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragAchievement extends Fragment {
    private Spinner bai;
    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_achievement, container, false);
        bai = root.findViewById(R.id.bai);
        listView= root.findViewById(R.id.listview);
        Gson gson = new Gson();
        Map<String, Long> spinnerMap = new HashMap<String, Long>();
        RequestQueue queue = Volley.newRequestQueue(root.getContext());
        String url = getString(R.string.baseUrl) + "api/Exam/GetAll";
        String url2 = getString(R.string.baseUrl) + "api/Exam/Ranking";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Test[] testResponse = gson.fromJson(response, Test[].class);
                        List<Test> testList = Arrays.asList(testResponse);
                        List<String> listSpinnerView = new ArrayList<>();
                        for (int index = 0; index < testList.size(); index++) {
                            Test Test = testList.get(index);
                            listSpinnerView.add(Test.getContent());
                            spinnerMap.put(Test.getContent(), Test.getId());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_dropdown_item, listSpinnerView);
                        bai.setAdapter(arrayAdapter);
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
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2 + "?id=" + spinnerMap.get(bai.getSelectedItem()),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Ranking[] rankingResponse = gson.fromJson(response, Ranking[].class);
                        List<Ranking> rankings = Arrays.asList(rankingResponse);
                        //show view
                        RankingViewAdapter rankingViewAdapter = new RankingViewAdapter(rankings);
                        listView.setAdapter(rankingViewAdapter);
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
        queue.add(stringRequest2);
        bai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                StringRequest stringRequest3 = new StringRequest(Request.Method.GET, url2 + "?id=" + spinnerMap.get(bai.getSelectedItem()),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Ranking[] rankingResponse = gson.fromJson(response, Ranking[].class);
                                List<Ranking> rankings = Arrays.asList(rankingResponse);
                                //show view
                                RankingViewAdapter rankingViewAdapter = new RankingViewAdapter(rankings);
                                listView.setAdapter(rankingViewAdapter);
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
                queue.add(stringRequest3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return root;
    }


}
