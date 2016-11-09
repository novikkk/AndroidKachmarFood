package com.eleks.bohdannovosad.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bohdan.novosad on 04.11.2016.
 */

public class UiWithRecept extends AppCompatActivity {
    private JSONObject jsonObject = null;
    private List<Recept> recepts = new ArrayList<>();
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_recept);

        rv = (RecyclerView) findViewById(R.id.rv);
        Intent intent = getIntent();
        try {
            jsonObject = new JSONObject(intent.getStringExtra("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsonObject!=null) {
            initializeData(jsonObject);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(llm);

            RVAdapter adapter = new RVAdapter(recepts,Picasso.with(this));
            rv.setAdapter(adapter);

        }
    }

    private void initializeData(JSONObject data){
        JSONArray results = null;
        try {
            results = data.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0;i<results.length();i++){
            try {
                recepts.add(new Recept(results.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    public class Recept{
        String name;
        String ing;
        String href;
        String thumbnail;
        public Recept(JSONObject jsonObject) {
            try {
                name = jsonObject.getString("title");
                name = name.replaceAll("\t", "");
                name = name.replaceAll("\n", "");
                ing = jsonObject.getString("ingredients");
                ing = ing.replaceAll("\t", "");
                ing = ing.replaceAll("\n", "");
                thumbnail = jsonObject.getString("thumbnail");
                href = jsonObject.getString("href");
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}


