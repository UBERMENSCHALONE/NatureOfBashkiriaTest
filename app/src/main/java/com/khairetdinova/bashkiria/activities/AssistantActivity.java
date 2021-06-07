package com.khairetdinova.bashkiria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.database.DatabaseHelper;
import com.khairetdinova.bashkiria.models.Place;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class AssistantActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private ImageView imageView;
    private SeekBar seekBar;
    private TextView textViewDistance;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    int distance = 0;
    int complexity = 1;

    private Button button;
    private SQLiteDatabase database;
    private List<Place> places;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        textViewDistance = findViewById(R.id.textViewDistance);
        imageView.setOnClickListener(v -> finish());

        radioButton1 = findViewById(R.id.radioButton1);
        radioButton1.setOnClickListener(radioButtonClickListener);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton2.setOnClickListener(radioButtonClickListener);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton3.setOnClickListener(radioButtonClickListener);
        Intent intent = new Intent(this, ObjectsActivity.class);
        button = findViewById(R.id.buttonShow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("text", "-");
                intent.putExtra("distance", distance);
                intent.putExtra("complexity", complexity);
                startActivity(intent);
            }
        });
    }

    private List<Place> initializationData() {
        DatabaseHelper mDBHelper = new DatabaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        database = mDBHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM places", null);

        places = new LinkedList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Place place = new Place();
            place.setId(cursor.getInt(0));
            place.setType(cursor.getString(1));
            place.setName(cursor.getString(2));
            place.setDifficultyTheRoute(cursor.getInt(3));
            place.setDistanceFromUfa(cursor.getInt(4));
            place.setCoordinatesX(cursor.getString(5));
            place.setCoordinatesY(cursor.getString(6));
            place.setImages(cursor.getString(7));
            place.setDescription(cursor.getString(8));
            if(place.getDifficultyTheRoute() == complexity && place.getDistanceFromUfa() >= distance) places.add(place);
            cursor.moveToNext();
        }
        return places;
    }

    View.OnClickListener radioButtonClickListener = v -> {
        RadioButton rb = (RadioButton)v;
        switch (rb.getId()) {
            case R.id.radioButton1: complexity = 1;
                break;
            case R.id.radioButton2: complexity = 2;
                break;
            case R.id.radioButton3: complexity = 3;
                break;
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        textViewDistance.setText(seekBar.getProgress() + " км");
        distance = seekBar.getProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        textViewDistance.setText(seekBar.getProgress() + " км");
        distance = seekBar.getProgress();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        textViewDistance.setText(seekBar.getProgress() + " км");
        distance = seekBar.getProgress();
    }
}