package controller.filter;

public enum YearFilterType {
    MAIOR("Data maior que"),
    MENOR("Data menor que"),
    IGUAL("Data Igual a"),
    DIFERENTE("Data Diferente de");

    private String descricao;
    YearFilterType(String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }
}
