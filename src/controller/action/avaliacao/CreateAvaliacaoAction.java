package controller.action.avaliacao;

import controller.action.ActionResult;
import controller.action.BaseSetAction;
import controller.action.Validation;
import controller.dataBase.FilmeRepository;
import controller.dataBase.LivroRepository;
import controller.dataBase.SerieRepository;
import model.*;
import model.commons.IAvaliavel;

import java.time.LocalDateTime;

/**
 * Classe responsável por realizar a ação de criação de uma {@link Avaliacao}
 * associada a um objeto que implementa a interface {@link IAvaliavel}.
 * Essa classe herda de {@link BaseSetAction}, permitindo configurar
 * tanto o modelo {@link Avaliacao} quanto o modelo superior (ex: Filme, Livro ou Temporada).
 *
 * <p>Durante a execução da ação, a classe valida o modelo de avaliação,
 * define a data da avaliação como a data/hora atual e atualiza o repositório
 * correspondente com a nova avaliação associada.</p>
 *
 * <p>Suporta os seguintes tipos de mídia:
 * <ul>
 *     <li>{@link TipoMedia#FILME}</li>
 *     <li>{@link TipoMedia#LIVRO}</li>
 *     <li>{@link TipoMedia#TEMPORADA}</li>
 * </ul>
 * </p>
 *
 * @author [Seu Nome]
 */
public class CreateAvaliacaoAction extends BaseSetAction<Avaliacao, IAvaliavel> {

    /**
     * Construtor que recebe uma validação específica para o modelo de avaliação.
     *
     * @param validation Objeto responsável por validar a {@link Avaliacao} antes da execução da ação.
     */
    public CreateAvaliacaoAction(Validation<Avaliacao> validation) {
        super(validation);
    }

    /**
     * Executa o processo de criação de uma avaliação.
     * Valida o modelo, define a data atual como data da avaliação e persiste a avaliação
     * no repositório correspondente ao tipo de mídia ({@link Filme}, {@link Livro} ou {@link Temporada}).
     *
     * @param model Objeto {@link Avaliacao} contendo os dados da avaliação a ser criada.
     * @return {@link ActionResult} representando o sucesso ou falha da operação, juntamente com uma mensagem.
     */
    @Override
    public ActionResult execute(Avaliacao model) {
        // Verifica se o modelo é válido
        if (!isValid(model)) {
            return new ActionResult(false, getErrorMessage());
        }

        // Define a data da avaliação como a data/hora atual
        LocalDateTime dataAtual = LocalDateTime.now();
        model.setDataAvaliacao(dataAtual);

        if (superModel == null) {
            return new ActionResult(false, "Super model is null");
        }

        if (superModel.getTipoMedia() == null) {
            return new ActionResult(false, "Media type cannot be null");
        }

        // Define comportamento com base no tipo de mídia
        if (superModel.getTipoMedia() == TipoMedia.FILME) {
            superModel.Avaliar(model);
            FilmeRepository.getInstance().update((Filme) superModel);
        } else if (superModel.getTipoMedia() == TipoMedia.LIVRO) {
            superModel.Avaliar(model);
            LivroRepository.getInstance().update((Livro) superModel);
        } else if (superModel.getTipoMedia() == TipoMedia.TEMPORADA) {
            superModel.Avaliar(model);
            Temporada temporada = (Temporada) superModel;
            SerieRepository serieRepository = SerieRepository.getInstance();
            Serie serie = serieRepository.getItemById(temporada.getSerieId());
            serie.updateTemporadaById(temporada.getId(), temporada);
            serieRepository.update(serie);
        } else {
            return new ActionResult(false, "Tipo de mídia inválido");
        }

        return new ActionResult(true, "Avaliacao criada com sucesso");
    }
}
