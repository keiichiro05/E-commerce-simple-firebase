package com.example.myfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailCartActivity extends AppCompatActivity {
    TextView txtPesanan, txtJmlHarga;

    FirebaseFirestore fireDb;
    Pesanan pesanan;

    String del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cart);
        initFab();

        txtPesanan = findViewById(R.id.txt_pesanan_detail);
        txtJmlHarga = findViewById(R.id.txt_jmlharga_detail);

        Intent it = getIntent();
        Pesanan pesanan = (Pesanan) it.getSerializableExtra("pesanan");
        txtPesanan.setText(pesanan.namaPesanan + " (" + String.valueOf(pesanan.jumlahPesanan) +
                " x " + rp(Integer.parseInt(String.valueOf(pesanan.hargaPesanan))) + ')');
        txtJmlHarga.setText(rp(Integer.parseInt(String.valueOf(pesanan.hargaPesanan * pesanan.jumlahPesanan))));

        fireDb = FirebaseFirestore.getInstance();

        del = pesanan.namaPesanan;
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    public void close(View v){
        finish();
    }

    private void initFab() {
        FloatingActionButton fabCart= findViewById(R.id.fabCart);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });
        FloatingActionButton fabHome= findViewById(R.id.fabHome1);
        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ProductActivity.class));
            }
        });
        FloatingActionButton back= findViewById(R.id.fabBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });
    }


    public void delete(View v){
        fireDb.collection("pesanan").whereEqualTo("namaPesanan",del)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty()){
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documenID = documentSnapshot.getId();
                            Toast.makeText(DetailCartActivity.this, "Delete Item", Toast.LENGTH_SHORT).show();
                            fireDb.collection("pesanan")
                                    .document(documenID)
                                    .delete();
                        }
                    }
                });
        startActivity(new Intent(this, ProductActivity.class));
    }
}