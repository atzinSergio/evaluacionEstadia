package com.example.PokeApiv1;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PokeApiV1.R;
import com.example.PokeApiv1.Modelos.PokeList;
import com.example.PokeApiv1.Modelos.Pokemon;
import com.example.PokeApiv1.Modelos.Type;
import com.example.PokeApiv1.Modelos.Types;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private List<Pokemon> dataset;
    private LayoutInflater mInflater;
    private Context context;

    public ListaPokemonAdapter(List<Pokemon> dataset,Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataset = dataset;
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }

    @Override
    public ListaPokemonAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListaPokemonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListaPokemonAdapter.ViewHolder holder, final int position){
        holder.bindData(dataset.get(position));
    }


    public void setItems(List<Pokemon> items) { dataset = items;}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void adicionarListaPokemon(Pokemon pokemonItem) {
        dataset.add(pokemonItem);
        dataset.sort(Comparator.comparing(Pokemon::getId));
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pkmnImage;
        TextView pkmnName;
        TextView pkmnTipos;
        ViewHolder(View itemView){
            super(itemView);
            pkmnImage = itemView.findViewById(R.id.pokemonImage);
            pkmnName = itemView.findViewById(R.id.pokemonName);
            pkmnTipos = itemView.findViewById(R.id.pkmnTipo);
        }

        void bindData(final Pokemon item){
            /*List<Types>  listaTipos = item.getTypes(); //Un pokémon puede tener 1 o 2 tipos
            ArrayList<Type> tipoPkmn = new ArrayList<>(); //Cada tipo tiene un nombre y una URL
            String cadena = "";
            for (Types tipo: listaTipos) {
                tipoPkmn = tipo.getType(); //Obtener el objeto que tiene el nombre y la URL de cada tipo de la lista, 2 posibles
            }
            for (Type tipo: tipoPkmn) {
                cadena+= tipo.getName() + " " + "\n";
            }*/
            pkmnName.setText(item.getName());
        }
    }




}
