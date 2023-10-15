package com.example.recyleviewdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.recyleviewdemo.model.AddActivity;
import com.example.recyleviewdemo.model.VegetableAdapter;
import com.example.recyleviewdemo.model.OnVegetableAddedListener;
import com.example.recyleviewdemo.model.Vegetable;
import com.example.recyleviewdemo.model.VegetableDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements VegetableAdapter.VegetableItemListener {
    private RecyclerView recyclerView;
    private Button editButton;
    private VegetableAdapter adapter;
    private Toolbar toolbar;
    public static OnVegetableAddedListener onVegetableAddedListener;
    private static final int ADD_PRODUCT_REQUEST_CODE = 1;
    private static final int UPDATE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editButton = findViewById(R.id.edit_button);
        recyclerView = findViewById(R.id.rview);
        adapter = new VegetableAdapter(getList());
        adapter.setVegetableItemListener(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        onVegetableAddedListener = new OnVegetableAddedListener() {
            @Override
            public void onVegetableAdded(Vegetable newVegetable) {
                adapter.add(newVegetable);
            }
        };

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, ADD_PRODUCT_REQUEST_CODE);
            }
        });
    }

    private List<Vegetable> getList() {
        List<Vegetable> list = new ArrayList<>();
        list.add(new Vegetable(R.drawable.bidao, "Bí đao", 300, "Bí đao (tên khoa học: Lagenaria siceraria) là một loại cây thảo, thuộc họ bầu bí (Cucurbitaceae), mà có thể được trồng để thu hoạch trái và được sử dụng trong nhiều món ăn và mục đích khác nhau"));
        list.add(new Vegetable(R.drawable.carot, "Cà rốt", 100, "Cà rốt (carrot) là một loại rau củ phổ biến được biết đến với màu cam sáng và hương vị ngọt, giòn"));
        list.add(new Vegetable(R.drawable.dualeo, "Dưa leo", 150, "Dưa leo (cucumber) là một loại rau củ phổ biến được biết đến với vị mát mẻ, giòn và thường có màu xanh sáng"));
        list.add(new Vegetable(R.drawable.khoaitay, "Khoai tây", 200, "Khoai tây (potato) là một loại cây củ rễ phổ biến và là một nguồn dinh dưỡng quan trọng trong ẩm thực trên khắp thế giới"));
        list.add(new Vegetable(R.drawable.khoailang, "Khoai lang", 350, "Khoai lang (sweet potato) là một loại cây củ rễ có nguồn gốc từ vùng nhiệt đới và cận nhiệt đới"));
        return list;
    }

    @Override
    public void onItemClick(View view, int position) {
        Vegetable selectedvegetable = adapter.getItem(position);
        //Truyền Intent qua VegetableDetail
        Intent intent = new Intent(MainActivity.this, VegetableDetail.class);
        intent.putExtra("selected_vegetable",selectedvegetable);
        startActivityForResult(intent, UPDATE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PRODUCT_REQUEST_CODE && resultCode == RESULT_OK) {
            // Nhận dữ liệu mới từ AddActivity
            Vegetable newVegetable = data.getParcelableExtra("new_vegetable");
            if (newVegetable != null) {
                onVegetableAddedListener.onVegetableAdded(newVegetable);
            }
        }
        if (requestCode == UPDATE_REQUEST_CODE && resultCode == RESULT_OK){
            //Lấy updateVegetable
            Vegetable updatedVegetable = data.getParcelableExtra("updated_vegetable");
            if (updatedVegetable != null){
                //Lấy vị trí updateVegetable
                int position = adapter.getPosition(updatedVegetable);
                if (position != -1){
                    //Thực hiện Update
                    adapter.updateItemAtPosition(position, updatedVegetable);
                }
            }
        }
    }
}
