package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.group0201.motobikelicenseexam.ui.exam.ItemAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ExamActivity extends AppCompatActivity {
    private  String[] questions={"cau hoi 1","cau hoi 2","cau hoi 3","cau hoi 4"};
    private  int[] images={R.drawable.bb3,R.drawable.bb4,R.drawable.bb6,R.drawable.bb7};
    private  String[] answers1={"cau 1","cau 2","cau 3"};
    private  String[] answers2={"cau 1","cau 2","cau 3"};
    private  String[] answers3={"cau 1","cau 2","cau 3"};
    private  String[] answers4={"cau 1","cau 2"};
    private  int[] corrAnses={1,2,1,1};
    private ViewPager examPages;
    //    private static ExamItem itemFrag;
    private int currentPage=0;
    private ArrayList<String> qusArr;
    private  ArrayList<Integer> imgArr;
    private  ArrayList<String[]> answersArr;
    private  ArrayList<Integer> corrArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qusArr=new ArrayList<>();
        for(int a=0;a<questions.length;a++){
            qusArr.add(questions[a]);
        }
        imgArr=new ArrayList<>();
        for(int i=0;i<images.length;i++){
            imgArr.add(images[i]);
        }
        answersArr=new ArrayList<>();
        answersArr.add(answers1);
        answersArr.add(answers2);
        answersArr.add(answers3);
        answersArr.add(answers4);
        corrArr=new ArrayList<>();
        for(int b=0;b<corrAnses.length;b++){
            corrArr.add(corrAnses[b]);
        }
        setContentView(R.layout.activity_exam);
        examPages =(ViewPager) findViewById(R.id.pager);
        init();
    }
    public void init(){
        ItemAdapter mPagerAdap=new ItemAdapter(getSupportFragmentManager(),this.imgArr,this.answersArr,this.corrArr,this.qusArr);
        examPages.setAdapter(mPagerAdap);
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(examPages);
        examPages.setCurrentItem(currentPage,true);
    }

}