package com.example.recyleviewdemo.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyleviewdemo.R;
import java.util.List;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.CartViewHolder> {
    private List<Vegetable> mList;
    private VegetableItemListener vegetableItemListener;


    public VegetableAdapter(List<Vegetable> mList) {
        this.mList = mList;
    }

    public void setVegetableItemListener(VegetableItemListener vegetableItemListener) {
        this.vegetableItemListener = vegetableItemListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Vegetable vegetable = mList.get(position);
        holder.img.setImageResource(vegetable.getImg());
        holder.tv.setText(vegetable.getName());
        holder.tv2.setText(String.valueOf(vegetable.getPrice()));
    }

    public void add(Vegetable vegetable) {
        mList.add(vegetable);
        notifyDataSetChanged();
    }

    public Vegetable getItem(int position) {
        return mList.get(position);
    }
    //Lấy vị trí của Update Vegetable
    public void updateItemAtPosition(int position, Vegetable updatedVegetable) {
        if (position >= 0 && position < mList.size()) {
            mList.set(position, updatedVegetable);
            notifyItemChanged(position);
        }
    }
    //Lấy vị trí của selected Vegetable
    public int getPosition(Vegetable vegetable) {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getName().equals(vegetable.getName())){
                return i; // Found the position
            }
        }
        return -1; // Not found
    }
    @Override
    public int getItemCount() {
        if (mList != null) return mList.size();
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView tv,tv2;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.cat1);
            tv = itemView.findViewById(R.id.name);
            tv2 = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (vegetableItemListener != null) {
                vegetableItemListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface VegetableItemListener {
        void onItemClick(View view, int position);
    }
}
