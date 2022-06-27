package com.patriciameta.ajr_customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.patriciameta.ajr_customer.adapters.AsetAdapter;
import com.patriciameta.ajr_customer.adapters.PromoAdapter;
import com.patriciameta.ajr_customer.api.ApiClient;
import com.patriciameta.ajr_customer.api.ApiInterface;
import com.patriciameta.ajr_customer.models.AsetResponse;
import com.patriciameta.ajr_customer.models.PromoResponse;
import com.patriciameta.ajr_customer.preferences.UserPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsetActivity extends AppCompatActivity {

    public static Activity asetActivity;
    private UserPreferences userPreferences;

    private ApiInterface apiService;
    private SwipeRefreshLayout srAset;
    private AsetAdapter adapter;
    private SearchView svAset;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asetActivity = this;
        setContentView(R.layout.activity_aset);

        //  Ubah Title pada App Bar Aplikasi
        setTitle("Aset");
        userPreferences = new UserPreferences(AsetActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        layoutLoading = findViewById(R.id.layout_loading);
        srAset = findViewById(R.id.sr_aset);
        svAset = findViewById(R.id.sv_aset);

        srAset.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllAset();
            }
        });
        svAset.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        RecyclerView rvAset = findViewById(R.id.rv_aset);
        adapter = new AsetAdapter(new ArrayList<>(), this);
//        rvAset.setLayoutManager(new LinearLayoutManager(AsetActivity.this,
//                LinearLayoutManager.VERTICAL, false));
        rvAset.setLayoutManager(new GridLayoutManager(AsetActivity.this, 2));
        rvAset.setAdapter(adapter);

        getAllAset();
    }

    private void getAllAset(){
        Call<AsetResponse> call = apiService.getAllAset();
        srAset.setRefreshing(true);
        call.enqueue(new Callback<AsetResponse>() {

            @Override
            public void onResponse(Call<AsetResponse> call, Response<AsetResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setAsetList(response.body().getAsetList());
                    adapter.getFilter().filter(svAset.getQuery());
                }else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(AsetActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AsetActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srAset.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<AsetResponse> call, Throwable t) {
                Toast.makeText(AsetActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srAset.setRefreshing(false);
            }
        });
    }

    // Fungsi untuk menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_home) {
            MainActivity.mainActivity.finish();
            finishAndRemoveTask();
            startActivity(new Intent(AsetActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.menu_promo) {
            MainActivity.mainActivity.finish();
            finishAndRemoveTask();
            startActivity(new Intent(AsetActivity.this, PromoActivity.class));
        } else if (item.getItemId() == R.id.menu_logout){
            // Jika menu yang dipilih adalah menu Exit, maka tampilkan sebuah dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.exit_confirm).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    userPreferences.logout();
                    checkLogin();
                }
            }).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkLogin(){
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(AsetActivity.this, LoginActivity.class));
            finish();
        }
    }
}