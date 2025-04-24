package model;

/**
 * Enum que representa os tipos de mídia disponíveis no sistema, com uma descrição associada a cada tipo.
 * Os tipos de mídia incluem: Livro, Filme, Série e Temporada.
 *
 * Cada tipo de mídia tem uma descrição associada, que pode ser utilizada para representar o tipo de forma legível.
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 * TipoMedia tipo = TipoMedia.LIVRO;
 * System.out.println(tipo.getDescricao()); // Saída: "Livro"
 * </pre>
 *
 * @author
 */
public enum TipoMedia {

    /** Tipo de mídia: Livro. */
    LIVRO("Livro"),

    /** Tipo de mídia: Filme. */
    FILME("Filme"),

    /** Tipo de mídia: Série. */
    SERIE("Serie"),

    /** Tipo de mídia: Temporada. */
    TEMPORADA("Temporada");

    /** Descrição associada ao tipo de mídia. */
    private String descricao;

    /**
     * Construtor do enum que inicializa a descrição de cada tipo de mídia.
     *
     * @param descricao Descrição do tipo de mídia.
     */
    TipoMedia(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição do tipo de mídia.
     *
     * @return Descrição do tipo de mídia.
     */
    public String getDescricao() {
        return descricao;
    }
}
