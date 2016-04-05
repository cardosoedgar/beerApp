package com.cardosoedgar.beerapp;

import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BeerActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;

    @Bind(R.id.beer_name) TextView beerName;
    @Bind(R.id.beer_style) TextView beerStyle;
    @Bind(R.id.beer_brewery) TextView beerBrewery;
    @Bind(R.id.beer_country) TextView beerCountry;
    @Bind(R.id.beer_abv) TextView beerABV;
    @Bind(R.id.beer_temp) TextView beerTemp;
    @Bind(R.id.beer_glass) TextView beerGlass;
    @Bind(R.id.beer_description) TextView beerDescription;
    @Bind(R.id.beer_image) ImageView beerImage;
    @Bind(R.id.beer_score) TextView beerScore;
    @Bind(R.id.favorite_button) ImageView favoriteButton;

    Beer beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        loadBeer();
    }

    private void loadBeer() {
        int position = getIntent().getIntExtra("position", 0);
        position++;

        beer = Beer.findById(Beer.class, position);
        String percent = Math.round(beer.abv * 100) + "%";

        beerImage.setImageDrawable(Utils.getImageFromAssets(this, beer.imagem));
        beerName.setText(beer.nome);
        beerStyle.setText(beer.estilo);
        beerCountry.setText(beer.paisDeOrigem);
        beerABV.setText(percent);
        beerTemp.setText(beer.temperaturaDeConsumo);
        beerGlass.setText(beer.copoIdeal);
        beerDescription.setText(beer.descricao);
        beerBrewery.setText(beer.cervejaria);
        beerScore.setText(beer.score);
        setFavoriteButton();
    }

    private void setFavoriteButton() {
        if(beer.isFavorite) {
            favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(
                    getResources(), R.drawable.ic_favorite_black_24dp, null));
        } else {
            favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(
                    getResources(), R.drawable.ic_favorite_border_black_24dp, null));
        }
    }

    @OnClick(R.id.favorite_button)
    public void onClickFavoriteButton() {
        beer.isFavorite = !beer.isFavorite;
        beer.save();
        EventBus.getDefault().post(new Message(beer.getId()));
        setFavoriteButton();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
