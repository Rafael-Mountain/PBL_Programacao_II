package view;

import model.Media;

import java.util.List;

public class ListGridMedia {

    public void draw(List<Media> medias) {
        int colSeletor = 8;
        int colTitulo = 20;
        int colGenero = 20;
        int colAno = 6;
        int colTipo = 20;

        // Imprime linha superior
        printLinha(colSeletor, colTitulo, colGenero, colAno, colTipo);

        // Cabeçalho
        System.out.printf("| %-"+colSeletor+"s | %-"+colTitulo+"s | %-"+colGenero+"s | %-"+colAno+"s | %-"+colTipo+"s |%n",
                "Seletor", "Título", "Gênero", "Ano", "Tipo");

        // Linha separadora
        printLinha(colSeletor, colTitulo, colGenero, colAno, colTipo);

        // Dados
        for (int i = 0; i < medias.size(); i++) {
            Media media = medias.get(i);
            String titulo = media.getTitulo();
            String genero = media.getGeneros().stream().map(g -> g.getNome()).toList().toString();
            String ano = String.valueOf(media.getDataLancamento().getYear());
            String tipoMedia = media.getTipoMedia().getDescricao();
            String seletor = String.valueOf(i);

            if (titulo.length() > colTitulo) {
                titulo = titulo.substring(0, colTitulo - 3) + "...";
            }

            if (genero.length() > colGenero) {
                genero = genero.substring(0, colGenero - 3) + "...";
            }

            if (tipoMedia.length() > colTipo) {
                tipoMedia = tipoMedia.substring(0, colTipo - 3) + "...";
            }

            System.out.printf("| %-"+colSeletor+"s | %-"+colTitulo+"s | %-"+colGenero+"s | %-"+colAno+"s | %-"+colTipo+"s |%n",
                    seletor, titulo, genero, ano, tipoMedia);
        }

        // Linha inferior
        printLinha(colSeletor, colTitulo, colGenero, colAno, colTipo);
    }

    private static void printLinha(int col1, int col2, int col3, int col4, int col5) {
        System.out.print("+");
        printRepetido('-', col1 + 2);
        System.out.print("+");
        printRepetido('-', col2 + 2);
        System.out.print("+");
        printRepetido('-', col3 + 2);
        System.out.print("+");
        printRepetido('-', col4 + 2);
        System.out.print("+");
        printRepetido('-', col5 + 2);
        System.out.println("+");
    }

    private static void printRepetido(char c, int vezes) {
        for (int i = 0; i < vezes; i++) {
            System.out.print(c);
        }
    }
}
