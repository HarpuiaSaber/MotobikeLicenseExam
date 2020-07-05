package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.Test;
import com.group0201.motobikelicenseexam.model.User;
import com.group0201.motobikelicenseexam.ui.exam.ExamAdapter;
import com.group0201.motobikelicenseexam.ui.exam.ExamIcon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamListActivity extends AppCompatActivity {
    private GridView listExam;
    private List<Test> arrIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        listExam=(GridView) findViewById(R.id.list_exam);
        init();
    }
    public void init(){
        this.arrIcon=new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getString(R.string.baseUrl) + "api/Exam/GetAllTrial";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
//                        User userFromDB = gson.fromJson(response, User.class);
                        Test[] testDB=gson.fromJson(response,Test[].class);
                        for(int i=0;i<testDB.length;i++){
                            if(testDB[i].getType()==1){
                                arrIcon.add(testDB[i]);
                            }
                        }
//                        arrIcon= Arrays.asList(testDB);
                        //show view
                        ExamAdapter examAdapter=new ExamAdapter(arrIcon);
                        listExam.setAdapter(examAdapter);
                        listExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(ExamListActivity.this,ExamActivity.class);
                                intent.putExtra("exam_id",arrIcon.get(position).getId());
                                intent.putExtra("duration",arrIcon.get(position).getTime());
                                intent.putExtra("test_type",arrIcon.get(position).getType());
                                startActivity(intent);
                            }
                        });
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