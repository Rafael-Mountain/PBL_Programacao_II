package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Filme extends MediaAudioVisual implements IAvaliavel {
    private String direcao;
    private int duracao; // Duração em minutos
    private String roteiro;
    private TipoMedia tipoMedia = TipoMedia.FILME;
    private List<Avaliacao> avaliacoes;


    public Filme() {
        super();
        this.avaliacoes = new ArrayList<>();
    }


    // Construtor completo
    public Filme(String titulo, LocalDateTime dataLancamento, boolean consumido, List<Genero> generos, String tituloOriginal,
                 String localDisponivel, List<String> elenco, String direcao, int duracao, String roteiro) {

        super(titulo, dataLancamento, consumido, generos, tituloOriginal, localDisponivel, elenco);
        this.avaliacoes = new ArrayList<>();
        this.direcao = direcao;
        this.duracao = duracao; // Garante que a duração seja positiva
        this.roteiro = roteiro;
    }

    // Getters e Setters
    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getRoteiro() {
        return roteiro;
    }

    public void setRoteiro(String roteiro) {
        this.roteiro = roteiro;
    }

    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
    }

    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }

    //Todo: Copiar a avaliação
    @Override
    public Filme clone() {
        Filme clone = new Filme();
        clone.setId(this.getId());
        clone.setTitulo(this.getTitulo());
        clone.setDataLancamento(this.getDataLancamento());
        clone.setConsumido(this.isConsumido());
        clone.setGeneros(this.getGeneros());
        clone.setTituloOriginal(this.getTituloOriginal());
        clone.setLocalDisponivel(this.getLocalDisponivel());
        clone.setElenco(this.getElenco());
        clone.setDirecao(this.direcao);
        clone.setDuracao(this.duracao);
        clone.setRoteiro(this.roteiro);
        return clone;
    }

    @Override
    public int getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }

    @Override
    public void Avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return "Filme{\n" +
                "titulo='" + getTitulo() + "'\n" +
                ", id='" + ((getId() == -1) ? "N/A" : getId()) + "'\n" +
                ", tituloOriginal='" + getTituloOriginal() + "'\n" +
                ", dataLancamento=" + (getDataLancamento() != null ? getDataLancamento().format(formatter) : "N/A") + "\n" +
                ", consumido=" + (isConsumido() ? "Sim" : "Não") + "\n" +
                ", generos=" + (getGeneros() != null ? getGeneros() : "N/A") + "\n" +
                ", localDisponivel='" + (getLocalDisponivel() != null ? getLocalDisponivel() : "N/A") + "'\n" +
                ", elenco=" + (getElenco() != null && !getElenco().isEmpty() ? getElenco() : "Sem elenco") + "\n" +
                ", direcao='" + (direcao != null ? direcao : "N/A") + "'\n" +
                ", roteiro='" + (roteiro != null ? roteiro : "N/A") + "'\n" +
                ", duracao=" + duracao + " min\n" +
                '}';
    }

}
