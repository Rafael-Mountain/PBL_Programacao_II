package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Filme extends MediaAudioVisual implements IAvaliavel {
    // Atributos
    private String direcao;
    private int duracao; // Duração em minutos
    private String roteiro;
    private TipoMedia tipoMedia = TipoMedia.FILME;
    private List<Avaliacao> avaliacoes;

    // Construtores
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

    // Método de Avaliação
    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
    }

    @Override
    public void Avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public double getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }

    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }

}
