package com.example.PokeApiv1.PokeApiService;

import com.example.PokeApiv1.Modelos.Consulta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {
    @GET("pokemon")
    Call<Consulta> getConsulta();
}
