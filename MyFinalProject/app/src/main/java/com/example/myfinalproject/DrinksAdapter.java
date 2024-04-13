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

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    ViewHolder holder;

    public DrinksAdapter(ArrayList<Drinks> listDrinks) {
        this.listDrinks = listDrinks;
    }

    private ArrayList<Drinks> listDrinks;

    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder holder, int position) {
        Drinks drinks = listDrinks.get(position);
        holder.txtNamaDrinks.setText(drinks.getNamaDrinks());
        holder.txtHargaDrinks.setText(rp(Integer.parseInt(drinks.getHargaDrinks())));
        holder.imgDrinks.setImageResource(drinks.getImgDrinks());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listDrinks.get(position).getNamaDrinks().equals("Mousepad Compshere")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.mousepad);
                    intent.putExtra("NAMA_DEFAULT", "Mousepad Compshere");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Soft base, 100% Original");
                    intent.putExtra("HARGA_DEFAULT", "15000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Tumblr CSGO")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.tumblr);
                    intent.putExtra("NAMA_DEFAULT", "Tumblr Computing");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Steel base tumblr. Best 100%");
                    intent.putExtra("HARGA_DEFAULT", "50000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Lanyard Computing")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.lanyard);
                    intent.putExtra("NAMA_DEFAULT", "Lanyard Computing");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Best Lanyard");
                    intent.putExtra("HARGA_DEFAULT", "20000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Totebag Computing")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.totebag);
                    intent.putExtra("NAMA_DEFAULT", "Totebag Computing");
                    intent.putExtra("DESKRIPSI_DEFAULT", "100% catoon");
                    intent.putExtra("HARGA_DEFAULT", "45000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Sticker Compsphere")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.sticker);
                    intent.putExtra("NAMA_DEFAULT", "Sticker Compsphere");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Sticker original best");
                    intent.putExtra("HARGA_DEFAULT", "10000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listDrinks.get(position).getNamaDrinks().equals("Mouse Computing")) {
                    Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.mouse);
                    intent.putExtra("NAMA_DEFAULT", "Mouse Computing");
                    intent.putExtra("DESKRIPSI_DEFAULT", "Silent Click, Comfortable mouse");
                    intent.putExtra("HARGA_DEFAULT", "60000");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDrinks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNamaDrinks, txtHargaDrinks;
        public ImageView imgDrinks;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaDrinks = (TextView) itemView.findViewById(R.id.txtNamaItem);
            txtHargaDrinks = (TextView) itemView.findViewById(R.id.txtHargaItem);
            imgDrinks = (ImageView) itemView.findViewById(R.id.imgItem);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}
