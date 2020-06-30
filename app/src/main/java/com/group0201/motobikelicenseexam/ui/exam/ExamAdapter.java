package com.group0201.motobikelicenseexam.ui.exam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;

import java.util.ArrayList;

public class ExamAdapter extends BaseAdapter {
    private ArrayList<ExamIcon> arrIcon;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExamAdapter(ArrayList<ExamIcon> arrIcon, Context context) {
        this.arrIcon = arrIcon;
        this.context = context;
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
        layoutInflater=(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            holder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.exam_button_layout,null);
            holder.number=(TextView) convertView.findViewById(R.id.number);
            holder.corrects=(TextView) convertView.findViewById(R.id.count_correct);
            holder.fails=(TextView)convertView.findViewById(R.id.count_fail);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        ExamIcon exam=this.arrIcon.get(position);
        holder.number.setText("Test No."+exam.getTestNum());
        holder.corrects.setText("Correct: "+exam.getCorrectCount()+"/200");
        holder.fails.setText("Fail: "+exam.getFailCount()+"/200");
        return convertView;
    }

    private class ViewHolder{
        TextView number,corrects,fails;
    }
}
