package com.patriciameta.ajr_customer.api;

import com.patriciameta.ajr_customer.models.AsetResponse;
import com.patriciameta.ajr_customer.models.Promo;
import com.patriciameta.ajr_customer.models.PromoResponse;
import com.patriciameta.ajr_customer.models.TransaksiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    //Promo
    @Headers({"Accept: application/json"})
    @GET("promo")
    Call<PromoResponse> getAllPromo();
    @Headers({"Accept: application/json"})
    @GET("promo/{id}")
    Call<PromoResponse> getPromoById(@Path("id") long id);
    @Headers({"Accept: application/json"})
    @GET("promoAktif")
    Call<PromoResponse> getPromoAktif();
    @Headers({"Accept: application/json"})
    @DELETE("promo/{id}")
    Call<PromoResponse> deletePromo(@Path("id") long id);

    //Aset
    @Headers({"Accept: application/json"})
    @GET("aset")
    Call<AsetResponse> getAllAset();
    @Headers({"Accept: application/json"})
    @GET("aset/{id}")
    Call<AsetResponse> getAsetById(@Path("id") long id);
    @Headers({"Accept: application/json"})
    @DELETE("aset/{id}")
    Call<AsetResponse> deleteAset(@Path("id") long id);

    //transaksi
    @Headers({"Accept: application/json"})
    @GET("transaksi")
    Call<TransaksiResponse> getAllTransaksi();
    @Headers({"Accept: application/json"})
    @GET("transaksiHistory")
    Call<TransaksiResponse> gethistory();
    @Headers({"Accept: application/json"})
    @GET("transaksiMobil")
    Call<TransaksiResponse> getIncomeMobil();
    @Headers({"Accept: application/json"})
    @GET("transaksiCust")
    Call<TransaksiResponse> getIncomeCust();
    @Headers({"Accept: application/json"})
    @GET("transaksiTop5Driver")
    Call<TransaksiResponse> getTop5Driver();
    @Headers({"Accept: application/json"})
    @GET("transaksiTop5Cust")
    Call<TransaksiResponse> getTop5Cust();
    @Headers({"Accept: application/json"})
    @GET("transaksi/{id}")
    Call<TransaksiResponse> getTransaksiById(@Path("id") long id);
    @Headers({"Accept: application/json"})
    @DELETE("transaksi/{id}")
    Call<TransaksiResponse> deleteTransaksi(@Path("id") long id);

    //Customer
//    @Headers({"Accept: application/json"})
//    @GET("customer")
//    Call<PromoResponse> getAllPromo();
//    @Headers({"Accept: application/json"})
//    @GET("customer/{id}")
//    Call<PromoResponse> getPromoById(@Path("id") long id);
//    @Headers({"Accept: application/json"})
//    @DELETE("customer/{id}")
//    Call<PromoResponse> deleteMPromo(@Path("id") long id);
}
