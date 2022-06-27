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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.patriciameta.ajr_customer.R;
import com.patriciameta.ajr_customer.models.Aset;
import com.patriciameta.ajr_customer.models.Promo;

import java.util.ArrayList;
import java.util.List;

public class AsetAdapter extends RecyclerView.Adapter<AsetAdapter.ViewHolder> implements Filterable {
    private List<Aset> asetList, filteredAsetList;
    private Context context;
    private Aset aset;

    public AsetAdapter(List<Aset> asetList, Context context) {
        this.asetList = asetList;
        filteredAsetList = new ArrayList<>(asetList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                ArrayList<Aset> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(asetList);
                } else {
                    for (Aset aset : asetList) {
                        if (aset.getNama_mobil().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(aset);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                ArrayList<Aset> asets = new ArrayList<>();
                filteredAsetList.clear();
                filteredAsetList.addAll((List<Aset>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public AsetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_aset, parent, false);

        return new AsetAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsetAdapter.ViewHolder holder, int position) {
        Aset aset = filteredAsetList.get(position);
        final int yellow = ContextCompat.getColor(context, R.color.yellow);
        final int green = ContextCompat.getColor(context, R.color.green);

        holder.tvMobil.setText(aset.getNama_mobil().toString());
        holder.tvHarga.setText(" Rp. "+ aset.getBiaya_sewa().toString());
        holder.tvStatus.setText(aset.getStatus_tersedia());

        if (aset.getStatus_tersedia().toString().equalsIgnoreCase("Tidak Tersedia")){
            holder.cvStatus.setCardBackgroundColor(yellow);
        }else{
            holder.cvStatus.setCardBackgroundColor(green);
        }
    }

    @Override
    public int getItemCount() {
        return filteredAsetList.size();
    }

    public void setAsetList(List<Aset> asetList) {
        this.asetList = asetList;
        filteredAsetList = new ArrayList<>(asetList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMobil, tvHarga, tvStatus;
        CardView cvAset, cvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMobil = itemView.findViewById(R.id.tv_mobil);
            tvHarga = itemView.findViewById(R.id.tv_harga);
            tvStatus = itemView.findViewById(R.id.tv_status);
            cvAset = itemView.findViewById(R.id.group);
            cvStatus = itemView.findViewById(R.id.cv_status);
        }
    }
}
