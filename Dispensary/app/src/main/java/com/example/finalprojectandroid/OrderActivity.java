package com.example.finalprojectandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.finalprojectandroid.Adopters.OrderAdopter;
import com.example.finalprojectandroid.Models.OrderModel;
import com.example.finalprojectandroid.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActionBar title = getSupportActionBar();
        title.setTitle("Dispensary");

        DBHelper helper = new DBHelper(this);
        ArrayList<OrderModel> list = helper.getOrders();


        OrderAdopter adopter = new OrderAdopter(list,this);
        binding.orderRecyclerView.setAdapter(adopter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(OrderActivity.this,OrderActivity.class));
                break;
            case R.id.logout:
                startActivity(new Intent(OrderActivity.this,AccountActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}