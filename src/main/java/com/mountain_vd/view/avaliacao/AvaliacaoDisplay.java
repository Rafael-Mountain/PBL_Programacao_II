package com.mountain_vd.view.avaliacao;

import com.mountain_vd.controller.util.DateFormater;
import com.mountain_vd.model.Avaliacao;

/**
 * A classe {@code AvaliacaoDisplay} é responsável por exibir no console os detalhes de uma avaliação de forma formatada.
 * Ela usa a classe {@link DateFormater} para formatar as datas da avaliação e de consumo.
 *
 * A principal função desta classe é fornecer uma maneira de mostrar informações sobre uma avaliação,
 * como a data da avaliação, a data em que a mídia foi consumida, a pontuação dada na avaliação, e o comentário.
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 * Avaliacao avaliacao = new Avaliacao(...);
 * AvaliacaoDisplay display = new AvaliacaoDisplay(avaliacao);
 * display.draw();
 * </pre>
 *
 * @author
 */
public class AvaliacaoDisplay {

    /** A avaliação que será exibida. */
    private Avaliacao avaliacao;

    /**
     * Construtor da classe, recebe uma avaliação e prepara para exibi-la.
     *
     * @param avaliacao A avaliação a ser exibida.
     */
    public AvaliacaoDisplay(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * Exibe no console as informações detalhadas da avaliação.
     * A exibição inclui a data da avaliação formatada, a data em que a mídia foi consumida,
     * a pontuação atribuída na avaliação, e o comentário associado.
     */
    public void draw(){
        System.out.println("Data da Avaliação: " + DateFormater.DataHora(avaliacao.getDataAvaliacao()));
        System.out.println("--------------------");
        System.out.println("Dia que assistiu/leu: " + avaliacao.getDataConsumo());
        System.out.println("Avaliação: " + avaliacao.getPontuacao());
        System.out.println("Comentário: " + avaliacao.getReview());
    }
}
