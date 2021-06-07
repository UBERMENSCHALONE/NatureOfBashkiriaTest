package com.khairetdinova.bashkiria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.database.DatabaseHelper;
import com.khairetdinova.bashkiria.models.Place;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ObjectActivity extends AppCompatActivity {

    private int objectId = 0;
    private SQLiteDatabase database;
    private List<Place> places;
    private TextView textViewDescription;
    private TextView textView;
    private ImageView imageView;
    private ImageView imageViewImg;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object);

        Bundle arguments = getIntent().getExtras();
        objectId = arguments.getInt("objectId");
        Place place = initializationData(objectId);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewDescription.setText(place.getDescription());
        textView = findViewById(R.id.textView);
        textView.setText(place.getName());
        imageView = findViewById(R.id.imageView);
        imageViewImg = findViewById(R.id.imageViewImg);
        imageView.setOnClickListener(v -> finish());

        InputStream ims = getClass().getResourceAsStream("/assets/img/" + place.getImages() + ".jpg");
        imageViewImg.setImageDrawable(Drawable.createFromStream(ims, null));

        button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Uri navigationIntentUri = Uri.parse("google.navigation:q=" + place.getCoordinatesX() +"," + place.getCoordinatesY());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });
    }

    private Place initializationData(int objectId) {
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
            places.add(place);
            cursor.moveToNext();
        }
        return places.get(objectId);
    }
}