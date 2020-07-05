package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.Answer;
import com.group0201.motobikelicenseexam.model.Question;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private TextView question;
    private ImageView question_image;
    private CheckBox btnA;
    private CheckBox btnB;
    private CheckBox btnC;
    private CheckBox btnD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        question = findViewById(R.id.question);
        question_image = findViewById(R.id.question_image);
        btnA = findViewById(R.id.btn_a);
        btnB = findViewById(R.id.btn_b);
        btnC = findViewById(R.id.btn_c);
        btnD = findViewById(R.id.btn_d);

        btnA.setClickable(false);
        btnB.setClickable(false);
        btnC.setClickable(false);
        btnD.setClickable(false);

        Intent intent = this.getIntent();
        long questionId = intent.getLongExtra("questionId", 0);
        RequestQueue queue = Volley.newRequestQueue(getBaseContext());
        String url = getString(R.string.baseUrl) + "api/Question/GetQuestionById";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + "?id=" + questionId,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Question questionDB = gson.fromJson(response, Question.class);
                        //show view
                        question.setText(questionDB.getContent());
                        if (questionDB.getImage() != null) {
                            question_image.getLayoutParams().height = 500;
                            new DownLoadImageTask(question_image).execute(getString(R.string.baseUrl) + "images/" + questionDB.getImage());
                        }
                        Answer[] answers = questionDB.getAnswers();
                        switch (answers.length) {
                            case 2: {
                                btnA.setText(answers[0].getContent());
                                btnA.setChecked(answers[0].getIsCorrect());
                                btnB.setText(answers[1].getContent());
                                btnB.setChecked(answers[1].getIsCorrect());
                                btnC.setVisibility(View.INVISIBLE);
                                btnD.setVisibility(View.INVISIBLE);
                                break;
                            }
                            case 3: {
                                btnA.setText(answers[0].getContent());
                                btnA.setChecked(answers[0].getIsCorrect());
                                btnB.setText(answers[1].getContent());
                                btnB.setChecked(answers[1].getIsCorrect());
                                btnC.setText(answers[2].getContent());
                                btnC.setChecked(answers[2].getIsCorrect());
                                btnD.setVisibility(View.INVISIBLE);
                                break;
                            }
                            default: {
                                btnA.setText(answers[0].getContent());
                                btnA.setChecked(answers[0].getIsCorrect());
                                btnB.setText(answers[1].getContent());
                                btnB.setChecked(answers[1].getIsCorrect());
                                btnC.setText(answers[2].getContent());
                                btnC.setChecked(answers[2].getIsCorrect());
                                btnD.setText(answers[3].getContent());
                                btnD.setChecked(answers[3].getIsCorrect());
                                break;
                            }
                        }

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
    }

    private class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) { // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }
    }
}

