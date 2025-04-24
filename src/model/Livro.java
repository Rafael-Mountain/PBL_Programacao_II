package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa um livro como tipo de mídia que pode ser avaliado.
 * Estende a classe {@link Media} e implementa a interface {@link IAvaliavel}.
 *
 * Cada livro possui informações como autor, editora, ISBN e se está ou não na posse do usuário.
 *
 * A pontuação atribuída ao livro corresponde à avaliação mais recente.
 *
 */
public class Livro extends Media implements IAvaliavel {

    /** Nome do autor do livro. */
    private String autor;

    /** Nome da editora responsável pela publicação. */
    private String editora;

    /** Indica se o usuário possui fisicamente ou digitalmente o livro. */
    private boolean possui;

    /** Código ISBN do livro. */
    private String isbn;

    /** Tipo de mídia, fixado como LIVRO. */
    private TipoMedia tipoMedia = TipoMedia.LIVRO;

    /** Lista de avaliações feitas ao livro. */
    private List<Avaliacao> avaliacoes;

    /**
     * Construtor padrão.
     * Inicializa a lista de avaliações como vazia.
     */
    public Livro() {
        super();
        this.avaliacoes = new ArrayList<>();
    }

    /**
     * Construtor completo que inicializa todos os atributos do livro.
     *
     * @param titulo Título do livro.
     * @param dataLancamento Data de lançamento.
     * @param consumido Indica se o livro foi lido.
     * @param generosLivro Lista de gêneros associados.
     * @param autor Nome do autor.
     * @param editora Nome da editora.
     * @param possui Indica se o livro está em posse do usuário.
     * @param isbn Código ISBN do livro.
     */
    public Livro(String titulo, LocalDateTime dataLancamento, boolean consumido, List<Genero> generosLivro,
                 String autor, String editora, boolean possui, String isbn) {
        super(titulo, dataLancamento, consumido, generosLivro);
        this.autor = autor;
        this.editora = editora;
        this.possui = possui;
        this.isbn = isbn;
        this.avaliacoes = new ArrayList<>();
    }

    /**
     * Retorna o nome do autor do livro.
     *
     * @return nome do autor.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Define o nome do autor do livro.
     *
     * @param autor nome do autor.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Retorna o nome da editora.
     *
     * @return nome da editora.
     */
    public String getEditora() {
        return editora;
    }

    /**
     * Define o nome da editora.
     *
     * @param editora nome da editora.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }

    /**
     * Indica se o livro está em posse do usuário.
     *
     * @return true se estiver em posse, false caso contrário.
     */
    public boolean isPossui() {
        return possui;
    }

    /**
     * Define se o livro está em posse do usuário.
     *
     * @param possui true se estiver em posse, false caso contrário.
     */
    public void setPossui(boolean possui) {
        this.possui = possui;
    }

    /**
     * Retorna o código ISBN do livro.
     *
     * @return código ISBN.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Define o código ISBN do livro.
     *
     * @param isbn código ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Retorna o tipo da mídia, que neste caso é {@link TipoMedia#LIVRO}.
     *
     * @return tipo da mídia.
     */
    @Override
    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
    }

    /**
     * Retorna a lista de avaliações feitas ao livro, ordenada da mais recente para a mais antiga.
     *
     * @return lista de avaliações.
     */
    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Retorna a pontuação da avaliação mais recente feita ao livro.
     *
     * @return pontuação mais recente ou 0 caso não haja avaliações.
     */
    @Override
    public double getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }

    /**
     * Adiciona uma nova avaliação ao livro.
     *
     * @param avaliacao avaliação a ser adicionada.
     */
    @Override
    public void avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }
}
