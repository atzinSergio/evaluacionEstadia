package com.example.PokeApiv1.Modelos;

import java.util.List;

public class Consulta {
    private String next;
    private List<PokeList> results;

    public String getNext() {
        return next;
    }

    public List<PokeList> getResults() {
        return results;
    }
}
