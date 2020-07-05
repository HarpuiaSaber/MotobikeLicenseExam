package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.Fail;
import com.group0201.motobikelicenseexam.model.User;
import com.group0201.motobikelicenseexam.ui.fail.FailViewAdapter;

import java.util.Arrays;
import java.util.List;

public class FailActivity extends AppCompatActivity {

    private ListView listFail;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fail);

        listFail = findViewById(R.id.listFail);

        Gson gson = new Gson();

        sharedPreferences = getSharedPreferences("group0201", MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", null);
        User user = gson.fromJson(userJson, User.class);

        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        String url = getString(R.string.baseUrl) + "api/Question/GetFailQuestions";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "?userId=" + user.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Fail[] FailResponse = gson.fromJson(response, Fail[].class);
                        List<Fail> Fails = Arrays.asList(FailResponse);
                        //show view
                        FailViewAdapter FailViewAdapter = new FailViewAdapter(Fails);
                        listFail.setAdapter(FailViewAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                String des = new String(networkResponse.data);
                if (networkResponse != null && networkResponse.statusCode == 500) {
                    Toast.makeText(getBaseContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                }
            }
        });
        queue.add(stringRequest);

        listFail.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("questionId", listFail.getItemIdAtPosition(position));
            startActivity(intent);
        });
    }
}
