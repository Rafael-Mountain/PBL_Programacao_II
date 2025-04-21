package view;

import model.Avaliacao;

public class AvaliacaoDisplay {
    Avaliacao avaliacao;

    public AvaliacaoDisplay(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void draw(){
        System.out.println("======================================");
        System.out.println("Data da Avaliação " + avaliacao.getDataAvaliacao());
        System.out.println("--------------------");
        System.out.println("Dia do consumo: " + avaliacao.getDataConsumo());
        System.out.println("Avaliação: " + avaliacao.getPontuacao());
        System.out.println("Comentário: " + avaliacao.getReview());
        System.out.println("======================================");
    }
}

