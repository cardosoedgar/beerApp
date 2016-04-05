package com.cardosoedgar.beerapp;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by edgarcardoso on 4/4/16.
 */
public class Beer extends SugarRecord {

    @SerializedName("nome") String nome;
    @SerializedName("paisDeOrigem") String paisDeOrigem;
    @SerializedName("estilo") String estilo;
    @SerializedName("cervejaria") String cervejaria;
    @SerializedName("abv") double abv;
    @SerializedName("temperaturaDeConsumo") String temperaturaDeConsumo;
    @SerializedName("copoIdeal") String copoIdeal;
    @SerializedName("aparencia") String aparencia;
    @SerializedName("descricao") String descricao;
    @SerializedName("ratebeercom") String ratebeercom;
    @SerializedName("score") String score;
    @SerializedName("imagem") String imagem;
    boolean isFavorite = false;
}
