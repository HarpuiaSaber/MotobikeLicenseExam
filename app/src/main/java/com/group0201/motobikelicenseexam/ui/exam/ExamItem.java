package com.group0201.motobikelicenseexam.ui.exam;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Answer;
import com.group0201.motobikelicenseexam.model.Question;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamItem extends Fragment {
    private TextView questionText;
    private ImageView image;
    private RadioGroup ansGroup;
    private RadioButton btnA;
    private RadioButton btnB;
    private RadioButton btnC;
    private RadioButton btnD;
    private int anscount;
    private String questionString;
    private String imageURI;
    private ArrayList<String> anses;
    private Question question;



    public ExamItem() {

    }

    public static ExamItem newInstance( String questionString, String imageURI, Answer[] anses) {
        ExamItem examitem = new ExamItem();
        Bundle args = new Bundle();
        args.putString("question", questionString);
        args.putString("image", imageURI);
        for(int i=0;i<anses.length;i++){
            args.putString("answers"+i, anses[i].getContent());
        }
        args.putInt("answer_count",anses.length);
        examitem.setArguments(args);
        return examitem;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.anses=new ArrayList<>();
        this.imageURI = getArguments().getString("image");
        this.questionString = getArguments().getString("question", "dont have question");
        this.anscount=getArguments().getInt("answer_count",4);
        String[] ansDb=new String[anscount];
        for(int i=0;i<anscount;i++){
            String ans=getArguments().getString("answers"+i,"");
            anses.add(ans);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_layout, container, false);
        questionText = (TextView) view.findViewById(R.id.question);
        image = (ImageView) view.findViewById(R.id.question_image);
        ansGroup = (RadioGroup) view.findViewById(R.id.btn_group);
        btnA = (RadioButton) view.findViewById(R.id.btn_a);
        btnB = (RadioButton) view.findViewById(R.id.btn_b);
        btnC = (RadioButton) view.findViewById(R.id.btn_c);
        btnD=(RadioButton) view.findViewById(R.id.btn_d);
        new DownLoadImageTask(this.image).execute(this.getContext().getString(R.string.baseUrl) +"images/" +this.imageURI);
        this.ansGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onAnswerButtonCheckedChanged(group, checkedId);
            }
        });

        return view;
    }

    public void onAnswerButtonCheckedChanged(RadioGroup group, int checkedId) {
        int checkedAnsId = group.getCheckedRadioButtonId();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.questionText.setText(questionString);
//        new DownLoadImageTask(this.image).execute(this.getContext().getString(R.string.baseUrl) +"images/" +this.imageURI);
        switch (this.anscount){
            case 2:{
                this.btnA.setText(anses.get(0));
                this.btnB.setText(anses.get(1));
                this.btnC.setVisibility(View.INVISIBLE);
                this.btnC.setClickable(false);
                this.btnD.setVisibility(View.INVISIBLE);
                this.btnD.setClickable(false);
                break;
            }
            case 3:{
                this.btnA.setText(anses.get(0));
                this.btnB.setText(anses.get(1));
                this.btnC.setText(anses.get(2));
                this.btnD.setVisibility(View.INVISIBLE);
                this.btnD.setClickable(false);
                break;
            }
            default:{
                this.btnA.setText(anses.get(0));
                this.btnB.setText(anses.get(1));
                this.btnC.setText(anses.get(2));
                this.btnD.setText(anses.get(3));
                break;
            }
        }
    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
