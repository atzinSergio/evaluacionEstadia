package com.example.PokeApiv1.Adaptador;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.PokeApiV1.R;
import com.example.PokeApiv1.Modelos.Pokemon;
import com.example.PokeApiv1.Modelos.Type;
import com.example.PokeApiv1.Modelos.Types;

import java.util.Comparator;
import java.util.List;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private List<Pokemon> dataset;
    private LayoutInflater mInflater;
    private Context context;
    private onPokemonListener mOnPokemonListener;

    public ListaPokemonAdapter(List<Pokemon> dataset,Context context, onPokemonListener onPokemonListener){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataset = dataset;
        this.mOnPokemonListener =  onPokemonListener;
    }

    @Override
    public int getItemCount(){
        return dataset.size();
    }

    @Override
    public ListaPokemonAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListaPokemonAdapter.ViewHolder(view,mOnPokemonListener);
    }

    @Override
    public void onBindViewHolder(final ListaPokemonAdapter.ViewHolder holder, final int position){
        holder.bindData(dataset.get(position));
        Glide.with(context)
                .load(dataset.get(position).getSprites().getFront_default())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pkmnImage);

    }


    public void setItems(List<Pokemon> items) { dataset = items;}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void adicionarListaPokemon(Pokemon pokemonItem) {
        dataset.add(pokemonItem);
        dataset.sort(Comparator.comparing(Pokemon::getId));
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView pkmnImage;
        TextView pkmnName;
        LinearLayout pkmnTipo1;
        RelativeLayout pkmnTipo2;
        onPokemonListener onPokemonListener;
        ViewHolder(View itemView, onPokemonListener onPokemonListener){
            super(itemView);
            pkmnImage = itemView.findViewById(R.id.pokemonImage);
            pkmnName = itemView.findViewById(R.id.pokemonName);
            pkmnTipo1 = itemView.findViewById(R.id.layoutPrimerTipo);
            pkmnTipo2 = itemView.findViewById(R.id.layoutSegundoTipo);
            itemView.setOnClickListener(this);
            this.onPokemonListener = onPokemonListener;
        }
        void tipoFondo1(String name){
            Drawable t1;
            switch (name){
                case "normal":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.normal);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "fighting":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.fighting);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "flying":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.flying);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "poison":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.poison);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "ground":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ground);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "rock":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.rock);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "bug":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.bug);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "ghost":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ghost);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "steel":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.steel);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "fire":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.fire);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "water":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.water);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "grass":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.grass);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "electric":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.electric);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "psychic":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.psychic);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "ice":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ice);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "dragon":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.dragon);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "dark":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.dark);
                    pkmnTipo1.setBackground(t1);
                    break;
                case "fairy":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.fairy);
                    pkmnTipo1.setBackground(t1);
                    break;
            }
        }
        void tipoFondo2(String name){
            Drawable t1;
            switch (name){
                case "normal":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.snormal);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "fighting":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sfighting);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "flying":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sflying);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "poison":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.spoison);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "ground":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sground);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "rock":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.srock);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "bug":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sbug);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "ghost":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sghost);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "steel":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ssteel);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "fire":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sfire);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "water":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.swater);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "grass":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sgrass);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "electric":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.selectric);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "psychic":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.spsychic);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "ice":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sice);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "dragon":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sdragon);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "dark":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sdark);
                    pkmnTipo2.setBackground(t1);
                    break;
                case "fairy":
                    t1 = ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.sfairy);
                    pkmnTipo2.setBackground(t1);
                    break;
            }
        }

        void bindData(final Pokemon item){
            List<Types> tiposPokemon = item.getTypes();
            Type tipoPokemon;
            pkmnTipo1.setBackground(null);
            pkmnTipo2.setBackground(null);
            if(tiposPokemon.size() == 1){
                tipoFondo1(tiposPokemon.get(0).getType().getName());
            }else{
                tipoFondo1(tiposPokemon.get(0).getType().getName());
                tipoFondo2(tiposPokemon.get(1).getType().getName());
            }


            /*List<Types>  listaTipos = item.getTypes(); //Un pokémon puede tener 1 o 2 tipos
            ArrayList<Type> tipoPkmn = new ArrayList<>(); //Cada tipo tiene un nombre y una URL
            String cadena = "";
            for (Types tipo: listaTipos) {
                tipoPkmn = tipo.getType(); //Obtener el objeto que tiene el nombre y la URL de cada tipo de la lista, 2 posibles
            }
            for (Type tipo: tipoPkmn) {
                cadena+= tipo.getName() + " " + "\n";
            }*/
            String input= item.getName();
            String output = input.substring(0, 1).toUpperCase() + input.substring(1);
            pkmnName.setText(output);
        }

        @Override
        public void onClick(View v) {
            onPokemonListener.onPokemonClick(getAdapterPosition());
        }
    }
    public interface onPokemonListener{
        void onPokemonClick(int position);
    }




}
