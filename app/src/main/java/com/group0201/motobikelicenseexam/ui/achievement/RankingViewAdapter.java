package com.group0201.motobikelicenseexam.ui.achievement;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Ranking;
import com.group0201.motobikelicenseexam.model.Test;

import java.util.List;

public class RankingViewAdapter extends BaseAdapter {
    private List<Ranking> list;

    public RankingViewAdapter(List<Ranking> list) {
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
        return list.get(position).getUserId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View root;
        if (convertView == null) {
            root = View.inflate(parent.getContext(), R.layout.personal_achievement_view, null);
        } else {
            root = convertView;
        }
        Ranking ranking = (Ranking) getItem(position);
        ((TextView) root.findViewById(R.id.bai)).setText(String.format("%s", ranking.getUserName()));
        ((TextView) root.findViewById(R.id.rightAnswer)).setText(String.format("%s/20", ranking.getTotalCorrect()));
        ((TextView) root.findViewById(R.id.time)).setText(String.format("%s s", ranking.getTime()));
        return root;
    }
}
