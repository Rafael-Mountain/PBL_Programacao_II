import controller.action.genero.CreateGeneroAction;
import controller.action.genero.CreateGeneroValidation;
import controller.dataBase.FilmeRepository;
import controller.action.filme.CreateFilmeAction;
import controller.action.filme.CreateFilmeValidation;
import controller.action.ActionResult;
import controller.dataBase.GeneroRepository;
import model.Filme;
import model.Genero;
import view.ListGridMedia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void rodar3() {
        String[] generos = {
                "Ação", "Fantasia", "Sci-Fi", "Romance",
                "Terror", "Comédia", "Drama", "Suspense",
                "Animação", "Documentário"
        };

        for (String nome : generos)
            new CreateGeneroAction(new CreateGeneroValidation()).execute(new Genero(nome));

        List<Genero> g = GeneroRepository.getInstance().getItems();
        CreateFilmeAction action = new CreateFilmeAction(new CreateFilmeValidation());

        List<Filme> filmes = Arrays.asList(
                new Filme("Avengers", LocalDateTime.now(), true, g.subList(0, 2), "Avengers Ultimato", "Prime Video",
                        Arrays.asList("Robert Downey Jr.", "Scarlett Johansson"), "Alguém", 120, "Filme muito bom"),

                new Filme("Her", LocalDateTime.now(), false, g.subList(3, 4), "Homem se apaixona por IA", "HBO Max",
                        Arrays.asList("Joaquin Phoenix", "Scarlett Johansson"), "Spike Jonze", 126, "Drama romântico"),

                new Filme("Corra!", LocalDateTime.now(), false, g.subList(4, 6), "Suspense psicológico", "Netflix",
                        Arrays.asList("Daniel Kaluuya", "Allison Williams"), "Jordan Peele", 104, "Terror e crítica social"),

                new Filme("Toy Story", LocalDateTime.now(), false, g.subList(8, 9), "Brinquedos vivos", "Disney+",
                        Arrays.asList("Tom Hanks", "Tim Allen"), "Pixar", 81, "Animação clássica"),

                new Filme("O Jogo da Imitação", LocalDateTime.now(), false, g.subList(5, 7), "Alan Turing", "Globoplay",
                        Arrays.asList("Benedict Cumberbatch", "Keira Knightley"), "Morten Tyldum", 113, "Drama histórico"),

                // Filmes adicionados
                new Filme("Interestelar", LocalDateTime.now(), false, g.subList(2, 3), "Viagem no tempo e espaço", "Netflix",
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway"), "Christopher Nolan", 169, "Ficção científica épica"),

                new Filme("O Labirinto do Fauno", LocalDateTime.now(), false, g.subList(1, 2), "Fantasia sombria na guerra civil espanhola", "HBO Max",
                        Arrays.asList("Ivana Baquero", "Sergi López"), "Guillermo del Toro", 118, "Conto sombrio e mágico"),

                new Filme("Senna", LocalDateTime.now(), false, g.subList(9, 10), "Documentário sobre Ayrton Senna", "Amazon Prime",
                        Arrays.asList("Ayrton Senna", "Alain Prost"), "Asif Kapadia", 106, "Documentário emocionante sobre um ídolo")
        );

        for (Filme f : filmes) {
            action.execute(f);
//            System.out.println("Filme criado: " + f);
        }
    }

}
