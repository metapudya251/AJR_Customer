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
import com.patriciameta.ajr_customer.models.Transaksi;

import java.util.ArrayList;
import java.util.List;
public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> implements Filterable {
    private List<Transaksi> transaksiList, filteredTransaksiList;
    private List<Aset> asetList;
    private Context context;

    public TransaksiAdapter(List<Transaksi> transaksiList, Context context) {
        this.transaksiList= transaksiList;
        filteredTransaksiList = new ArrayList<>(transaksiList);
        this.context = context;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                ArrayList<Transaksi> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(transaksiList);
                } else {
                    for (Transaksi transaksi : transaksiList) {
                        if (transaksi.getNo_transaksi().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(transaksi);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                ArrayList<Transaksi> transaksis = new ArrayList<>();
                filteredTransaksiList.clear();
                filteredTransaksiList.addAll((List<Transaksi>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    @NonNull
    @Override
    public TransaksiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.rv_item_transaksi, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransaksiAdapter.ViewHolder holder, int position) {
        Transaksi transaksi = filteredTransaksiList.get(position);
        final int yellow = ContextCompat.getColor(context, R.color.yellow);
        final int green = ContextCompat.getColor(context, R.color.green);

        holder.tvMobil.setText(transaksi.getNama_aset());
        holder.tvTotal.setText(transaksi.getTotal_biaya_sewa().toString());
        holder.tvNo.setText   (transaksi.getNo_transaksi());
//        holder.tvJenis.setText   ("Jenis Transaksi: " +transaksi.getJenis_transaksi());
        holder.tvCust.setText   (transaksi.getNama_cust());
        holder.tvTgl.setText   (transaksi.getTgl_waktu_selesai_sewa());
        holder.tvStatus.setText(transaksi.getStatus_transaksi());
        if (transaksi.getStatus_transaksi().equalsIgnoreCase("Belum Lunas")){
            holder.tvStatus.setBackgroundColor(yellow);
        }else{
            holder.tvStatus.setBackgroundColor(green);
        }

    }

    @Override
    public int getItemCount() {
        return filteredTransaksiList.size();
    }

    public void setTransaksiList(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
        filteredTransaksiList = new ArrayList<>(transaksiList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvMobil, tvTotal, tvJenis,tvNo,tvCust,tvTgl,tvStatus;
        CardView cvTransaksi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMobil = itemView.findViewById(R.id.tv_aset);
            tvTotal = itemView.findViewById(R.id.tv_total);
//            tvJenis = itemView.findViewById(R.id.tv_jenis);
            tvNo = itemView.findViewById(R.id.tv_no);
            tvCust = itemView.findViewById(R.id.tv_cust);
            tvTgl = itemView.findViewById(R.id.tv_tgl);
            tvStatus = itemView.findViewById(R.id.tv_status);
            cvTransaksi = itemView.findViewById(R.id.cv_transaksi);
        }
    }
}
