package com.mountain_vd.controller.dataBase.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsula o conteúdo de um repositório para fins de persistência.
 *
 * <p>Esta classe atua como um contêiner de dados, agrupando a lista de itens
 * gerenciados por um repositório e o contador de ID utilizado para gerar
 * identificadores únicos para novos itens. É projetada para ser facilmente
 * serializada e desserializada, por exemplo, para o formato JSON.</p>
 *
 * @param <T> O tipo dos itens contidos na lista.
 */
public class RepositoryContent<T> {
    private List<T> listItems;
    private int idCounter;

    /**
     * Constrói uma nova instância de {@code RepositoryContent} com uma lista de itens
     * e um contador de ID especificados.
     *
     * @param listItems A lista de itens a ser encapsulada.
     * @param idCounter O valor atual do contador de ID para o repositório.
     */
    public RepositoryContent(List<T> listItems, int idCounter) {
        this.listItems = listItems;
        this.idCounter = idCounter;
    }

    /**
     * Constrói uma nova instância de {@code RepositoryContent} vazia.
     * A lista de itens é inicializada como uma {@link ArrayList} vazia e
     * o contador de ID assume o valor padrão para um {@code int} (0).
     * Este construtor é útil para desserialização ou para criar um estado inicial.
     */
    public RepositoryContent() {
        this.listItems = new ArrayList<>();
        // idCounter será inicializado com 0 por padrão para int.
    }

    /**
     * Retorna a lista de itens armazenada neste contêiner.
     *
     * @return A {@link List} de itens.
     */
    public List<T> getListItems() {
        return listItems;
    }

    /**
     * Retorna o valor atual do contador de ID.
     * Este valor geralmente representa o próximo ID a ser atribuído a um novo item
     * no repositório associado.
     *
     * @return O valor do contador de ID.
     */
    public int getIdCounter() {
        return idCounter;
    }

    /**
     * Define o valor do contador de ID.
     *
     * @param idCounter O novo valor para o contador de ID.
     */
    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    /**
     * Define a lista de itens para este contêiner.
     *
     * @param listItems A nova {@link List} de itens.
     */
    public void setListItems(List<T> listItems) {
        this.listItems = listItems;
    }
}