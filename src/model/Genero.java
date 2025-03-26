package model;

public class Genero {
    String nome;

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

}
