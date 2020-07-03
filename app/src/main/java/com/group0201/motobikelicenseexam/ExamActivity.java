package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.ListQuestion;
import com.group0201.motobikelicenseexam.model.Question;
import com.group0201.motobikelicenseexam.model.Test;
import com.group0201.motobikelicenseexam.ui.exam.ExamAdapter;
import com.group0201.motobikelicenseexam.ui.exam.ItemAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class ExamActivity extends AppCompatActivity {
    private ViewPager examPages;
    private long examId;
    private int currentPage=0;
    private ListQuestion questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Intent intent=this.getIntent();
        this.examId=intent.getLongExtra("exam_id",0);
        init(examId);
    }
    public void init(long examid){
        this.questions=new ListQuestion();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.baseUrl) + "api/Exam/Get?id="+examid;
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        questions=gson.fromJson(response,ListQuestion.class);
                        //show view
                        ItemAdapter mPagerAdap=new ItemAdapter(getSupportFragmentManager(),questions);
                        examPages =(ViewPager) findViewById(R.id.pager);
                        examPages.setAdapter(mPagerAdap);
                        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
                        indicator.setViewPager(examPages);
                        examPages.setCurrentItem(currentPage,true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 500) {
//                    Toast.makeText(this, new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

}