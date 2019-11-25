package com.kalanyr.alphapokedex.Interface;

import android.view.View;

import com.kalanyr.alphapokedex.Model.Pokemon;

public interface IItemClickListener {
    void onClick(Pokemon pokemon, Integer position);
}
