package com.group0201.motobikelicenseexam.ui.exam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Test;
import com.group0201.motobikelicenseexam.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class ExamAdapter extends BaseAdapter {
    //    private ArrayList<ExamIcon> arrIcon;
    private List<Test> arrIcon;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExamAdapter(List<Test> arrIcon) {
        this.arrIcon = arrIcon;
//        this.context = context;
//        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrIcon.size();
    }

    @Override
    public Object getItem(int position) {
        return arrIcon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        this.context = parent.getContext();
        layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.exam_button_layout, null);
            holder.number = (TextView) convertView.findViewById(R.id.number);
            holder.duration = (TextView) convertView.findViewById(R.id.duration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        ExamIcon exam=this.arrIcon.get(position);
        Test test = this.arrIcon.get(position);
        holder.number.setText("Tên bài: " + test.getContent());
        holder.duration.setText("Thời gian: " + TimeUtils.secondToMinuteAndSecond(test.getTime()) + " phút");
        return convertView;
    }

    private class ViewHolder {
        TextView number, duration;
    }
}
