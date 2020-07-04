package com.group0201.motobikelicenseexam.ui.exam;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.group0201.motobikelicenseexam.model.ListQuestion;
import com.group0201.motobikelicenseexam.model.Question;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends FragmentPagerAdapter {
//    private ArrayList<Integer> imageUris;
//    private ArrayList<String[]> answers;
//    private ArrayList<Integer> corrections;
//    private ArrayList<String> questions;
    private ListQuestion questions;
    public ItemAdapter(FragmentManager fm,
                       ListQuestion questions) {
        super(fm);
        this.questions = questions;
    }


    @Override
    public Fragment getItem(int i) {

        return ExamItem.newInstance(questions.getQuestions().get(i).getId(),questions.getQuestions().get(i).getContent(),
                            questions.getQuestions().get(i).getImage(),
                            questions.getQuestions().get(i).getAnswers());
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getCount() {
        return questions.getQuestions().size();
    }
}
