package com.example.PokeApiv1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PokeApiV1.R;
import com.example.PokeApiv1.Modelos.Consulta;
import com.example.PokeApiv1.Modelos.PokeList;
import com.example.PokeApiv1.Modelos.Pokemon;
import com.example.PokeApiv1.PokeApiService.PokeApiService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<PokeList> listaResultados = new ArrayList<>();
    private List<Pokemon> listaPokemon = new ArrayList<>(20);
    private int offset = 0;
    private TextView txtTitulo;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private Retrofit retrofit;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(listaPokemon,this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);

         retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getPokemonList(offset);


    }


    private void getPokemon(String name){

        PokeApiService PokeApiServiceObj = retrofit.create(PokeApiService.class);
        Call<Pokemon> call = PokeApiServiceObj.getPokemon(name);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                String mensaje = t.getMessage();
                Toast toast = Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG);
                toast.show();
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Pokemon pokemonItem = response.body();
                listaPokemonAdapter.adicionarListaPokemon(pokemonItem);
            }

        });
    }

    private void getPokemonList(int offset){
        PokeApiService PokeApiServiceObj = retrofit.create(PokeApiService.class);
        Call<Consulta> call = PokeApiServiceObj.getConsulta(35,offset);
        call.enqueue(new Callback<Consulta>() {
            @Override
            public void onFailure(Call<Consulta> call, Throwable t) {
                //jsonText.setText(t.getMessage());
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                if(!response.isSuccessful()){
                    return;
                }

                Consulta consulta = response.body();
                 listaResultados =  consulta.getResults();
                for(PokeList item: listaResultados) {
                    getPokemon(item.getName());
                }
            }



        });

    }
}