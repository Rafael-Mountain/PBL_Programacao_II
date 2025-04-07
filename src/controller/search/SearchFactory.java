package controller.search;

public class SearchFactory {
    public static Search createSearch(SearchFields type) {
        switch (type) {
            case SearchFields.TUDO:
                return new SearchAll();
//            case "Titulo":
//                return new SearchTitulo();
//            case "Genero":
//                return new SearchGenero();
//            case "Ano de Lançamento":
//                return new SearchAnoLancamento();
//            case "Diretor":
//                return new SearchDiretor();
//            case "Autor":
//                return new SearchAutor();
//            case "ISBN":
//                return new SearchISBN();
//            case "Duração":
//                return new SearchDuracao();
//            case "Elenco":
//                return new SearchElenco();
//            case "Roteiro":
//                return new SearchRoteiro();
            default:
                throw new IllegalArgumentException("Tipo de busca inválido: " + type);
        }
    }
}
