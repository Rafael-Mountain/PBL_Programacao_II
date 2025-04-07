package model;

public enum TipoMedia {
    LIVRO("Livro"),
    FILME("Filme"),
    SERIE("Serie"),
    TEMPORADA("Temporada");

    String descricao;
    TipoMedia(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}



