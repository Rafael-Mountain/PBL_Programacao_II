package controller.search;

import model.Media;
import java.util.List;

/**
 * Representa os resultados de uma operação de busca.
 * <p>
 * Contém a lista de mídias encontradas e uma mensagem de status ou descrição
 * sobre o resultado da busca.
 * </p>
 *
 * @see Media
 */
public class SearchResults {

    /**
     * Lista de mídias que satisfazem o critério de busca.
     */
    private List<Media> mediaList;

    /**
     * Mensagem de status ou descrição associada aos resultados da busca.
     */
    private String message;

    /**
     * Constrói um novo objeto {@code SearchResults} com a lista de mídias e a mensagem fornecidas.
     *
     * @param mediaList lista de mídias retornadas pela busca
     * @param message   mensagem de status ou descrição dos resultados
     */
    public SearchResults(List<Media> mediaList, String message) {
        this.mediaList = mediaList;
        this.message = message;
    }

    /**
     * Retorna a lista de mídias encontradas na busca.
     *
     * @return lista de {@link Media}
     */
    public List<Media> getMediaList() {
        return mediaList;
    }

    /**
     * Substitui a lista de mídias pelos novos resultados.
     *
     * @param mediaList nova lista de mídias
     */
    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    /**
     * Retorna a mensagem de status ou descrição dos resultados da busca.
     *
     * @return mensagem associada à busca
     */
    public String getMessage() {
        return message;
    }

    /**
     * Atualiza a mensagem de status ou descrição dos resultados da busca.
     *
     * @param message nova mensagem a ser associada
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Verifica se não há mídias na lista de resultados.
     *
     * @return {@code true} se a lista de mídias estiver vazia, {@code false} caso contrário
     */
    public boolean isEmpty() {
        return mediaList == null || mediaList.isEmpty();
    }
}
