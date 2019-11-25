package com.kalanyr.alphapokedex.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kalanyr.alphapokedex.Interface.IItemClickListener;
import com.kalanyr.alphapokedex.Model.Pokemon;
import com.kalanyr.alphapokedex.R;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder>{

    Context context;
    List<Pokemon> pokemonList;
    private IItemClickListener listener;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList, IItemClickListener listener) {
        this.context = context;
        this.pokemonList = pokemonList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        //Load
        Glide.with(context).load(pokemonList.get(position).getImg()).into(holder.pokemon_image);
        //Change name
        holder.pokemon_name.setText(pokemonList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(pokemonList.get(position), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemon_image;
        TextView pokemon_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pokemon_image = (ImageView)itemView.findViewById(R.id.pokemon_image);
            pokemon_name = (TextView)itemView.findViewById(R.id.txt_pokemon_name);
        }
    }
}
