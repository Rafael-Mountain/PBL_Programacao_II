package com.mountain_vd.controller.search;

/**
 * Enumeração que define os campos disponíveis para busca em repositórios de mídia.
 * Cada constante representa um critério de pesquisa e possui um nome legível
 * que pode ser apresentado em interfaces ou logs.
 *
 * @see SearchFactory
 * @see Search
 * @see SearchResults
 */
public enum SearchFields {

    /** Retorna todos os itens sem aplicar filtro específico. */
    TUDO("tudo"),

    /** Busca pelo título da mídia. */
    TITULO("Título"),

    /** Busca pelo(s) gênero(s) associado(s) à mídia. */
    GENERO("Gênero"),

    /** Busca pelo ano de lançamento da mídia. */
    ANO_LANCAMENTO("Ano de Lançamento"),

    /** Busca pelo nome do diretor (aplicável a mídias audiovisuais). */
    DIRETOR("Diretor"),

    /** Busca pelo nome do autor (aplicável a livros). */
    AUTOR("Autor"),

    /** Busca pelo código ISBN (aplicável a livros). */
    ISBN("ISBN"),

    /** Busca pela duração da mídia em minutos. */
    DURACAO("Duração"),

    /** Busca por membros do elenco (aplicável a mídias audiovisuais). */
    ELENCO("Elenco"),

    /** Busca pela descrição do roteiro ou sinopse. */
    ROTEIRO("Roteiro");

    /** Nome legível para apresentação do campo de busca. */
    private final String fieldName;

    /**
     * Construtor da enumeração.
     *
     * @param descricao Nome legível do campo de busca.
     */
    SearchFields(String descricao) {
        this.fieldName = descricao;
    }

    /**
     * Recupera o nome legível associado ao campo de busca.
     *
     * @return Descrição ou rótulo do campo de busca.
     */
    public String getFieldName() {
        return fieldName;
    }
}
