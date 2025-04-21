package view.avaliacao;

import controller.util.DateFormater;
import model.Avaliacao;

public class AvaliacaoDisplay {
    Avaliacao avaliacao;

    public AvaliacaoDisplay(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void draw(){
        System.out.println("Data da Avaliação " + DateFormater.DataHora(avaliacao.getDataAvaliacao()));
        System.out.println("--------------------");
        System.out.println("Dia que assistiu/leu: " + avaliacao.getDataConsumo());
        System.out.println("Avaliação: " + avaliacao.getPontuacao());
        System.out.println("Comentário: " + avaliacao.getReview());
    }
}

