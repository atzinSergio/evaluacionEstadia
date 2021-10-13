package com.example.PokeApiv1.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.PokeApiV1.R;

public class StatsActivity extends AppCompatActivity {
    TextView txtTipo1;
    TextView txtTipo2;
    private static  final String TAG = "ActivityStats";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        String front_default,name,tipo1,tipo2;
        int hp, ataque, defensa, ataqueEspecial, defensaEspecial, velocidad;
        Log.d(TAG, "getIncomingIntent: Check for intents.");
        if(getIntent().hasExtra("front_default" ) && getIntent().hasExtra("name" ) && getIntent().hasExtra("tipo1" ) &&
                getIntent().hasExtra("tipo2" ) && getIntent().hasExtra("hp" ) && getIntent().hasExtra("ataque" ) &&
                getIntent().hasExtra("defensa" ) && getIntent().hasExtra("ataqueEspecial" ) && getIntent().hasExtra("defensaEspecial" ) &&
                getIntent().hasExtra("velocidad")){

            front_default = getIntent().getStringExtra("front_default");
             name = getIntent().getStringExtra("name");
             tipo1 = getIntent().getStringExtra("tipo1");
             tipo2 = getIntent().getStringExtra("tipo2");
              hp = getIntent().getIntExtra("hp",0);
              ataque = getIntent().getIntExtra("ataque",0);
              defensa = getIntent().getIntExtra("defensa",0);
              ataqueEspecial = getIntent().getIntExtra("ataqueEspecial",0);
              defensaEspecial = getIntent().getIntExtra("defensaEspecial",0);
              velocidad = getIntent().getIntExtra("velocidad",0);
              setValues(front_default,name,tipo1,tipo2,hp,ataque,defensa,ataqueEspecial,defensaEspecial,velocidad);
        }


    }
    private void setValues(String front_default, String name, String tipo1, String tipo2, int hp, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad){
        String input;
        String output;
        ImageView imgPokemon = findViewById(R.id.imgPokemon);
        ImageView imgBack = findViewById(R.id.imgRetornar);
        TextView txtNombre = findViewById(R.id.txtNombrePokemon);
        TextView txtTotal = findViewById(R.id.txtTotalStats);
        TextView txtHp = findViewById(R.id.txtHp);
        TextView txtAtaque = findViewById(R.id.txtAtaque);
        TextView txtDefensa = findViewById(R.id.txtDefensa);
        TextView txtAtaqueEspecial = findViewById(R.id.txtAtaqueEspecial);
        TextView txtDefensaEspecial = findViewById(R.id.txtDefensaEspecial);
        TextView txtVelocidad = findViewById(R.id.txtVelocidad);
        txtTipo1 = findViewById(R.id.txtTipoPokemon1);
        txtTipo2 = findViewById(R.id.txtTipoPokemon2);
        ProgressBar barHP = findViewById(R.id.barHp);
        ProgressBar barAtaque = findViewById(R.id.barAtaque);
        ProgressBar barDefensa = findViewById(R.id.barDefensa);
        ProgressBar barAtaqueEspecial = findViewById(R.id.barAtaqueEspecial);
        ProgressBar barDefensaEspecial = findViewById(R.id.barDefensaEspecial);
        ProgressBar barVelocidad = findViewById(R.id.barVelocidad);
        ProgressBar barTotal = findViewById(R.id.barTotalStats);

        Glide.with(this)
                .load(front_default)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgPokemon);

        input = name;
        output = input.substring(0, 1).toUpperCase() + input.substring(1);
        txtNombre.setText(output);




        if(tipo2.equals("")){
            input = tipo1;
            output = input.substring(0, 1).toUpperCase() + input.substring(1);
            txtTipo1.setText(output);
        }else{
            input = tipo1;
            output = input.substring(0, 1).toUpperCase() + input.substring(1);
            txtTipo1.setText(output);

            input = tipo2;
            output = input.substring(0, 1).toUpperCase() + input.substring(1);
            txtTipo2.setText(output);
        }
        int total;
        total = hp + ataque + defensa +ataqueEspecial + defensaEspecial + velocidad;
        barHP.setProgress((int) hp * 100 / 180);
        barAtaque.setProgress((int) ataque * 100 / 180);
        barDefensa.setProgress((int) defensa * 100 / 180);
        barAtaqueEspecial.setProgress((int) ataqueEspecial * 100 / 180);
        barDefensaEspecial.setProgress((int) defensaEspecial * 100 / 180);
        barVelocidad.setProgress((int) velocidad * 100 / 180);
        barTotal.setProgress((int) total * 100 / 1080);

        txtHp.setText(Integer.toString(hp));
        txtAtaque.setText(Integer.toString(ataque));
        txtDefensa.setText(Integer.toString(defensa));
        txtAtaqueEspecial.setText(Integer.toString(ataqueEspecial));
        txtDefensaEspecial.setText(Integer.toString(defensaEspecial));
        txtVelocidad.setText(Integer.toString(velocidad));
        txtTotal.setText(Integer.toString(total));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
