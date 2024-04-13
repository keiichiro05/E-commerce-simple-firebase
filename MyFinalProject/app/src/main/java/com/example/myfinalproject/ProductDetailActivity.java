package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {
    TextView txtNamaMenuDetail, txtHargaMenuDetail;
    EditText edtJumlah;
    FirebaseFirestore fireDb;
    FirebaseUser user;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFab();

        setContentView(R.layout.activity_product_detail);
        ImageView img_menu_detail = findViewById(R.id.imgMenuDetail);
        TextView txt_nama_menu_detail = findViewById(R.id.txtNamaMenuDetail);
        TextView txt_deskripsi_menu_detail = findViewById(R.id.txtDeskripsiMenuDetail);
        TextView txt_harga_menu_detail = findViewById(R.id.txtHargaMenuDetail);

        Intent intent = getIntent();

        int gambarMakanan = intent.getIntExtra("GAMBAR_DEFAULT", 0);
        String namaMakanan = intent.getStringExtra("NAMA_DEFAULT");
        String deskripsiMakanan = intent.getStringExtra("DESKRIPSI_DEFAULT");
        String hargaMakanan = intent.getStringExtra("HARGA_DEFAULT");

        img_menu_detail.setImageResource(gambarMakanan);
        txt_nama_menu_detail.setText(namaMakanan);
        txt_deskripsi_menu_detail.setText(deskripsiMakanan);
        txt_harga_menu_detail.setText(rp(Integer.parseInt(hargaMakanan)));

        txtNamaMenuDetail = findViewById(R.id.txtNamaMenuDetail);
        txtHargaMenuDetail = findViewById(R.id.txtHargaMenuDetail);
        edtJumlah = findViewById(R.id.edt_jumlah);

        user = FirebaseAuth.getInstance().getCurrentUser();
        fireDb = FirebaseFirestore.getInstance();
        back = findViewById(R.id.btn_back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initFab() {
        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });
        FloatingActionButton back = findViewById(R.id.btn_back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });
        FloatingActionButton Home = findViewById(R.id.fabHome2);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProductActivity.class));
            }
        });
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    public void postPesanan(View v){
        String userId = user.getUid();
        String email = user.getEmail();
        String namaPesanan = txtNamaMenuDetail.getText().toString();
        int hargaPesanan = Integer.parseInt(txtHargaMenuDetail.getText().toString().replaceAll("[^0-9]", ""));
        int jumlahPesanan = Integer.parseInt(edtJumlah.getText().toString());
        Toast.makeText(ProductDetailActivity.this, "Success add to cart", Toast.LENGTH_SHORT).show();

        fireDb.collection("pesanan").document()
                .set(new Pesanan(userId, email, namaPesanan, hargaPesanan, jumlahPesanan))
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("store_error", e.getMessage());
                    }
                });
        finish();
    }
}