package controller.filter;

public enum GenreFilterType {
    CONTEM_TODOS("Contem todos os generos"),
    CONTEM_UM("Contem pelo menos um genero"),
    NAO_CONTEM_TODOS("Não contem todos os generos"),
    NAO_CONTEM_UM("Não contem pelo menos um genero");

    private String descricao;
    GenreFilterType(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}
