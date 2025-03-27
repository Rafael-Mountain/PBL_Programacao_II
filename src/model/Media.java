package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Media implements IAvaliavel, Identifiable {
    private int id = -1;
    private String titulo;
    private LocalDateTime dataLancamento;
    private List<Genero> generos;
    private boolean consumido;
    private TipoMedia tipoMedia;

    // Construtor padrão
    public Media() {
        this.generos = new ArrayList<>();
        this.consumido = false;
    }

    // Construtor com parâmetros
    public Media(String titulo, LocalDateTime dataLancamento, boolean consumido,List<Genero> generos) {
        this();
        if (generos != null) {
            for (Genero genero : generos) {
                addGenero(genero);
            }
        }
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.consumido = consumido;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public List<Genero> getGeneros() {
        return new ArrayList<>(generos); // Retorna uma cópia para evitar modificação externa
    }

    public void addGenero(Genero genero) {
        if (genero != null) {
            generos.add(genero);
        }
    }
    public void setGeneros(List<Genero> generos) {
        if (generos != null) {
            this.generos = new ArrayList<>(generos);
        }
    }

    public boolean isConsumido() { // Melhoria: "is" para boolean
        return consumido;
    }

    public void setConsumido(boolean consumido) {
        this.consumido = consumido;
    }

    public TipoMedia getTipoMedia() {
        return tipoMedia;
    }

    @Override
    public int getPontuacao() {
        return 0;
    }

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }
}
