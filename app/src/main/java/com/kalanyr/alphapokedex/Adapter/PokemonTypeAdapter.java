package com.kalanyr.alphapokedex.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kalanyr.alphapokedex.Common.Common;
import com.kalanyr.alphapokedex.Interface.IItemClickListener;
import com.kalanyr.alphapokedex.Model.Pokemon;
import com.kalanyr.alphapokedex.R;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnChipClickListener;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder> {
    Context context;
    List<String> typeList;

    public PokemonTypeAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item,parent,false);
        return new MyViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.chip.setChipText(typeList.get(position));
        holder.chip.changeBackgroundColor(Common.getColorByType(typeList.get(position)));
    }

    @Override
    public int getItemCount() {

        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        IItemClickListener iItemClickListener;
        List<Pokemon> pokemonList;
        public MyViewHolder(View itemView, final int position){
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnChipClickListener(new OnChipClickListener(){
                public void onChipClick(View v) {
                    iItemClickListener.onClick(pokemonList.get(position), position);
                }
            });
        }
    }

}
