package com.example.fitnessapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnessapp.adapter.FirebaseAdapter;
import com.example.fitnessapp.model.FoodItem;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseAdapter morningAdapter, lunchAdapter, eveningAdapter;
    private List<FoodItem> morningFoodList = new ArrayList<>();
    private List<FoodItem> lunchFoodList = new ArrayList<>();
    private List<FoodItem> eveningFoodList = new ArrayList<>();
    private RecyclerView morningRecyclerView, lunchRecyclerView, eveningRecyclerView;
    private boolean isMorningVisible = true;
    private boolean isLunchVisible = true;
    private boolean isEveningVisible = true;

    private Button calculateCaloriesButton, calculateBMIButton;
    private TextView totalCaloriesTextView, bmiResultTextView;
    private EditText heightEditText, weightEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        heightEditText = view.findViewById(R.id.height_editText);
        weightEditText = view.findViewById(R.id.weight_editText);
        calculateBMIButton = view.findViewById(R.id.calculate_bmi_button);
        bmiResultTextView = view.findViewById(R.id.bmi_result_textview);

        calculateBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        calculateCaloriesButton = view.findViewById(R.id.calculate_calories_button);
        totalCaloriesTextView = view.findViewById(R.id.total_calories_textview);

        calculateCaloriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalCalories();
            }
        });

        morningRecyclerView = view.findViewById(R.id.morning_recyclerview);
        morningRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference morningRef = FirebaseDatabase.getInstance().getReference().child("foods").child("breakfast");
        FirebaseRecyclerOptions<FoodItem> morningOptions =
                new FirebaseRecyclerOptions.Builder<FoodItem>()
                        .setQuery(morningRef, FoodItem.class)
                        .build();
        morningAdapter = new FirebaseAdapter(morningOptions, getContext());
        morningRecyclerView.setAdapter(morningAdapter);


        lunchRecyclerView = view.findViewById(R.id.lunch_recyclerview);
        lunchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference lunchRef = FirebaseDatabase.getInstance().getReference().child("foods").child("lunch");
        FirebaseRecyclerOptions<FoodItem> lunchOptions =
                new FirebaseRecyclerOptions.Builder<FoodItem>()
                        .setQuery(lunchRef, FoodItem.class)
                        .build();
        lunchAdapter = new FirebaseAdapter(lunchOptions, getContext());
        lunchRecyclerView.setAdapter(lunchAdapter);


        eveningRecyclerView = view.findViewById(R.id.evening_recyclerview);
        eveningRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference eveningRef = FirebaseDatabase.getInstance().getReference().child("foods").child("dinner");
        FirebaseRecyclerOptions<FoodItem> eveningOptions =
                new FirebaseRecyclerOptions.Builder<FoodItem>()
                        .setQuery(eveningRef, FoodItem.class)
                        .build();
        eveningAdapter = new FirebaseAdapter(eveningOptions, getContext());
        eveningRecyclerView.setAdapter(eveningAdapter);

        TextView morningText = view.findViewById(R.id.morning_text);
        TextView lunchText = view.findViewById(R.id.lunch_text);
        TextView eveningText = view.findViewById(R.id.evening_text);

        morningText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecyclerViewVisibility(morningRecyclerView);
                isMorningVisible = !isMorningVisible;
            }
        });

        lunchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecyclerViewVisibility(lunchRecyclerView);
                isLunchVisible = !isLunchVisible;
            }
        });

        eveningText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleRecyclerViewVisibility(eveningRecyclerView);
                isEveningVisible = !isEveningVisible;
            }
        });

        return view;
    }

    private void calculateBMI() {
        double height = Double.parseDouble(heightEditText.getText().toString());
        double weight = Double.parseDouble(weightEditText.getText().toString());
        double bmi = weight / ((height / 100) * (height / 100));

        String formattedBMI = String.format("%.2f", bmi);
        bmiResultTextView.setText("BMI Sonucu: " + formattedBMI);
        if(bmi < 18.5){
            bmiResultTextView.setText(formattedBMI+" " + "İdeal kilonuzun altındasınız");
        }else if(bmi>=18.5 && bmi<=24.9 ){
            bmiResultTextView.setText(formattedBMI+" " + "İdeal kilonuzdasınız");
        }else{
            bmiResultTextView.setText(formattedBMI+" " + "İdeal kilonuzun üstündesiniz");
        }
    }

    private void toggleRecyclerViewVisibility(RecyclerView recyclerView) {
        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateTotalCalories() {
        int totalCalories = 0;
        for (FoodItem food : morningAdapter.getMorningFoodList()) {
            totalCalories += food.getCalories();
        }
        for (FoodItem food : lunchAdapter.getLunchFoodList()) {
            totalCalories += food.getCalories();
        }
        for (FoodItem food : eveningAdapter.getEveningFoodList()) {
            totalCalories += food.getCalories();
        }
        totalCaloriesTextView.setText(String.valueOf(totalCalories));
    }

    @Override
    public void onStart() {
        super.onStart();
        morningAdapter.startListening();
        lunchAdapter.startListening();
        eveningAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        morningAdapter.stopListening();
        lunchAdapter.stopListening();
        eveningAdapter.stopListening();
    }
}
