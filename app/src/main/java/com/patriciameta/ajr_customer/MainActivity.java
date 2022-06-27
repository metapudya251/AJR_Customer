package com.patriciameta.ajr_customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.patriciameta.ajr_customer.adapters.PromoAdapter;
import com.patriciameta.ajr_customer.api.ApiClient;
import com.patriciameta.ajr_customer.api.ApiInterface;
import com.patriciameta.ajr_customer.models.Promo;
import com.patriciameta.ajr_customer.models.PromoResponse;
import com.patriciameta.ajr_customer.preferences.UserPreferences;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static Activity mainActivity;

    private MaterialTextView tvWelcome, tvName, tvAset, tvPromo, tvHistory,tvLogout;
    private ImageView ivUser, ivLogout, ivHistory, ivPromo, ivAset;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;
        setContentView(R.layout.activity_main);

        //  Ubah Title pada App Bar Aplikasi
        setTitle("Home");

        userPreferences = new UserPreferences(MainActivity.this);

        //  set Text View
        tvWelcome = findViewById(R.id.tv_welcome);
        tvName = findViewById(R.id.tv_name);
        tvAset = findViewById(R.id.tv_mobil);
        tvPromo = findViewById(R.id.tv_promo);
        tvHistory = findViewById(R.id.tv_history);
        tvLogout = findViewById(R.id.tv_logout);

        tvName.setText(userPreferences.getUserLogin().getNama());
        //  Set Text View
        ivUser = findViewById(R.id.imageView2);
        ivAset = findViewById(R.id.imageView3);
        ivPromo = findViewById(R.id.imageView4);
        ivHistory = findViewById(R.id.imageView5);
        ivLogout = findViewById(R.id.imageView9);

        ivPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, PromoActivity.class));
            }
        });

        ivAset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, AsetActivity.class));
            }
        });

        ivHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, TransaksiActivity.class));
            }
        });

        ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jika menu yang dipilih adalah menu Exit, maka tampilkan sebuah dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.exit_confirm).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userPreferences.logout();
                        checkLogin();
                    }
                }).show();
            }
        });
        checkLogin();
    }

    private void checkLogin(){
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

}