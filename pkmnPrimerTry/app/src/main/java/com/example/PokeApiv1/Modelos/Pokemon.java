package com.example.PokeApiv1.Modelos;

import java.util.HashMap;
import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    //private List<Types> types;
    //private HashMap<String,String> sprites;
    private List<Stats> stats;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*public List<Types> getTypes() {
        return types;
    }*/

    /*public HashMap<String,String> getSprites() {
        return sprites;
    }
    */

    public List<Stats> getStats() {
        return stats;
    }
}
