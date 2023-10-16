package com.example.recyleviewdemo.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyleviewdemo.MainActivity;
import com.example.recyleviewdemo.R;

import org.w3c.dom.Text;

public class VegetableDetail extends AppCompatActivity {
    EditText etDesc, etPrice;
    Button btnSave, btnDelete;
    TextView txtName;
    ImageView img;
    Vegetable selectedVegetable;
    VegetableAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_detail);
        initView();
        // Lấy dữ liệu từ Intent
        selectedVegetable = getIntent().getParcelableExtra("selected_vegetable");
        // Hiển thị dữ liệu trong giao diện

        txtName.setText(selectedVegetable.getName());
        img.setImageResource(selectedVegetable.getImg());
        etDesc.setText(selectedVegetable.getDes());
        etPrice.setText(String.valueOf(selectedVegetable.getPrice()));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String nameUpdate = txtName.getText().toString();
                    double priceUpdate = Double.parseDouble(etPrice.getText().toString());
                    String descUpdate = etDesc.getText().toString();
                    selectedVegetable.setDes(descUpdate);
                    selectedVegetable.setPrice(priceUpdate);
                    //New Intent để chuyển value qua lại MainActivity
                    Intent intent = new Intent(VegetableDetail.this, MainActivity.class);
                    intent.putExtra("updated_vegetable", selectedVegetable);
                    setResult(RESULT_OK, intent);
                    finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteButtonClick(v);
            }
        });
    }
    public void onDeleteButtonClick(View view) {
        if (selectedVegetable != null) {
                    Intent intent = new Intent(VegetableDetail.this, MainActivity.class);
                    intent.putExtra("deleted_vegetable", selectedVegetable);
                    setResult(RESULT_OK, intent);
                    finish();
        }
    }
    private void initView(){
        img = findViewById(R.id.vegetable_image);
        txtName = findViewById(R.id.vegetable_name);
        etDesc = findViewById(R.id.vegetable_description);
        etPrice = findViewById(R.id.vegetable_price);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
    }
}
