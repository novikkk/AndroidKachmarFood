package com.eleks.bohdannovosad.test;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView textView;
    Button add, search;
    ListView listView;

    List<String> ingredients = new ArrayList<>();
    private String mUrl = "http://www.recipepuppy.com/api/?i=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.addButton);
        search = (Button) findViewById(R.id.searchButton);
        listView = (ListView) findViewById(R.id.listView);
        textView = (AutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView4);


        addIngredientsToList("tomato, onions, red pepper, garlic, olive oil, zucchini, cream cheese, vermicelli, eggs, parmesan cheese, milk, italian seasoning, salt, black pepper");
        String[] values = (String[]) INGREDIENTS_LIST.toArray(new String[INGREDIENTS_LIST.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,values);

        textView.setAdapter(adapter);


        ArrayAdapter<String> recyclerList = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,values);

        final BaseAdapter baseAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,ingredients);

        listView.setAdapter(baseAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setIcon(R.drawable.ic_error);

                builder.setMessage("Delete ingredient "+ ingredients.get(position)+" ?");

                builder.setTitle("Delete");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        ingredients.remove(position);
                        baseAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });


// Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                return false;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().length()!=0) {

                    String one = textView.getText().toString();
                    if(ingredients.contains(one)){
                        Toast.makeText(MainActivity.this,"This ingredient already exist!!!",Toast.LENGTH_LONG).show();

                    }else {
                        ingredients.add(one);
                        textView.setText("");
                        baseAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRequest();
            }
        });

    }


    void addIngredientsToList(String s){
        String []values = s.split(", ");
        for (String one: values)
            INGREDIENTS_LIST.add(one);
    }
    private static final Set<String> INGREDIENTS_LIST = new HashSet<>();


    private void createRequest() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        StringBuilder requestString = new StringBuilder();

        for (String one:ingredients){
            if(ingredients.indexOf(one)!=ingredients.size()-1)
                requestString.append(one+",");
            else
                requestString.append(one);
        }
// Request a string response from the provided URL.


        StringRequest stringRequest = new StringRequest(Request.Method.GET, mUrl+requestString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MainActivity.this.ShowUiWithRecept(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    private void ShowUiWithRecept(String response) {
        Intent intent = new Intent(this, UiWithRecept.class);
        intent.putExtra("data",response);
        this.startActivity(intent);
    }

}
