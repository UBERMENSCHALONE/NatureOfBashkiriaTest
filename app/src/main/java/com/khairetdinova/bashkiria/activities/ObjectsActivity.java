package com.khairetdinova.bashkiria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.adaters.PlaceAdapter;
import com.khairetdinova.bashkiria.database.DatabaseHelper;
import com.khairetdinova.bashkiria.models.Place;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectsActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;
    private String text = null;
    private RecyclerView recyclerView;
    private SQLiteDatabase database;
    private List<Place> places;
    int distance = 0;
    int complexity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);

        Bundle arguments = getIntent().getExtras();
        text = arguments.get("text").toString();
        distance = arguments.getInt("distance");
        complexity = arguments.getInt("complexity");

        textView = findViewById(R.id.textView);

        if(text.contains("-")) {
            textView.setText("Объекты");
        } else {
            textView.setText(text);
        }

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.recyclerView);

        places = initializationData(text);
        PlaceAdapter placeAdapter = new PlaceAdapter(this, places);
        recyclerView.setAdapter(placeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Place> initializationData(String text) {
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
            if(place.getType().contains(text)) places.add(place);
            if(place.getDistanceFromUfa() <= distance && place.getDifficultyTheRoute() == complexity) places.add(place);
            cursor.moveToNext();
        }
        return places;
    }
}