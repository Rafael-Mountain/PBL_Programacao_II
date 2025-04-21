package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Avaliacao {
    private LocalDateTime dataAvaliacao;
    private int pontuacao;
    private String review;
    private LocalDate dataConsumo;


    public Avaliacao(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDate dataConsumo) {
        this.dataAvaliacao = dataAvaliacao;
        this.pontuacao = pontuacao;
        this.review = review;
        this.dataConsumo = dataConsumo;
    }

    // Getters e Setters
    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
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

    public LocalDate getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(LocalDate dataConsumo) {
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
