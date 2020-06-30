package com.group0201.motobikelicenseexam.ui.exam;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ItemAdapter extends FragmentPagerAdapter {
    private ArrayList<Integer> imageUris;
    private ArrayList<String[]> answers;
    private ArrayList<Integer> corrections;
    private ArrayList<String> questions;

    public ItemAdapter(FragmentManager fm,
                       ArrayList<Integer> imageUris,
                       ArrayList<String[]> answers,
                       ArrayList<Integer> corrections,
                       ArrayList<String> questions) {
        super(fm);
        this.imageUris = imageUris;
        this.answers = answers;
        this.corrections = corrections;
        this.questions = questions;
    }

//    public void setUp(){
//        this.fragArr=new ArrayList<>();
//        for(int i=0;i<questions.size();i++){
//            ExamItem newItem=new ExamItem();
//            newItem.setAgu(questions.get(i),imageUris.get(i),answers.get(i),corrections.get(i));
//            this.fragArr.add(newItem);
//        }
//    }

    @Override
    public Fragment getItem(int i) {
        return ExamItem.newInstance(corrections.get(i),questions.get(i),imageUris.get(i),answers.get(i));
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return questions.size();
    }
}
