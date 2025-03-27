import controller.DataBase.FilmeRepository;
import controller.action.Filme.CreateFilmeAction;
import controller.action.Filme.CreateFilmeValidation;
import controller.action.commons.ActionResult;
import model.Filme;
import model.Genero;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Testes {

    static void rodar() {
        Filme filme1 = new Filme();

        LocalDateTime dataLancamento = LocalDateTime.now();
        List<Genero> generos = new ArrayList<>();
        generos.add(new Genero("Ação"));
        generos.add(new Genero("Fantasia"));
        List<String> elenco = new ArrayList<>();
        elenco.add("Robert downey jr");
        elenco.add("Scarlett Johansson");

        Filme filme2 = new Filme("Avengers",dataLancamento,true,generos,"Avengers Ultimato",
                "Prime Video",elenco,"Alguem",120,"Filme muito Bom");

        System.out.println(filme1);
        System.out.println("");
        System.out.println(filme2);
    }

    static void rodarTeste2() {
        Filme filme1 = new Filme();

        filme1.setDirecao("Steven Spielberg");
        filme1.setTitulo("O Poderoso Chefão");
        filme1.setTituloOriginal("The Godfather");
        filme1.setDuracao(175);
        filme1.setRoteiro("O filme");
        List<String> atores = new ArrayList<>();
        atores.add("Robert De Niro");
        atores.add("Al Patino");
        filme1.setElenco(atores);
        LocalDateTime dataLancamento2 = LocalDateTime.of(2008,1,1,0,0);
        filme1.setDataLancamento(dataLancamento2);
        List<Genero> generos2 = new ArrayList<>();
        generos2.add(new Genero("crime"));
        generos2.add(new Genero("drama"));
        filme1.setGeneros(generos2);
        filme1.addGenero(new Genero("Suspense"));



        LocalDateTime dataLancamento = LocalDateTime.now();
        List<Genero> generos = new ArrayList<>();
        generos.add(new Genero("Ação"));
        generos.add(new Genero("Fantasia"));
        List<String> elenco = new ArrayList<>();
        elenco.add("Robert downey jr");
        elenco.add("Scarlett Johansson");

        Filme filme2 = new Filme("Avengers",dataLancamento,true,generos,"Avengers Ultimato",
                "Prime Video",elenco,"Alguem",120,"Filme muito Bom");

        CreateFilmeAction action = new CreateFilmeAction(new CreateFilmeValidation());
        ActionResult result2 = action.execute(filme1);
        ActionResult result = action.execute(filme2);

        FilmeRepository repository = FilmeRepository.getInstance();

        System.out.println(result.getMessage());
        System.out.println(result2.getMessage());
        System.out.println(repository.getItems());

        //rodar();
    }
}
