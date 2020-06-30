package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.group0201.motobikelicenseexam.ui.exam.ExamAdapter;
import com.group0201.motobikelicenseexam.ui.exam.ExamIcon;

import java.util.ArrayList;

public class ExamListActivity extends AppCompatActivity {
    private GridView listExam;
    private ArrayList<ExamIcon> arrIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        init();
    }
    public void init(){
        this.arrIcon=new ArrayList<>();
        for(int i=0;i<3;i++){
            ExamIcon examIcon=new ExamIcon(i+1,0,0);
            this.arrIcon.add(examIcon);
        }
        ExamAdapter examAdapter=new ExamAdapter(arrIcon,this);
        listExam=(GridView) findViewById(R.id.list_exam);
        this.listExam.setAdapter(examAdapter);
        listExam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ExamListActivity.this,ExamActivity.class);
                startActivity(intent);
            }
        });
    }
}