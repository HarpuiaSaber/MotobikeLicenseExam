package com.group0201.motobikelicenseexam.ui.fail;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Fail;

import java.util.List;

public class FailViewAdapter extends BaseAdapter {

    private List<Fail> list;

    public FailViewAdapter(List<Fail> list) {
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
            root = View.inflate(parent.getContext(), R.layout.fail_view, null);
        } else {
            root = convertView;
        }
        Fail fail = (Fail) getItem(position);
        ((TextView) root.findViewById(R.id.content)).setText(String.format("%s", fail.getContent()));
        ((TextView) root.findViewById(R.id.times)).setText(String.format("%d", fail.getTimes()));
        return root;
    }
}
