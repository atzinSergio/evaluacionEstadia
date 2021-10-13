package com.example.PokeApiv1.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PokeApiV1.R;
import com.example.PokeApiv1.Adaptador.ListaPokemonAdapter;
import com.example.PokeApiv1.Modelos.Consulta;
import com.example.PokeApiv1.Modelos.PokeList;
import com.example.PokeApiv1.Modelos.Pokemon;
import com.example.PokeApiv1.Modelos.Stats;
import com.example.PokeApiv1.Modelos.Types;
import com.example.PokeApiv1.PokeApiService.PokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ListaPokemonAdapter.onPokemonListener {
    private static final String TAG = "Listener";
    private List<PokeList> listaResultados = new ArrayList<>();
    private List<Pokemon> listaPokemon = new ArrayList<>(20);
    private List<Pokemon> lstPokemon = new ArrayList<>();
    private int offset = 0;
    private TextView txtTitulo;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private Retrofit retrofit;
    private boolean cargaLista;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.listRecyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(listaPokemon,this,this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if(cargaLista){
                        if((visibleItemCount + pastVisibleItems) >= totalItemCount){
                            cargaLista = false;
                            offset+=20;
                            getPokemonList(offset);
                        }
                    }
                }
            }
        });

         retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         cargaLista = true;
         offset = 0;
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
                lstPokemon.add(pokemonItem);
            }

        });
    }

    private void getPokemonList(int offset){
        PokeApiService PokeApiServiceObj = retrofit.create(PokeApiService.class);
        Call<Consulta> call = PokeApiServiceObj.getConsulta(20,offset);
        call.enqueue(new Callback<Consulta>() {
            @Override
            public void onFailure(Call<Consulta> call, Throwable t) {
                cargaLista = true;
                //jsonText.setText(t.getMessage());
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                cargaLista = true;
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

    @Override
    public void onPokemonClick(int position) {
        position =  position + 1;

        List<Types> tiposPokemon = getBuscado(position).getTypes();
        List<Stats> statsPokemon = getBuscado(position).getStats();
        Log.d(TAG, "onPokemonClick: clocked." + position);
        Intent intent = new Intent(this,StatsActivity.class);
        intent.putExtra("front_default",getBuscado(position).getSprites().getFront_default());
        intent.putExtra("name",getBuscado(position).getName());
        if(tiposPokemon.size() == 1){
            intent.putExtra("tipo1",tiposPokemon.get(0).getType().getName());
            intent.putExtra("tipo2","");
        }else{
            intent.putExtra("tipo1",tiposPokemon.get(0).getType().getName());
            intent.putExtra("tipo2",tiposPokemon.get(1).getType().getName());
        }
        intent.putExtra("hp", statsPokemon.get(0).getBase_stat());
        intent.putExtra("ataque", statsPokemon.get(1).getBase_stat());
        intent.putExtra("defensa", statsPokemon.get(2).getBase_stat());
        intent.putExtra("ataqueEspecial", statsPokemon.get(3).getBase_stat());
        intent.putExtra("defensaEspecial", statsPokemon.get(4).getBase_stat());
        intent.putExtra("velocidad", statsPokemon.get(5).getBase_stat());
        startActivity(intent);
    }
    public Pokemon getBuscado(int id){
        for (Pokemon item: lstPokemon) {
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }
}