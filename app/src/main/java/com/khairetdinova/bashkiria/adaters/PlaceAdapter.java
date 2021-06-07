package com.khairetdinova.bashkiria.adaters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.khairetdinova.bashkiria.R;
import com.khairetdinova.bashkiria.activities.ObjectActivity;
import com.khairetdinova.bashkiria.models.Place;

import java.io.InputStream;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<Place> places;
    private Context context;

    public PlaceAdapter(Context context, List<Place> places) {
        this.context = context;
        this.places = places;
    }

    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceAdapter.ViewHolder viewHolder, final int position) {
        final Place places = this.places.get(position);

        TextView textView = viewHolder.textViewName;
        ImageView imageView = viewHolder.imageView;
        CardView card = viewHolder.card;
        textView.setText(places.getName());

        InputStream ims = getClass().getResourceAsStream("/assets/img/" + places.getImages() + ".jpg");
        imageView.setImageDrawable(Drawable.createFromStream(ims, null));

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != RecyclerView.NO_POSITION) {
                    Intent intent = new Intent(context, ObjectActivity.class);
                    intent.putExtra("objectId", places.getId() - 1);
                    context.startActivity(intent);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewName;
        public ImageView imageView;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageViewDelete);
            card = itemView.findViewById(R.id.card);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}