package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.AnswerResult;
import com.group0201.motobikelicenseexam.model.ListQuestion;
import com.group0201.motobikelicenseexam.model.User;
import com.group0201.motobikelicenseexam.model.UserResult;
import com.group0201.motobikelicenseexam.ui.exam.ExamItem;
import com.group0201.motobikelicenseexam.ui.exam.ItemAdapter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ExamActivity extends AppCompatActivity implements ExamItem.PassData {
    private ViewPager examPages;
    private TextView minute;
    private TextView second;
    private long examId;
    private int testDur;
    private int currentPage=0;
    private ListQuestion questions;
    private ArrayList<AnswerResult> arrAnsRes;
    private UserResult userResult;
    private long duration;
    private int correctMes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        Intent intent=this.getIntent();
        this.examId=intent.getLongExtra("exam_id",0);
        this.testDur=intent.getIntExtra("duration",20000)*1000;
        this.minute=(TextView) findViewById(R.id.minutes);
        this.second=(TextView) findViewById(R.id.seconds);
        init(examId);
    }
    public void init(long examid){
        this.questions=new ListQuestion();
        this.arrAnsRes=new ArrayList<>();
        duration=(long)testDur;
//        for(int i=0;i<20;i++){
//            AnswerResult ar=new AnswerResult(0,false);
//            arrAnsRes.add(ar);
//        }
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
                        for(int i=0;i<questions.getQuestions().size();i++){
                            AnswerResult asre=new AnswerResult(questions.getQuestions().get(i).getId(),false);
                            arrAnsRes.add(asre);
                        }
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

        new CountDownTimer(duration,1000){
            @Override
            public void onTick(long l) {
                minute.setText(""+duration/60000);
                second.setText(""+(duration%60000)/1000);
                duration-=1000;
            }

            @Override
            public void onFinish() {

                Toast.makeText(getApplicationContext(),"time is up",Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("group0201", MODE_PRIVATE);
                String userJson = sharedPreferences.getString("user", null);
                Gson gson = new Gson();
                User obj = gson.fromJson(userJson, User.class);

                long idUser=obj.getId();
                int countCorr=0;
                AnswerResult[] answerResults=new AnswerResult[20];
                for(int i=0;i<arrAnsRes.size();i++){
                    AnswerResult ansr=new AnswerResult(arrAnsRes.get(i).getQuestionId(),arrAnsRes.get(i).isRight());
                    answerResults[i]=ansr;
                    if(arrAnsRes.get(i).isRight()==true){
                        countCorr++;
                    }
                }
                correctMes=countCorr;
                userResult=new UserResult(idUser,examId,(testDur-(int)duration)/1000+1,countCorr,answerResults);

                String body=gson.toJson(userResult);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = getString(R.string.baseUrl) + "api/Exam/SaveResult";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(),"Result uploaded!",Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null && networkResponse.statusCode == 500) {
                            Toast.makeText(getApplicationContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                        }
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return body == null ? null : body.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
                            return null;
                        }
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
                showAlertDialog();
            }
        }.start();

    }

    @Override
    public void onDataPass(long id, boolean is) {
        AnswerResult ansre=new AnswerResult(id,is);
        for(int i=0;i<arrAnsRes.size();i++){
            if(arrAnsRes.get(i).getQuestionId()==ansre.getQuestionId()){
                arrAnsRes.remove(i);
                arrAnsRes.add(i,ansre);
                return;
            }
        }
        arrAnsRes.add(ansre);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences("group0201", MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", null);
        Gson gson = new Gson();
        User obj = gson.fromJson(userJson, User.class);

        long idUser=obj.getId();
        int countCorr=0;
        AnswerResult[] answerResults=new AnswerResult[20];
        for(int i=0;i<arrAnsRes.size();i++){
            AnswerResult ansr=new AnswerResult(arrAnsRes.get(i).getQuestionId(),arrAnsRes.get(i).isRight());
            answerResults[i]=ansr;
            if(arrAnsRes.get(i).isRight()==true){
                countCorr++;
            }
        }
        correctMes=countCorr;
        userResult=new UserResult(idUser,examId,(testDur-(int)duration)/1000+1,countCorr,answerResults);

        String body=gson.toJson(userResult);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = getString(R.string.baseUrl) + "api/Exam/SaveResult";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"Result uploaded!",Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null && networkResponse.statusCode == 500) {
                    Toast.makeText(getApplicationContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return body == null ? null : body.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
                    return null;
                }
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        new AlertDialog.Builder(this)
                .setTitle("Kết quả:")
                .setMessage("Đúng "+this.correctMes+"/20 trong "+((testDur-(int)duration)/1000)+" giây")
                .setPositiveButton("Thoát", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .show();
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Kết quả:");
        builder.setMessage("Đúng "+correctMes+"/20 trong "+((testDur-(int)duration)/1000)+" giây");
        builder.setCancelable(false);
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(ExamActivity.this,ExamListActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}