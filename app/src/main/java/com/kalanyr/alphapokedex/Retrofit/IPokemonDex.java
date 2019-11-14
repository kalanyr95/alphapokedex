package com.kalanyr.alphapokedex.Retrofit;



import com.kalanyr.alphapokedex.Model.Pokedex;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IPokemonDex {

    @GET("pokedex.json")
    Observable<Pokedex> getListPokemon();
}
