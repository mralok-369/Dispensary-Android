package com.example.finalprojectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;

import com.example.finalprojectandroid.Adopters.MainAdopter;
import com.example.finalprojectandroid.Models.MainModel;
import com.example.finalprojectandroid.databinding.ActivityHomepageBinding;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    ActivityHomepageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar title = getSupportActionBar();
        title.setTitle("Dispensary");

        ArrayList<MainModel> list = new ArrayList<>();
        list.add(new MainModel(R.drawable.md1,"Paracetomol","5","Paracetamol is a common painkiller used to treat aches and pain and to reduce a high temperature."));
        list.add(new MainModel(R.drawable.md2,"Antihistamines","10","This medicines work well to relieve symptoms of different types of allergies, including seasonal fever."));
        list.add(new MainModel(R.drawable.md3,"Antacids","15","Antacids are medicines that counteract the acid in your stomach to relieve indigestion and heartburn."));
        list.add(new MainModel(R.drawable.md4,"Vitamin C","8","It's also known as ascorbic acid, is necessary for the growth, development and repair of all body tissues."));
        list.add(new MainModel(R.drawable.md5,"Antiseptic Cream","50","This antiseptic cream soothes and heals wounds, protects against infection minor burns and scalds."));
        list.add(new MainModel(R.drawable.md6,"Cephalexin","10","It is used to treat infections caused by bacteria, including upper respiratory and ear infections."));
        list.add(new MainModel(R.drawable.md7,"Azithromycin","20","It is used to treat certain bacterial infections, such as bronchitis pneumonia and infections of the ears."));
        list.add(new MainModel(R.drawable.md8,"Ibuprofen","15"," ibuprofen is used to reduce fever and to relieve minor aches and pain from headaches, muscle aches, arthritis."));
        list.add(new MainModel(R.drawable.md9,"Vitamin D","12","It is a fat-soluble vitamin that has long been known to help the body absorb and retain calcium and phosphorus."));
        list.add(new MainModel(R.drawable.md10,"Amoxicillin","16","Amoxicillin is a penicillin antibiotic. It is used to treat bacterial infections, such as chest infections."));

        MainAdopter adopter = new MainAdopter(list,this);
        binding.recycleView.setAdapter(adopter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycleView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(HomepageActivity.this,OrderActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(HomepageActivity.this,AccountActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}