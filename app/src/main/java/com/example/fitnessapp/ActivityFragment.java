package com.example.fitnessapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class ActivityFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);

        LinearLayout cardioLayout = rootView.findViewById(R.id.cardioLayout);
        LinearLayout enduranceLayout = rootView.findViewById(R.id.enduranceLayout);
        LinearLayout strengthLayout = rootView.findViewById(R.id.strengthLayout);
        LinearLayout flexibilityLayout = rootView.findViewById(R.id.flexibilityLayout);

        cardioLayout.setOnClickListener(this);
        enduranceLayout.setOnClickListener(this);
        strengthLayout.setOnClickListener(this);
        flexibilityLayout.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int viewId = v.getId();
        if (viewId == R.id.cardioLayout) {
            intent = new Intent(getActivity(), CardioExerciseActivity.class);
        } else if (viewId == R.id.enduranceLayout) {
            intent = new Intent(getActivity(), EnduranceExerciseActivity.class);
        } else if (viewId == R.id.strengthLayout) {
            intent = new Intent(getActivity(), StrengthExerciseActivity.class);
        } else if (viewId == R.id.flexibilityLayout) {
            intent = new Intent(getActivity(), FlexibilityExerciseActivity.class);
        } else {
            return;
        }
        startActivity(intent);
    }

}
