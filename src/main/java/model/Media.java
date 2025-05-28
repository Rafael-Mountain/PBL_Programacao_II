package model;

import model.commons.ITemAvaliacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa uma mídia genérica (ex: livro, filme, série).
 * Implementa a interface {@link ITemAvaliacao} e pode ser estendida por mídias avaliáveis.
 *
 * Armazena informações comuns a todas as mídias como título, data de lançamento, gêneros e estado de consumo.
 *
 */
public abstract class Media implements ITemAvaliacao {

    /** Identificador único da mídia. */
    private int id = -1;

    /** Título da mídia. */
    private String titulo;

    /** Data de lançamento da mídia. */
    private LocalDateTime dataLancamento;

    /** Lista de gêneros associados à mídia. */
    private List<Genero> generos;

    /** Indica se a mídia já foi consumida (ex: lida, assistida). */
    private boolean consumido;

    /** Tipo da mídia (ex: LIVRO, FILME). */
    private TipoMedia tipoMedia;

    /**
     * Construtor padrão que inicializa a lista de gêneros vazia
     * e define a mídia como não consumida.
     */
    public Media() {
        this.generos = new ArrayList<>();
        this.consumido = false;
    }

    /**
     * Construtor com parâmetros para inicialização completa da mídia.
     *
     * @param titulo Título da mídia.
     * @param dataLancamento Data de lançamento.
     * @param consumido Indica se a mídia já foi consumida.
     * @param generos Lista de gêneros associados à mídia.
     */
    public Media(String titulo, LocalDateTime dataLancamento, boolean consumido, List<Genero> generos) {
        this();
        if (generos != null) {
            for (Genero genero : generos) {
                addGenero(genero);
            }
        }
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.consumido = consumido;
    }

    /**
     * Retorna o ID da mídia.
     *
     * @return ID da mídia.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Define o ID da mídia.
     *
     * @param id novo ID da mídia.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o título da mídia.
     *
     * @return título da mídia.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define o título da mídia.
     *
     * @param titulo novo título.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Retorna a data de lançamento da mídia.
     *
     * @return data de lançamento.
     */
    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    /**
     * Define a data de lançamento da mídia.
     *
     * @param dataLancamento nova data de lançamento.
     */
    public void setDataLancamento(LocalDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    /**
     * Retorna uma cópia da lista de gêneros da mídia.
     *
     * @return lista de gêneros.
     */
    public List<Genero> getGeneros() {
        return new ArrayList<>(generos);
    }

    /**
     * Adiciona um gênero à lista de gêneros da mídia.
     *
     * @param genero gênero a ser adicionado.
     */
    public void addGenero(Genero genero) {
        if (genero != null) {
            generos.add(genero);
        }
    }

    /**
     * Define a lista de gêneros da mídia.
     *
     * @param generos nova lista de gêneros.
     */
    public void setGeneros(List<Genero> generos) {
        if (generos != null) {
            this.generos = new ArrayList<>(generos);
        }
    }

    /**
     * Retorna se a mídia foi consumida.
     *
     * @return true se consumida, false caso contrário.
     */
    public boolean isConsumido() {
        return consumido;
    }

    /**
     * Define se a mídia foi consumida.
     *
     * @param consumido true para consumida, false caso contrário.
     */
    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }
}
