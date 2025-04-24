package model.commons;

import model.TipoMedia;

/**
 * Interface que define um contrato para objetos que possuem avaliação.
 *
 * Objetos que implementam esta interface devem ser identificáveis (via {@link Identifiable})
 * e devem fornecer uma pontuação e o tipo de média utilizada na avaliação.
 *
 */
public interface ITemAvaliacao extends Identifiable {

    /**
     * Retorna a pontuação associada ao objeto.
     *
     * @return um valor decimal representando a pontuação.
     */
    double getPontuacao();

    /**
     * Retorna o tipo de média utilizada para calcular a pontuação do objeto.
     *
     * @return um valor do enum {@link TipoMedia} representando o tipo de média.
     */
    TipoMedia getTipoMedia();
}
