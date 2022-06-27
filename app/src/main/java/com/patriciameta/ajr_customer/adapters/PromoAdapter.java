package com.patriciameta.ajr_customer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.patriciameta.ajr_customer.R;
import com.patriciameta.ajr_customer.models.Promo;

import java.util.ArrayList;
import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> implements Filterable{

    private List<Promo> promoList, filteredPromoList;
    private Context context;

    public PromoAdapter(List<Promo> promoList, Context context) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                ArrayList<Promo> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(promoList);
                } else {
                    for (Promo promo : promoList) {
                        if (promo.getKode_promo().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(promo);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                ArrayList<Promo> promos = new ArrayList<>();
                filteredPromoList.clear();
                filteredPromoList.addAll((List<Promo>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_promo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Promo promo = filteredPromoList.get(position);

        holder.tvKode.setText(promo.getKode_promo());
        holder.tvDiskon.setText(promo.getDiskon_promo().toString());
        holder.tvKet.setText(promo.getJenis_promo()+ " - " + promo.getKeterangan());

    }

    @Override
    public int getItemCount() {
        return filteredPromoList.size();
    }

    public void setPromoList(List<Promo> promoList) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvKode, tvDiskon, tvKet;
        CardView cvPromo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvKode = itemView.findViewById(R.id.tv_kode);
            tvDiskon = itemView.findViewById(R.id.tv_diskon);
            tvKet = itemView.findViewById(R.id.tv_ket);
            cvPromo = itemView.findViewById(R.id.cv_promo);
        }
    }
}
