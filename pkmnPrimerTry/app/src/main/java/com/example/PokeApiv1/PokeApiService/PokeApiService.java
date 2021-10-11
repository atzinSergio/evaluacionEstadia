package com.example.PokeApiv1.PokeApiService;

import com.example.PokeApiv1.Modelos.Consulta;
import com.example.PokeApiv1.Modelos.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiService {
    @GET("pokemon")
    Call<Consulta> getConsulta(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name")String name);

}
