package com.example.PokeApiv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PokeApiV1.R;
import com.example.PokeApiv1.Modelos.Consulta;
import com.example.PokeApiv1.Modelos.Results;
import com.example.PokeApiv1.PokeApiService.PokeApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView jsonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonText = findViewById(R.id.jsonText);
        getPokemonList();
    }

    private void getPokemonList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService PokeApiServiceObj = retrofit.create(PokeApiService.class);
        Call<Consulta> call = PokeApiServiceObj.getConsulta();
        call.enqueue(new Callback<Consulta>() {
            @Override
            public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                if(!response.isSuccessful()){
                    return;
                }
                Consulta resConsulta = response.body();
                String cadena = "";
                cadena = "next: " + resConsulta.getNext() + "\n";
                List<Results> listaResultados = resConsulta.getResults();
                for (Results resultado: listaResultados) {
                    cadena += "url: "+ resultado.getUrl() + "\n";
                }
                jsonText.append(cadena);


            }

            @Override
            public void onFailure(Call<Consulta> call, Throwable t) {
                jsonText.setText(t.getMessage());
            }
        });
    }
}