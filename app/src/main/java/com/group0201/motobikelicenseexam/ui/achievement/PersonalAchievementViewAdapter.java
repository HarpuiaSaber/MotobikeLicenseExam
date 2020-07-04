package com.group0201.motobikelicenseexam.ui.achievement;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Result;
import com.group0201.motobikelicenseexam.utils.TimeUtils;

import java.util.List;

public class PersonalAchievementViewAdapter extends BaseAdapter {
    private List<Result> list;

    public PersonalAchievementViewAdapter(List<Result> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root;
        if (convertView == null) {
            root = View.inflate(parent.getContext(), R.layout.personal_achievement_view, null);
        } else {
            root = convertView;
        }
        Result result = (Result) getItem(position);
        ((TextView) root.findViewById(R.id.bai)).setText(String.format("%s", result.getContent()));
        ((TextView) root.findViewById(R.id.rightAnswer)).setText(String.format("%s/20", result.getTotalCorrect()));
        ((TextView) root.findViewById(R.id.time)).setText(String.format("%s", TimeUtils.secondToMinuteAndSecond(result.getTime())));
        ((TextView) root.findViewById(R.id.dateAt)).setText(String.format("%s", result.getDateAt()));
        return root;
    }
}
