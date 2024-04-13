package com.example.myfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    String namaUser;
    TextView txtNama;

    FirebaseUser user;
    FirebaseAuth mAuth;

    FirebaseFirestore fireDb;

    private RecyclerView recPenyetan;
    private RecyclerView recDrinks;
    private ArrayList<Penyetan> listPenyetan;
    private ArrayList<Drinks> listDrinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initFab();

        fireDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        namaUser = user.getEmail();

        recPenyetan = findViewById(R.id.rec_penyetan);
        recDrinks = findViewById((R.id.rec_drinks));
        initDataPenyetan();
        initDataDrinks();

        recPenyetan.setAdapter(new PenyetanAdapter(listPenyetan));
        recPenyetan.setLayoutManager(new LinearLayoutManager(this));

        recDrinks.setAdapter(new DrinksAdapter(listDrinks));
        recDrinks.setLayoutManager(new LinearLayoutManager(this));

        txtNama = findViewById(R.id.txtNama);
        txtNama.setText("Hi, " + namaUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFab(){
        FloatingActionButton fabCart = findViewById(R.id.fabCart);
        fabCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), CartActivity.class));
            }
        });
        FloatingActionButton fabLogout = findViewById(R.id.fabLogout);
        fabLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getBaseContext(), splash.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    private void initDataPenyetan(){
        this.listPenyetan =  new ArrayList<>();
        listPenyetan.add(new Penyetan("T-shirt Compsphere", "70000", R.drawable.baju1));
        listPenyetan.add(new Penyetan("T-shirt Compgath", "80000", R.drawable.baju3));
        listPenyetan.add(new Penyetan("T-shirt Computing", "65000", R.drawable.baju5));
        listPenyetan.add(new Penyetan("Varsity Computing", "120000", R.drawable.varsity2));
        listPenyetan.add(new Penyetan("Varsity Compsphere", "110000", R.drawable.varsity1));
        listPenyetan.add(new Penyetan("Varsity CSGO", "100000", R.drawable.varsity));
    }

    private void initDataDrinks(){
        this.listDrinks = new ArrayList<>();
        listDrinks.add(new Drinks("MousePad Compsphere", "15000", R.drawable.mousepad));
        listDrinks.add(new Drinks("Tumblr CSGO", "50000", R.drawable.tumblr));
        listDrinks.add(new Drinks("Lanyard Computing", "20000", R.drawable.lanyard));
        listDrinks.add(new Drinks("Totebag Computing", "45000", R.drawable.totebag));
        listDrinks.add(new Drinks("Sticker Compsphere", "10000", R.drawable.sticker));
        listDrinks.add(new Drinks("Mouse Computing", "60000", R.drawable.mouse));
    }
}