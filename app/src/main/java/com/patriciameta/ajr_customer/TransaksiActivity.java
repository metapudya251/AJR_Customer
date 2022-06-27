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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.patriciameta.ajr_customer.adapters.PromoAdapter;
import com.patriciameta.ajr_customer.adapters.TransaksiAdapter;
import com.patriciameta.ajr_customer.api.ApiClient;
import com.patriciameta.ajr_customer.api.ApiInterface;
import com.patriciameta.ajr_customer.models.PromoResponse;
import com.patriciameta.ajr_customer.models.TransaksiResponse;
import com.patriciameta.ajr_customer.preferences.UserPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    public static Activity transaksiActivity;
    private UserPreferences userPreferences;

    private ApiInterface apiService;
    private SwipeRefreshLayout srTransaksi;
    private TransaksiAdapter adapter;
    private SearchView svTransaksi;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transaksiActivity = this;
        setContentView(R.layout.activity_transaksi);

        //  Ubah Title pada App Bar Aplikasi
        setTitle("Transaksi");
        userPreferences = new UserPreferences(TransaksiActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        layoutLoading = findViewById(R.id.layout_loading);
        srTransaksi = findViewById(R.id.sr_transaksi);
        svTransaksi = findViewById(R.id.sv_transaksi);

        srTransaksi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllTransaksi();
            }
        });
        svTransaksi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        RecyclerView rvTransaksi = findViewById(R.id.rv_transaksi);
        adapter = new TransaksiAdapter(new ArrayList<>(), this);
        rvTransaksi.setLayoutManager(new LinearLayoutManager(TransaksiActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvTransaksi.setAdapter(adapter);

        getAllTransaksi();
    }

    private void getAllTransaksi(){
        Call<TransaksiResponse> call = apiService.gethistory();
        srTransaksi.setRefreshing(true);
        call.enqueue(new Callback<TransaksiResponse>() {

            @Override
            public void onResponse(Call<TransaksiResponse> call, Response<TransaksiResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setTransaksiList(response.body().getTransaksiList());
                    adapter.getFilter().filter(svTransaksi.getQuery());
                }else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(TransaksiActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(TransaksiActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srTransaksi.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<TransaksiResponse> call, Throwable t) {
                Toast.makeText(TransaksiActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srTransaksi.setRefreshing(false);
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
            startActivity(new Intent(TransaksiActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.menu_brosur) {
            MainActivity.mainActivity.finish();
            finishAndRemoveTask();
            startActivity(new Intent(TransaksiActivity.this, AsetActivity.class));
        } else if (item.getItemId() == R.id.menu_history) {
            //
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
            startActivity(new Intent(TransaksiActivity.this, LoginActivity.class));
            finish();
        }
    }
}