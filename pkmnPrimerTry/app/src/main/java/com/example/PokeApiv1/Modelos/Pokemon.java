package com.example.PokeApiv1.Modelos;

import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    private List<Type> types;
    private Sprites front_default;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public Sprites getFront_default() {
        return front_default;
    }

}
