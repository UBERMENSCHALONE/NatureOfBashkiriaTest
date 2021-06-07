package com.khairetdinova.bashkiria.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.activities.AssistantActivity;
import com.khairetdinova.bashkiria.activities.ObjectsActivity;
import com.khairetdinova.bashkiria.interfaces.ChangeFragment;

public class ObjectsFragment extends Fragment implements View.OnClickListener {

    private ChangeFragment changeFragment;

    private Button buttonMountains;
    private Button buttonCaves;
    private Button buttonLakes;
    private Button buttonWaterfalls;
    private Button buttonSpring;
    private Button buttonRocks;
    private Button buttonReservoirs;
    private Button buttonRidges;
    private Button buttonAssistant;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_objects, container, false);
        init(rootView);
        setClickListener();
        return rootView;
    }

    private void init(View rootView){
        buttonMountains = rootView.findViewById(R.id.buttonMountains);
        buttonCaves = rootView.findViewById(R.id.buttonCaves);
        buttonLakes = rootView.findViewById(R.id.buttonLakes);
        buttonWaterfalls = rootView.findViewById(R.id.buttonWaterfalls);
        buttonSpring = rootView.findViewById(R.id.buttonSpring);
        buttonRocks = rootView.findViewById(R.id.buttonRocks);
        buttonReservoirs = rootView.findViewById(R.id.buttonReservoirs);
        buttonRidges = rootView.findViewById(R.id.buttonRidges);
        buttonAssistant = rootView.findViewById(R.id.buttonAssistant);
    }

    private void setClickListener(){
        buttonMountains.setOnClickListener(this);
        buttonCaves.setOnClickListener(this);
        buttonLakes.setOnClickListener(this);
        buttonWaterfalls.setOnClickListener(this);
        buttonSpring.setOnClickListener(this);
        buttonRocks.setOnClickListener(this);
        buttonReservoirs.setOnClickListener(this);
        buttonRidges.setOnClickListener(this);
        buttonAssistant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), ObjectsActivity.class);

        switch (v.getId()){
            case R.id.buttonMountains:
                intent.putExtra("text", "Горы");
                startActivity(intent);
                break;
            case R.id.buttonCaves:
                intent.putExtra("text", "Пещеры");
                startActivity(intent);
                break;
            case R.id.buttonLakes:
                intent.putExtra("text", "Озера");
                startActivity(intent);
                break;
            case R.id.buttonWaterfalls:
                intent.putExtra("text", "Водопады");
                startActivity(intent);
                break;
            case R.id.buttonSpring:
                intent.putExtra("text", "Источники");
                startActivity(intent);
                break;
            case R.id.buttonRocks:
                intent.putExtra("text", "Скалы");
                startActivity(intent);
                break;
            case R.id.buttonReservoirs:
                intent.putExtra("text", "Водохранилища");
                startActivity(intent);
                break;
            case R.id.buttonRidges:
                intent.putExtra("text", "Хребты");
                startActivity(intent);
                break;
            case R.id.buttonAssistant:
                Intent intentAssistant = new Intent(getActivity(), AssistantActivity.class);
                startActivity(intentAssistant);
                break;
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ChangeFragment) {
            changeFragment = (ChangeFragment) context;
        }
    }

    public static ObjectsFragment newInstance() {
        return new ObjectsFragment();
    }

}