package com.example.PokeApiv1.Modelos;

import java.util.HashMap;
import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    private List<Types> types;
    private Sprites sprites;
    private List<Stats> stats;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Types> getTypes() {
        return types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Stats> getStats() {
        return stats;
    }
}
