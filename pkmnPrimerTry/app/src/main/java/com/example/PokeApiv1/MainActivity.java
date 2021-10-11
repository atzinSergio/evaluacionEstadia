package com.example.PokeApiv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.PokeApiV1.R;
import com.example.PokeApiv1.Modelos.Consulta;
import com.example.PokeApiv1.Modelos.PokeList;
import com.example.PokeApiv1.Modelos.Pokemon;
import com.example.PokeApiv1.PokeApiService.PokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private int offset = 0;
    private TextView jsonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonText = findViewById(R.id.jsonText);
        getPokemonList(offset);
    }

    private void getPkmn(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeApiService PokeApiServiceObj = retrofit.create(PokeApiService.class);
        Call<Pokemon> call = PokeApiServiceObj.getPokemon(name);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                jsonText.setText(t.getMessage());
            }
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Pokemon pokemonItem = response.body();
                String cadena="";
                cadena += pokemonItem.getName() + " " + pokemonItem.getId() + "\n";
                jsonText.setText(cadena);
            }

        });
    }

    private void getPokemonList(int offset){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService PokeApiServiceObj = retrofit.create(PokeApiService.class);
        Call<Consulta> call = PokeApiServiceObj.getConsulta(offset,51);
        call.enqueue(new Callback<Consulta>() {
            @Override
            public void onFailure(Call<Consulta> call, Throwable t) {
                jsonText.setText(t.getMessage());
            }
            @Override
            public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                if(!response.isSuccessful()){
                    return;
                }

                Consulta consulta = response.body();
                String cadena = "", url = "", name = "";
                List<PokeList> listaPkmn =  consulta.getResults();
                for (PokeList item : listaPkmn) {
                    getPkmn(item.getName());
                }

            }


        });
    }
}