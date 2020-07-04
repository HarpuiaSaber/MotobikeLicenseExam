package com.group0201.motobikelicenseexam.ui.exam;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Answer;
import com.group0201.motobikelicenseexam.model.AnswerResult;
import com.group0201.motobikelicenseexam.model.Question;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExamItem extends Fragment implements View.OnClickListener {
    private TextView questionText;
    private ImageView image;
    private CheckBox btnA;
    private CheckBox btnB;
    private CheckBox btnC;
    private CheckBox btnD;
    private long questionId;
    private int anscount;
    private String questionString;
    private String imageURI;
    private ArrayList<String> anses;
    private ArrayList<Boolean> corrections;
    private PassData passData;


    public ExamItem() {

    }

    public static ExamItem newInstance( long quesID,String questionString, String imageURI, Answer[] anses) {
        ExamItem examitem = new ExamItem();
        Bundle args = new Bundle();
        args.putLong("id",quesID);
        args.putString("question", questionString);
        args.putString("image", imageURI);
        for(int i=0;i<anses.length;i++){
            args.putString("answers"+i, anses[i].getContent());
            args.putBoolean("is_correct"+i,anses[i].getIsCorrect());
        }
        args.putInt("answer_count",anses.length);
        examitem.setArguments(args);
        return examitem;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.anses=new ArrayList<>();
        this.corrections=new ArrayList<>();
        this.questionId=getArguments().getLong("id");
        this.imageURI = getArguments().getString("image");
        this.questionString = getArguments().getString("question", "dont have question");
        this.anscount=getArguments().getInt("answer_count",4);
        for(int i=0;i<anscount;i++){
            String ans=getArguments().getString("answers"+i,"");
            Boolean correct=getArguments().getBoolean("is_correct"+i,false);
            anses.add(ans);
            corrections.add(correct);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_layout, container, false);
        questionText = (TextView) view.findViewById(R.id.question);
        image = (ImageView) view.findViewById(R.id.question_image);
        btnA = (CheckBox) view.findViewById(R.id.btn_a);
        btnB = (CheckBox) view.findViewById(R.id.btn_b);
        btnC = (CheckBox) view.findViewById(R.id.btn_c);
        btnD = (CheckBox) view.findViewById(R.id.btn_d);
        new DownLoadImageTask(this.image).execute(this.getContext().getString(R.string.baseUrl) +"images/" +this.imageURI);
        return view;
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
        this.btnA.setOnClickListener(this);
        this.btnB.setOnClickListener(this);
        this.btnC.setOnClickListener(this);
        this.btnD.setOnClickListener(this);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.passData=(PassData) context;
    }
    public void onPassingData(long quId, boolean isCr){
        passData.onDataPass(quId,isCr);
    }
    @Override
    public void onClick(View view) {
        boolean iscr;
        boolean[] checkedPosition={false,false,false,false};
       if(view.getId()==R.id.btn_a ||
               view.getId()==R.id.btn_b||
               view.getId()==R.id.btn_c||
               view.getId()==R.id.btn_d){
           if(this.btnA.isChecked()){
               checkedPosition[0]=true;
           }
           if(this.btnB.isChecked()){
               checkedPosition[1]=true;
           }
           if(this.btnC.isChecked()){
               checkedPosition[2]=true;
           }
           if(this.btnD.isChecked()){
               checkedPosition[3]=true;
           }
       }
       boolean equal=true;
       for(int i=0;i<corrections.size();i++){
           if(checkedPosition[i]!=corrections.get(i)){
               equal=false;
               break;
           }
       }
       if(equal==false){
           iscr=false;
       }else{
           iscr=true;
       }
       onPassingData(questionId,iscr);
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
    public interface PassData{
        public void onDataPass(long id, boolean is);
    }
}
