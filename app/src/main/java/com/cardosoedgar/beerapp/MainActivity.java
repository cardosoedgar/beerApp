package com.cardosoedgar.beerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    BeerAdapter adapter;
    List<Beer> beerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        beerList = Utils.readBeerJson(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BeerAdapter(this,beerList);
        recyclerView.setAdapter(adapter);
    }
}
