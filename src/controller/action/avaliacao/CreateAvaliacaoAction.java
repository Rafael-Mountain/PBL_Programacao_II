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

public class CreateAvaliacaoAction extends BaseSetAction<Avaliacao, IAvaliavel> {

    public CreateAvaliacaoAction(Validation<Avaliacao> validation) {
        super(validation);
    }

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

            superModel.Avaliar(model);
            FilmeRepository filmeRepository = FilmeRepository.getInstance();
            try {
                filmeRepository.update((Filme) superModel);
            }catch (Exception e){
                return new ActionResult(false, e.getMessage());
            }

        } else if (superModel.getTipoMedia() == TipoMedia.LIVRO) {
            // Avaliação de Livro

            superModel.Avaliar(model);
            LivroRepository livroRepository = LivroRepository.getInstance();

            try {
                livroRepository.update((Livro) superModel);
            }catch (Exception e) {
                return new ActionResult(false, e.getMessage());
            }


        } else if (superModel.getTipoMedia() == TipoMedia.TEMPORADA) {
            // Avaliação de Temporada

            superModel.Avaliar(model);
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
