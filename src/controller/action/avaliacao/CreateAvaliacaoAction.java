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
        
        //Setar a data de avaliação
        LocalDateTime dataAtual = LocalDateTime.now();
        model.setDataAvaliacao(dataAtual);
        
        if (superModel == null) {
            return new ActionResult(false, "Super model is null");
        }
        
        if(superModel.getTipoMedia() == null){
            return new ActionResult(false, "Media type cannot be null");
        } 
        
        if (superModel.getTipoMedia()== TipoMedia.FILME) {
            // Avaliação de filme

            superModel.avaliar(model);
            FilmeRepository filmeRepository = FilmeRepository.getInstance();
            try {
                filmeRepository.update((Filme) superModel);
            }catch (Exception e){
                return new ActionResult(false, e.getMessage());
            }

        } else if (superModel.getTipoMedia() == TipoMedia.LIVRO) {
            // Avaliação de Livro

            superModel.avaliar(model);
            LivroRepository livroRepository = LivroRepository.getInstance();

            try {
                livroRepository.update((Livro) superModel);
            }catch (Exception e) {
                return new ActionResult(false, e.getMessage());
            }


        } else if (superModel.getTipoMedia() == TipoMedia.TEMPORADA) {
            // Avaliação de Temporada

            superModel.avaliar(model);
            Temporada temporada = (Temporada) superModel;
            SerieRepository serieRepository = SerieRepository.getInstance();
            int serieId = temporada.getSerieId();
            Serie serie = serieRepository.getItemById(serieId);

            if (serie == null) {
                return new ActionResult(false, "Serie not found");
            }

            serie.updateTemporadaById(temporada.getId(), temporada);

            try {
                serieRepository.update(serie);
            }
            catch (Exception e) {
                return new ActionResult(false, e.getMessage());
            }

        } else {
            return new ActionResult(false, "Tipo de mídia inválido");
        }
        
        return new ActionResult(true, "Avaliacao criada com sucesso");
    }
}
