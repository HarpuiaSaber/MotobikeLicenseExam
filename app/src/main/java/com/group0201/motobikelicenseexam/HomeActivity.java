package com.group0201.motobikelicenseexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.group0201.motobikelicenseexam.model.User;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        //config menu
        //check user logged from share preference
        sharedPreferences = getSharedPreferences("group0201", MODE_PRIVATE);
        String userJson = sharedPreferences.getString("user", null);
        if (userJson != null) {
            Gson gson = new Gson();
            User obj = gson.fromJson(userJson, User.class);
            //config for user logged
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_signup).setVisible(false);
            //config view navigation
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_user, R.id.nav_contact)
                    .setDrawerLayout(drawer)
                    .build();
        } else {
            menu.findItem(R.id.nav_infor).setVisible(false);
            menu.findItem(R.id.nav_logout).setVisible(false);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_user, R.id.nav_contact)
                    .setDrawerLayout(drawer)
                    .build();
        }

        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("user");
            editor.commit();
            finish();
            Intent intent = getIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY); //no back
            startActivity(intent);
            return true;
        });

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
