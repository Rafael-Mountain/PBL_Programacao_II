package model;

public enum TipoMedia {
    LIVRO("livro"),
    FILME("Filme"),
    SERIE("livro");

    String descricao;
    TipoMedia(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}



