package controller.filter;

public enum GenreFilterType {
    CONTEM("Contem  os generos"),
    NAO_CONTEM("Não contem os generos");

    private String descricao;
    GenreFilterType(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}
