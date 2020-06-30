package com.group0201.motobikelicenseexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.TraficSign;
import com.group0201.motobikelicenseexam.ui.viewtraficsign.TraficSignAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TraficSignViewModelDetail extends AppCompatActivity {
    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafic_sign_view_model_detail);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        int type = getIntent().getIntExtra("type", 0);
        String URL = getString(R.string.baseUrl) +"api/TraficSign/Get?traficSignType=" + type;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>(){
                    public void onResponse(JSONArray  response){
                        Log.e("Rest Response",response.toString());
                        List<TraficSign> traficSigns = new ArrayList<TraficSign>();
                        for(int i =0; i<response.length(); i++){
                            try {
                                JSONObject jsonTra = response.getJSONObject(i);
                                long Id = jsonTra.getLong("id");
                                String Content = jsonTra.getString("content");
                                int Type= jsonTra.getInt("type");
                                String Name = jsonTra.getString("name");
                                String Image = jsonTra.getString("image");
                                //Law law = new Law(Id,Content,Type, Punishment );
                                TraficSign traficSign = new TraficSign(Id,Name,Image,Content,Type);
                                traficSigns.add(traficSign);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        listView1 = (ListView) findViewById(R.id.lv_tra);
                        TraficSignAdapter traficSignAdapter = new TraficSignAdapter(getApplicationContext(), R.layout.row_tra, traficSigns);
                        listView1.setAdapter(traficSignAdapter);
                    }
                },
                new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){
                        Log.e("Rest Response",error.toString());
                    }
                }
        );
        requestQueue.add(arrayRequest);
    }
}
