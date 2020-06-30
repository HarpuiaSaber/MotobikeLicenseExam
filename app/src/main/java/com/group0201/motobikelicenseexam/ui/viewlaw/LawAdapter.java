package com.group0201.motobikelicenseexam.ui.viewlaw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.Law;

import java.util.List;

public class LawAdapter extends ArrayAdapter<Law> {
    private Context context;
    private int resource;
    private List<Law> arrLaw;

    public LawAdapter(Context context, int resource, List<Law> arrLaw) {
        super(context, resource, arrLaw);
        this.context = context;
        this.resource = resource;
        this.arrLaw = arrLaw;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_law, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            viewHolder.tvPunishment = (TextView) convertView.findViewById(R.id.tvPunishment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Law law = arrLaw.get(position);
        viewHolder.tvId.setText(String.valueOf(position+1));
        viewHolder.tvContent.setText(law.getContent());
        viewHolder.tvPunishment.setText(law.getPunishment());
        return convertView;
    }

    public class ViewHolder {
        TextView tvContent, tvId, tvPunishment;
    }

}
