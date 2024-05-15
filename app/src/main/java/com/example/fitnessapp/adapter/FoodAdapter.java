package com.example.fitnessapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.model.FoodItem;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context mContext;
    private List<FoodItem> mFoodList;

    public FoodAdapter(Context context, List<FoodItem> foodList) {
        mContext = context;
        mFoodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.food_item_layout, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodItem foodItem = mFoodList.get(position);
        holder.foodNameTextView.setText(foodItem.getName());
        holder.caloriesTextView.setText(String.valueOf(foodItem.getCalories()));

        // CheckBox durumunu güncelleyerek seçilen öğeyi işaretleyin
        holder.checkBox.setOnCheckedChangeListener(null); // OnCheckedChangeListener'ı geçici olarak kaldırın
        holder.checkBox.setChecked(foodItem.isSelected());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                foodItem.setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView;
        TextView caloriesTextView;
        CheckBox checkBox;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.food_name_text_view);
            caloriesTextView = itemView.findViewById(R.id.calories_text_view);
            checkBox = itemView.findViewById(R.id.food_checkbox);
        }
    }
}
