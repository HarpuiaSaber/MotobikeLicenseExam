package com.group0201.motobikelicenseexam.ui.personel;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group0201.motobikelicenseexam.HomeActivity;
import com.group0201.motobikelicenseexam.R;
import com.group0201.motobikelicenseexam.model.User;
import com.group0201.motobikelicenseexam.ui.signup.SignupViewModel;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PersonelFragment extends Fragment {

    private SignupViewModel galleryViewModel;
    private EditText name, username, phone, dob;
    private RadioButton male, female;
    private SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SignupViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personel, container, false);

        name = root.findViewById(R.id.name);
        name.setEnabled(false);
        username = root.findViewById(R.id.username);
        username.setEnabled(false);
        phone = root.findViewById(R.id.phone);
        phone.setEnabled(false);
        dob = root.findViewById(R.id.dob);
        dob.setEnabled(false);
        male = root.findViewById(R.id.male);
        male.setEnabled(false);
        female = root.findViewById(R.id.female);
        female.setEnabled(false);

        //default
        male.setChecked(true);
        dob.setInputType(InputType.TYPE_NULL);

        //read from share pref to get user id
        Gson gson = new Gson();
        sharedPreferences = root.getContext().getSharedPreferences("group0201", root.getContext().MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", null);
        if (userJson != null) {
            User user = gson.fromJson(userJson, User.class);
            RequestQueue queue = Volley.newRequestQueue(root.getContext());
            String url = getString(R.string.baseUrl) + "api/User/GetById?id=" + user.getId();
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            User userFromDB = gson.fromJson(response, User.class);
                            //show view
                            name.setText(userFromDB.getName());
                            username.setText(userFromDB.getUserName());
                            phone.setText(userFromDB.getPhone());
                            if (userFromDB.getGender() == 0) {
                                male.setChecked(true);
                                female.setChecked(false);
                            } else {
                                male.setChecked(false);
                                female.setChecked(true);
                            }
                            dob.setText(userFromDB.getDob());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null && networkResponse.statusCode == 500) {
                        Toast.makeText(root.getContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }
        return root;

    }

}
