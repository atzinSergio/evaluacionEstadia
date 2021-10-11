package com.example.PokeApiv1.Modelos;

public class Pokemon {
    private int id;
    private String name;
    private Type types;
    private Sprites front_default;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Type getTypes() {
        return types;
    }

    public Sprites getFront_default() {
        return front_default;
    }

}
