package com.group0201.motobikelicenseexam.ui.viewtraficsign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.TraficSign;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class TraficSignAdapter extends ArrayAdapter<TraficSign> {
    private Context context;
    private int resource;
    private List<TraficSign> arrTra;

    public TraficSignAdapter(Context context, int resource, List<TraficSign> arrTra) {
        super(context, resource, arrTra);
        this.context = context;
        this.resource = resource;
        this.arrTra = arrTra;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_tra, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.ivImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TraficSign traficSign = arrTra.get(position);
        viewHolder.tvContent.setText(traficSign.getContent());
        viewHolder.tvName.setText(traficSign.getName());
        new DownLoadImageTask(viewHolder.imageView).execute(convertView.getContext().getString(R.string.baseUrl) +traficSign.getImage());
        return convertView;
    }

    public class ViewHolder {
        TextView tvContent, tvName;
        ImageView imageView;

    }
    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }

}
