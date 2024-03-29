package com.kalanyr.alphapokedex;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.kalanyr.alphapokedex.Adapter.PokemonListAdapter;
import com.kalanyr.alphapokedex.Common.ItemOffsetDecoration;
import com.kalanyr.alphapokedex.Interface.IItemClickListener;
import com.kalanyr.alphapokedex.Model.Pokedex;
import com.kalanyr.alphapokedex.Model.Pokemon;
import com.kalanyr.alphapokedex.Retrofit.IPokemonDex;
import com.kalanyr.alphapokedex.Retrofit.RetrofitClient;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonList extends Fragment {

    IPokemonDex iPokemonDex;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    RecyclerView pokemon_list_recyclerview;


    static PokemonList instance;

    public static PokemonList getInstance(){
        if(instance == null)
            instance = new PokemonList();
        return instance;
    }


    public PokemonList() {
        Retrofit retrofit = RetrofitClient.getInstance();
        iPokemonDex = retrofit.create(IPokemonDex.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        pokemon_list_recyclerview = (RecyclerView)view.findViewById(R.id.pokemon_list_recyclerview);
        pokemon_list_recyclerview.setHasFixedSize(true);
        pokemon_list_recyclerview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.spacing);
        pokemon_list_recyclerview.addItemDecoration(itemOffsetDecoration);

        fetchData();

        return view;
    }

    private void fetchData() {
        compositeDisposable.add(iPokemonDex.getListPokemon()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Pokedex>() {
                    @Override
                    public void accept(Pokedex pokedex) throws Exception {
                        PokemonListAdapter adapter;
                        adapter = new PokemonListAdapter(getActivity(), pokedex.getPokemon(), new IItemClickListener() {
                            @Override
                            public void onClick(Pokemon pokemon, Integer position) {
                                Fragment detailFragment = PokemonDetail.getInstance();
                                Bundle bundle = new Bundle();
                                Gson gson = new Gson();
                                bundle.putString("pokemon", gson.toJson(pokemon));
                                detailFragment.setArguments(bundle);

                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, detailFragment);
                                fragmentTransaction.addToBackStack("detail");
                                fragmentTransaction.commit();
                            }
                        });

                        pokemon_list_recyclerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                })
        );
    }

}
