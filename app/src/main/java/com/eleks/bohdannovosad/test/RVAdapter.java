package com.eleks.bohdannovosad.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohdan.novosad on 04.11.2016.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ReceptViewHolder>{

    private Context context;
    List<UiWithRecept.Recept> recepts ;
    Picasso picasso;

    public RVAdapter(List<UiWithRecept.Recept> recepts, Picasso picasso, Context context){
        this.context = context;
        this.recepts = recepts;
        this.picasso = picasso;
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
    public void onBindViewHolder(ReceptViewHolder holder, final int position) {
        holder.title.setText(recepts.get(position).name);
        holder.ingredients.setText(recepts.get(position).ing);
        picasso.load(Uri.parse(recepts.get(position).thumbnail)).resize(200,200).into(holder.photo);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(recepts.get(position).href));
                context.startActivity(i);

            }
        });
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