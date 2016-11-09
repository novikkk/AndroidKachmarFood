package com.eleks.bohdannovosad.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohdan.novosad on 04.11.2016.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ReceptViewHolder>{

    List<UiWithRecept.Recept> recepts ;

    public RVAdapter(List<UiWithRecept.Recept> recepts){
        this.recepts = recepts;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ReceptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_card, parent, false);
        ReceptViewHolder pvh = new ReceptViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ReceptViewHolder holder, int position) {
        holder.title.setText(recepts.get(position).name);
        holder.ingredients.setText(recepts.get(position).ing);

//        URL url = null;
//        try {
//            url = new URL(recepts.get(position).thumbnail);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap bmp = null;
//        try {
//            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holder.photo.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return recepts.size();
    }

    public static class ReceptViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView ingredients;
        ImageView photo;

        ReceptViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.title2);
            ingredients = (TextView)itemView.findViewById(R.id.ingrediences2);
            photo = (ImageView)itemView.findViewById(R.id.photo2);
        }
    }

}