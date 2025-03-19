package model;

import java.util.Date;

public class Media implements IAvaliavel{
    private String titulo;
    private Date data_lancamento;
    private Genero genero ;
    private boolean consumido;
    private Avaliacao avaliacao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getGenero() {
        return genero.getDescricao();
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public boolean getConsumido() {
        return consumido;
    }

    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }

    @Override
    public int getPontuacao() {
        return avaliacao.getPontuacao();
    }

    @Override
    public void Avaliar(Date dataAvaliacao, int pontuacao, String review, Date dataConsumo) {

    }
}
