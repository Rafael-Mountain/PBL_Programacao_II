package model;

import model.commons.Identifiable;

public class Genero implements Identifiable {
    private int id;
    private String nome;

    public  Genero(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
