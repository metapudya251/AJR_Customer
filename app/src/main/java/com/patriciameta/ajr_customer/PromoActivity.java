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
import com.patriciameta.ajr_customer.api.ApiClient;
import com.patriciameta.ajr_customer.api.ApiInterface;
import com.patriciameta.ajr_customer.models.PromoResponse;
import com.patriciameta.ajr_customer.preferences.UserPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoActivity extends AppCompatActivity {
    public static Activity promoActivity;
    private UserPreferences userPreferences;

    private ApiInterface apiService;
    private SwipeRefreshLayout srPromo;
    private PromoAdapter adapter;
    private SearchView svPromo;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promoActivity = this;
        setContentView(R.layout.activity_promo);

        //  Ubah Title pada App Bar Aplikasi
        setTitle("Promo");
        userPreferences = new UserPreferences(PromoActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        layoutLoading = findViewById(R.id.layout_loading);
        srPromo = findViewById(R.id.sr_promo);
        svPromo = findViewById(R.id.sv_promo);

        srPromo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllPromo();
            }
        });
        svPromo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        RecyclerView rvPromo = findViewById(R.id.rv_promo);
        adapter = new PromoAdapter(new ArrayList<>(), this);
        rvPromo.setLayoutManager(new LinearLayoutManager(PromoActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvPromo.setAdapter(adapter);

        getAllPromo();
    }

    private void getAllPromo(){
        Call<PromoResponse> call = apiService.getPromoAktif();
        srPromo.setRefreshing(true);
        call.enqueue(new Callback<PromoResponse>() {

            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setPromoList(response.body().getPromoList());
                    adapter.getFilter().filter(svPromo.getQuery());
                }else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(PromoActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(PromoActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srPromo.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {
                Toast.makeText(PromoActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srPromo.setRefreshing(false);
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
            startActivity(new Intent(PromoActivity.this, MainActivity.class));
        } else if (item.getItemId() == R.id.menu_brosur) {
            MainActivity.mainActivity.finish();
            finishAndRemoveTask();
            startActivity(new Intent(PromoActivity.this, AsetActivity.class));
        } else if (item.getItemId() == R.id.menu_history) {
            MainActivity.mainActivity.finish();
            finishAndRemoveTask();
            startActivity(new Intent(PromoActivity.this, TransaksiActivity.class));
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
            startActivity(new Intent(PromoActivity.this, LoginActivity.class));
            finish();
        }
    }
}