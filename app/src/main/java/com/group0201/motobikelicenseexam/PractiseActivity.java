package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.AnswerResult;
import com.group0201.motobikelicenseexam.model.ListQuestion;
import com.group0201.motobikelicenseexam.ui.exam.ItemAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class PractiseActivity extends AppCompatActivity {
    private ViewPager examPages;
    private long examId;
    private int type;
    private int currentPage=0;
    private ListQuestion questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise);
        Intent intent=this.getIntent();
        this.examId=intent.getLongExtra("exam_id",0);
        this.type=intent.getIntExtra("exam_type",0);
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
                        ItemAdapter mPagerAdap=new ItemAdapter(getSupportFragmentManager(),questions,type);
                        for(int i=0;i<questions.getQuestions().size();i++){
                            AnswerResult asre=new AnswerResult(questions.getQuestions().get(i).getId(),false);
                        }
                        examPages =(ViewPager) findViewById(R.id.pager_prac);
                        examPages.setAdapter(mPagerAdap);
                        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator_prac);
                        indicator.setViewPager(examPages);
                        examPages.setCurrentItem(currentPage,true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 500) {
                  Toast.makeText(getApplicationContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
}