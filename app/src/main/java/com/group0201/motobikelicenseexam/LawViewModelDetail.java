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
import com.group0201.motobikelicenseexam.model.Law;
import com.group0201.motobikelicenseexam.ui.viewlaw.LawAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LawViewModelDetail extends AppCompatActivity {
    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_view_model_detail);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        int type = getIntent().getIntExtra("type", 0);
        String URL =  getString(R.string.baseUrl) +"api/Law/GetLaw?lawType=" + type;
        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>(){
                    public void onResponse(JSONArray  response){
                        Log.e("Rest Response",response.toString());
                        List<Law> laws = new ArrayList<Law>();
                        for(int i =0; i<response.length(); i++){
                            try {
                                JSONObject jsonLaw = response.getJSONObject(i);
                                long Id = jsonLaw.getLong("id");
                                String Content = jsonLaw.getString("content");
                                int Type= jsonLaw.getInt("type");
                                String Punishment = jsonLaw.getString("punishment");
                                Law law = new Law(Id,Content,Type, Punishment );
                                laws.add(law);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        listView1 = (ListView) findViewById(R.id.lv_law);
                        LawAdapter lawAdapter = new LawAdapter(getApplicationContext(), R.layout.row_law, laws);
                        listView1.setAdapter(lawAdapter);
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
