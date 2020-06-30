package com.group0201.motobikelicenseexam.ui.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group0201.motobikelicenseexam.R;

public class ExamItem extends Fragment {
    private TextView question;
    private ImageView image;
    private RadioGroup ansGroup;
    private RadioButton btnA;
    private RadioButton btnB;
    private RadioButton btnC;
    private int qId;
    private int corrAns;
    private String questionString;
    private Integer imageURI;
    private String[] anses;


    public ExamItem() {

    }

    public static ExamItem newInstance(int corrAns, String questionString, Integer imageURI, String[] anses) {
        ExamItem examitem = new ExamItem();
        Bundle args = new Bundle();
        args.putInt("correct", corrAns);
        args.putString("question", questionString);
        args.putInt("image", imageURI);
        args.putStringArray("answers", anses);
        examitem.setArguments(args);
        return examitem;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.corrAns = getArguments().getInt("correct", 1);
        this.imageURI = getArguments().getInt("image", R.drawable.bb3);
        this.questionString = getArguments().getString("question", "dont have question");
        this.anses = getArguments().getStringArray("answers");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.item_layout, container, false);

        question = (TextView) view.findViewById(R.id.question);
        image = (ImageView) view.findViewById(R.id.question_image);
        ansGroup = (RadioGroup) view.findViewById(R.id.btn_group);
        btnA = (RadioButton) view.findViewById(R.id.btn_a);
        btnB = (RadioButton) view.findViewById(R.id.btn_b);
        btnC = (RadioButton) view.findViewById(R.id.btn_c);

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
        if (checkedAnsId == R.id.btn_a) {
            Toast.makeText(this.getContext(), "button " + checkedId + "is checked", Toast.LENGTH_LONG).show();
            int checked = 1;
            if (checked == this.corrAns) {

            }
        } else if (checkedAnsId == R.id.btn_b) {
            Toast.makeText(this.getContext(), "button " + checkedId + "is checked", Toast.LENGTH_LONG).show();
            int checked = 2;
            if (checked == this.corrAns) {

            }
        } else {
            Toast.makeText(this.getContext(), "button " + checkedId + "is checked", Toast.LENGTH_LONG).show();
            int checked = 3;
            if (checked == this.corrAns) {

            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.question.setText(questionString);
        this.image.setImageResource(imageURI);
        if (anses.length == 3) {
            this.btnA.setText(anses[0]);
            this.btnB.setText(anses[1]);
            this.btnC.setText(anses[2]);
        } else {
            this.btnA.setText(anses[0]);
            this.btnB.setText(anses[1]);
            this.btnC.setClickable(false);
        }
    }
}
