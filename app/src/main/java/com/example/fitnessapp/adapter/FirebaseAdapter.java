package com.example.fitnessapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitnessapp.R;
import com.example.fitnessapp.model.FoodItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;


public class FirebaseAdapter extends FirebaseRecyclerAdapter<FoodItem, FirebaseAdapter.FoodViewHolder> {

    private static final int MORNING_VIEW_TYPE = 0;
    private static final int LUNCH_VIEW_TYPE = 1;
    private static final int EVENING_VIEW_TYPE = 2;

    private List<FoodItem> morningFoodList = new ArrayList<>();
    private List<FoodItem> lunchFoodList = new ArrayList<>();
    private List<FoodItem> eveningFoodList = new ArrayList<>();

    private Context mContext;

    public FirebaseAdapter(@NonNull FirebaseRecyclerOptions<FoodItem> options, Context context) {
        super(options);
        this.mContext = context;
    }

    public List<FoodItem> getEveningFoodList() {
        return eveningFoodList;
    }

    public List<FoodItem> getMorningFoodList() {
        return morningFoodList;
    }

    public List<FoodItem> getLunchFoodList() {
        return lunchFoodList;
    }

    @Override
    protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull FoodItem model) {
        holder.foodNameTextView.setText(model.getName());
        holder.caloriesTextView.setText(String.valueOf(model.getCalories()));

        FoodItem item = getItem(position);
        if (item != null) {
            if (getItemViewType(position) == MORNING_VIEW_TYPE) {
                morningFoodList.add(item);
            } else if (getItemViewType(position) == LUNCH_VIEW_TYPE) {
                lunchFoodList.add(item);
            } else if (getItemViewType(position) == EVENING_VIEW_TYPE) {
                eveningFoodList.add(item);
            }
        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new FoodViewHolder(view);
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView;
        TextView caloriesTextView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.food_name_text_view);
            caloriesTextView = itemView.findViewById(R.id.calories_text_view);
        }
    }
}

