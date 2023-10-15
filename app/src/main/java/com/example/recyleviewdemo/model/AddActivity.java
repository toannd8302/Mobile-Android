package com.example.recyleviewdemo.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recyleviewdemo.R;

public class AddActivity extends AppCompatActivity {
    private Spinner sp;
    private VegetableAdapter adapter;
    private EditText eName, ePrice, eDesc;
    private Button btAdd;
    private OnVegetableAddedListener onVegetableAddedListener;
    private int[]imgs = {R.drawable.cudau,R.drawable.cuden};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vegetable vege = new Vegetable();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDesc.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.download;
                double price = 0;

                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);

                    vege.setImg(img);
                    vege.setName(name);
                    vege.setPrice(price);
                    vege.setDes(desc);

                    // Tạo một Intent để chứa dữ liệu Vegetable và gửi nó trở lại MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("new_vegetable", vege);
                    setResult(RESULT_OK, intent);

                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(){
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        eName = findViewById(R.id.vegetable_name);
        ePrice = findViewById(R.id.vegetable_price);
        eDesc = findViewById(R.id.vegetable_description);

        btAdd = findViewById(R.id.add);
    }


}