package com.cardosoedgar.beerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.orm.SugarRecord;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    BeerAdapter adapter;
    List<Beer> beerList;
    long updateBeerData = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        EventBus.getDefault().register(this);
        loadBeerlist();
        setupRecyclerView();
    }

    private void loadBeerlist() {
        beerList = Beer.listAll(Beer.class);
        if(beerList.size() == 0) {
            beerList = Utils.readBeerJson(this);
            SugarRecord.saveInTx(beerList);
        }
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BeerAdapter(this,beerList);
        recyclerView.setAdapter(adapter);
    }

    @Subscribe
    public void updateData(Message message) {
        updateBeerData = message.idBeer;
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkDataHasChanged();
    }

    private void checkDataHasChanged() {
        if(updateBeerData != -1) {
            int arrayPosition = (int)updateBeerData-1;
            beerList.remove(arrayPosition);
            Beer beer = Beer.findById(Beer.class, updateBeerData);
            beerList.add(arrayPosition, beer);
            adapter.notifyItemChanged(arrayPosition);
            updateBeerData = -1;
        }
    }
}
