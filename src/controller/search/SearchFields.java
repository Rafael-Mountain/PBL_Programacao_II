package controller.search;

public enum SearchFields {

    TUDO("tudo"),
    TITULO("Título"),
    GENERO("Gênero"),
    ANO_LANCAMENTO("Ano de Lançamento"),
    DIRETOR("Diretor"),
    AUTOR("Autor"),
    ISBN("ISBN"),
    DURACAO("Duração"),
    ELENCO("Elenco"),
    ROTEIRO("Roteiro");

    String fieldName;

    SearchFields(String descricao) {
        this.fieldName = descricao;
    }

    public String getFieldName() {
        return fieldName;
    }
}
