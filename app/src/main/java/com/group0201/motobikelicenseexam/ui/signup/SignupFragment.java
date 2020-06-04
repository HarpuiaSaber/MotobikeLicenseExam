package com.group0201.motobikelicenseexam.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.group0201.motobikelicenseexam.HomeActivity;
import com.group0201.motobikelicenseexam.R;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class SignupFragment extends Fragment {

    private SignupViewModel galleryViewModel;
    private EditText name, username, password, passwordrepeat, phone, dob;
    private RadioButton male, female;
    private Button signup;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SignupViewModel.class);
        View root = inflater.inflate(R.layout.fragment_signup, container, false);

        name = root.findViewById(R.id.name);
        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        passwordrepeat = root.findViewById(R.id.passwordrepeat);
        phone = root.findViewById(R.id.phone);
        dob = root.findViewById(R.id.dob);
        male = root.findViewById(R.id.male);
        female = root.findViewById(R.id.female);
        signup = root.findViewById(R.id.signup);

        //default
        male.setChecked(true);

        StringBuilder bodyString = new StringBuilder();
        bodyString.append("{");
        bodyString.append("\"id\":0,");
        bodyString.append("\"username\":\"" + username.getText().toString() + "\",");
        bodyString.append("\"id\":\"" + password.getText().toString() + "\",");
        bodyString.append("\"id\":\"" + name.getText().toString() + "\",");
        bodyString.append("\"id\":true,");
        bodyString.append("\"id\":\"" + dob.getText().toString() + "\",");
        bodyString.append("\"id\":" + (male.isChecked() ? 0 : 1) + ",");
        bodyString.append("\"id\":\"" + phone.getText().toString() + "\"");
        bodyString.append("}");

        String body = bodyString.toString();

        signup.setOnClickListener((v) -> {
            if (password.getText().toString().equals(passwordrepeat.getText().toString())) {
                RequestQueue queue = Volley.newRequestQueue(root.getContext());
                String url = getString(R.string.baseUrl) + "api/User/Create";
                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null) {
                            if (networkResponse.statusCode == 500) {
                                Toast.makeText(root.getContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (networkResponse != null) {
                            if (networkResponse.statusCode == 200) {
                                Toast.makeText(root.getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(root.getContext(), HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); //no back
                                startActivity(intent);
                            }
                        }
                    }
                }) {
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return body == null ? null : body.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
                            return null;
                        }
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            } else {
                Toast.makeText(root.getContext(), "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        });
        return root;

    }

}
