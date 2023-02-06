package com.example.finalprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalprojectandroid.databinding.ActivityDetailBinding;

public class detailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type",0) == 1 ) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.medicineName.setText(name);
            binding.detailDescription.setText(description);

            binding.shopLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/CrR6VEY2rzTq8woy8"));
                    startActivity(intent);
                }
            });

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,// may be the name will change to medicineName
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted) {
                        Toast.makeText(detailActivity.this, "ordered successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(detailActivity.this,OrderActivity.class));
                    } else {
                        Toast.makeText(detailActivity.this, "some error occurred!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            final int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);
             /*
                id = 0
                name = 1
                phone 2
                price 3
                image 4
                quantity 5
                description 6
                medicineName 7
            */
            binding.detailImage.setImageResource(image);
            binding.medicineName.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));

            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.updateOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.medicineName.getText().toString(),
                            1,
                            id
                            );
                    if (isUpdated){
                        Toast.makeText(detailActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(detailActivity.this, "Failed!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}