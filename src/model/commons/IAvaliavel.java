package model.commons;

import model.Avaliacao;
import java.util.List;

/**
 * Interface que define o comportamento de uma entidade que pode ser avaliada.
 */
public interface IAvaliavel extends ITemAvaliacao {

    /**
     * Adiciona uma avaliação à entidade.
     *
     * @param avaliacao A avaliação a ser adicionada.
     */
    void avaliar(Avaliacao avaliacao);

    /**
     * Retorna a lista de avaliações da entidade.
     *
     * @return Lista de avaliações.
     */
    List<Avaliacao> getAvaliacoes();
}
