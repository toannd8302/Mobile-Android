package com.example.recyleviewdemo.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyleviewdemo.R;

import org.w3c.dom.Text;

public class VegetableDetail extends AppCompatActivity {
    EditText des, price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_detail);
        // Lấy dữ liệu từ Intent
        String vegetableName = getIntent().getStringExtra("vegetable_name");
        int vegetableImageResource = getIntent().getIntExtra("vegetable_image", R.drawable.bidao); // Mặc định nếu không có dữ liệu
        String vegetableDescription = getIntent().getStringExtra("vegetable_des");
        double vegetablePrice = getIntent().getDoubleExtra("vegetable_price", 0.0); // Giá trị mặc định nếu không có dữ liệu

        // Hiển thị dữ liệu trong giao diện
        TextView nameTextView = findViewById(R.id.vegetable_name);
        ImageView imageView = findViewById(R.id.vegetable_image);
        TextView descriptionTextView = findViewById(R.id.vegetable_description);
        TextView priceTextView = findViewById(R.id.vegetable_price);

        nameTextView.setText(vegetableName);
        imageView.setImageResource(vegetableImageResource);
        descriptionTextView.setText(vegetableDescription);
        priceTextView.setText(String.valueOf(vegetablePrice));
    }
    }
