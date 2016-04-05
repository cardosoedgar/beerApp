package com.cardosoedgar.beerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by edgarcardoso on 4/4/16.
 */
public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerHolder> {

    List<Beer> beerList;
    Context context;

    public BeerAdapter(Context context, List<Beer> beerList) {
        this.context = context;
        this.beerList = beerList;
    }

    @Override
    public BeerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_row, parent, false);
        return new BeerHolder(v);
    }

    @Override
    public void onBindViewHolder(BeerHolder holder, int position) {
        Beer beer = beerList.get(position);
        if(beer.isFavorite) {
            holder.favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(
                    context.getResources(), R.drawable.ic_favorite_black_24dp, null));
        } else {
            holder.favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(
                    context.getResources(), R.drawable.ic_favorite_border_black_24dp, null));
        }

        Drawable beerImage = Utils.getImageFromAssets(context, beer.imagem);
        holder.beerImage.setImageDrawable(beerImage);
        holder.beerName.setText(beer.nome);
        holder.beerScore.setText(beer.score);
        holder.beerStyle.setText(beer.estilo);
        holder.position = position;
        holder.beer = beer;
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }

    public class BeerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.beer_name) TextView beerName;
        @Bind(R.id.beer_style) TextView beerStyle;
        @Bind(R.id.beer_score) TextView beerScore;
        @Bind(R.id.beer_image) ImageView beerImage;
        @Bind(R.id.favorite_button) ImageView favoriteButton;

        Beer beer;
        int position;

        public BeerHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, BeerActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }

        @OnClick(R.id.favorite_button)
        public void onClickFavoriteButton() {
            if(beer.isFavorite) {
                beer.isFavorite = false;
                favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(
                        context.getResources(), R.drawable.ic_favorite_border_black_24dp, null));
            } else {
                beer.isFavorite = true;
                favoriteButton.setImageDrawable(ResourcesCompat.getDrawable(
                        context.getResources(), R.drawable.ic_favorite_black_24dp, null));
            }
            beer.save();
        }
    }
}
