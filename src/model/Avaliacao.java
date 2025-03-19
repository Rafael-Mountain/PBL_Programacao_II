package model;

import java.util.Date;

public class Avaliacao {
    private Date dataAvaliacao;
    private int pontuacao;
    private String review;
    private Date dataConsumo;


    public Avaliacao(Date dataAvaliacao, int pontuacao, String review, Date dataConsumo) {
        this.dataAvaliacao = dataAvaliacao;
        this.pontuacao = pontuacao;
        this.review = review;
        this.dataConsumo = dataConsumo;
    }

    // Getters e Setters
    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }


    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(Date dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    // Método toString para exibição dos dados
    @Override
    public String toString() {
        return "Avaliacao{" +
                "dataAvaliacao=" + dataAvaliacao +
                ", pontuacao=" + pontuacao +
                ", review='" + review + '\'' +
                ", dataConsumo=" + dataConsumo +
                '}';
    }
}
