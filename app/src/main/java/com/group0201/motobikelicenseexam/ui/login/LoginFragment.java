package com.group0201.motobikelicenseexam.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.group0201.motobikelicenseexam.HomeActivity;
import com.group0201.motobikelicenseexam.R;

public class LoginFragment extends Fragment {

    private LoginViewModel galleryViewModel;
    private EditText username, password;
    private Button login;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(LoginViewModel.class);
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        username = root.findViewById(R.id.username);
        password = root.findViewById(R.id.password);
        login = root.findViewById(R.id.login);

        login.setOnClickListener((v) -> {
            RequestQueue queue = Volley.newRequestQueue(root.getContext());
            String url = getString(R.string.baseUrl) + "api/User/Login";
            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url
                    + "?username=" + username.getText().toString() + "&password=" + password.getText().toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            SharedPreferences sharedPreferences = root.getContext().getSharedPreferences("group0201", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("user", response);
                            editor.commit();
                            Intent intent = new Intent(root.getContext(), HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); //no back
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse networkResponse = error.networkResponse;
                    String des = new String(networkResponse.data);
                    if (networkResponse != null && networkResponse.statusCode == 500) {
                        Toast.makeText(root.getContext(), new String(networkResponse.data), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        });
        return root;

    }

}
