package com.example.myfinalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PenyetanAdapter extends RecyclerView.Adapter<PenyetanAdapter.ViewHolder> {

    ViewHolder holder;

    public PenyetanAdapter(ArrayList<Penyetan> listPenyetan) {
        this.listPenyetan = listPenyetan;
    }

    private ArrayList<Penyetan> listPenyetan;

    @NonNull
    @Override
    public PenyetanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =  new ViewHolder(inflater.inflate(R.layout.item_menu, parent, false));

        return holder;
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    @Override
    public void onBindViewHolder(@NonNull PenyetanAdapter.ViewHolder holder, int position) {
        Penyetan penyetan = listPenyetan.get(position);
        holder.txtNamaPenyetan.setText(penyetan.getNamaPenyetan());
        holder.txtHargaPenyetan.setText(rp(Integer.parseInt(penyetan.getHargaPenyetan())));
        holder.imgPenyetan.setImageResource(penyetan.getImgPenyetan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listPenyetan.get(position).getNamaPenyetan().equals("T-shirt Compsphere")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.baju1);
                    intent.putExtra("NAMA_DEFAULT", "T-shirt Compsphere");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Ayam Goreng Jamin Gurih Sambal Mantap deh");
                    intent.putExtra("HARGA_DEFAULT", "70000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("T-shirt Compgath")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.baju3);
                    intent.putExtra("NAMA_DEFAULT", "T-shirt Compgath");
                    intent.putExtra("DESKRIPSI_DEFAULT", " Varsity with an attractive design that can be used every day and adds to the aesthetic style");
                    intent.putExtra("HARGA_DEFAULT", "80000");
                    holder.itemView.getContext().startActivity(intent);
                }
                    if (listPenyetan.get(position).getNamaPenyetan().equals("T-shirt Computing")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.baju5);
                    intent.putExtra("NAMA_DEFAULT", "T-shirt Computing");
                    intent.putExtra("DESKRIPSI_DEFAULT", " Varsity with an attractive design that can be used every day and adds to the aesthetic style");
                    intent.putExtra("HARGA_DEFAULT", "65000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("Varsity Computing")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.varsity2);
                    intent.putExtra("NAMA_DEFAULT", "Varsity Computing");
                    intent.putExtra("DESKRIPSI_DEFAULT", " Varsity with an attractive design that can be used every day and adds to the aesthetic style");
                    intent.putExtra("HARGA_DEFAULT", "120000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("Varsity Compsphere")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.varsity1);
                    intent.putExtra("NAMA_DEFAULT", "Varsity Compsphere");
                    intent.putExtra("DESKRIPSI_DEFAULT", " Varsity with an attractive design that can be used every day and adds to the aesthetic style");
                    intent.putExtra("HARGA_DEFAULT", "110000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listPenyetan.get(position).getNamaPenyetan().equals("Varsity CSGO")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.varsity);
                    intent.putExtra("NAMA_DEFAULT", "Varsity CSGO");
                    intent.putExtra("DESKRIPSI_DEFAULT", " Varsity with an attractive design that can be used every day and adds to the aesthetic style");
                    intent.putExtra("HARGA_DEFAULT", "100000");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPenyetan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaPenyetan, txtHargaPenyetan;
        public ImageView imgPenyetan;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaPenyetan = (TextView) itemView.findViewById(R.id.txtNamaItem);
            txtHargaPenyetan = (TextView) itemView.findViewById(R.id.txtHargaItem);
            imgPenyetan = (ImageView) itemView.findViewById(R.id.imgItem);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}
