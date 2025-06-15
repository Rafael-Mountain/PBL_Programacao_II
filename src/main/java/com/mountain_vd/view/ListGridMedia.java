package com.mountain_vd.view;

import com.mountain_vd.model.Media;

import java.util.List;

/**
 * Classe responsável por exibir uma lista de mídias (como filmes ou livros) em formato de tabela no terminal.
 * Cada linha da tabela exibe informações sobre uma mídia, como título, gênero, ano, tipo e pontuação.
 */
public class ListGridMedia {

    /**
     * Exibe a lista de mídias em formato de tabela no terminal.
     * A tabela é organizada em colunas com informações sobre a mídia, como título, gênero, ano, tipo e pontuação.
     *
     * @param medias Lista de mídias a ser exibida na tabela.
     */
    public void draw(List<Media> medias) {
        int colSeletor = 8;   // Tamanho da coluna para o seletor (índice)
        int colTitulo = 20;   // Tamanho da coluna para o título da mídia
        int colGenero = 20;   // Tamanho da coluna para o gênero
        int colAno = 6;       // Tamanho da coluna para o ano
        int colTipo = 20;     // Tamanho da coluna para o tipo de mídia
        int colPontuacao = 10; // Tamanho da coluna para a pontuação

        // Imprime linha superior
        printLinha(colSeletor, colTitulo, colGenero, colAno, colTipo, colPontuacao);

        // Cabeçalho da tabela
        System.out.printf("| %-"+colSeletor+"s | %-"+colTitulo+"s | %-"+colGenero+"s | %-"+colAno+"s | %-"+colTipo+"s | %-"+colPontuacao+"s |%n",
                "Seletor", "Título", "Gênero", "Ano", "Tipo", "Pontuação");

        // Linha separadora
        printLinha(colSeletor, colTitulo, colGenero, colAno, colTipo, colPontuacao);

        // Dados das mídias
        for (int i = 0; i < medias.size(); i++) {
            Media media = medias.get(i);
            String titulo = media.getTitulo();
            String genero = media.getGeneros().stream().map(g -> g.getNome()).toList().toString();
            String ano = String.valueOf(media.getDataLancamento().getYear());
            String tipoMedia = media.getTipoMedia().getDescricao();
            String seletor = String.valueOf(i);
            String pontuacao = String.valueOf(media.getPontuacao());

            // Trunca os textos que ultrapassam o tamanho da coluna
            if (titulo.length() > colTitulo) {
                titulo = titulo.substring(0, colTitulo - 3) + "...";
            }

            if (genero.length() > colGenero) {
                genero = genero.substring(0, colGenero - 3) + "...";
            }

            if (tipoMedia.length() > colTipo) {
                tipoMedia = tipoMedia.substring(0, colTipo - 3) + "...";
            }

            // Imprime os dados formatados na tabela
            System.out.printf("| %-"+colSeletor+"s | %-"+colTitulo+"s | %-"+colGenero+"s | %-"+colAno+"s | %-"+colTipo+"s | %-"+colPontuacao+"s |%n",
                    seletor, titulo, genero, ano, tipoMedia, pontuacao);
        }

        // Linha inferior
        printLinha(colSeletor, colTitulo, colGenero, colAno, colTipo, colPontuacao);
    }

    /**
     * Imprime uma linha separadora no formato de tabela, com base nos tamanhos das colunas fornecidos.
     *
     * @param col1 Tamanho da primeira coluna.
     * @param col2 Tamanho da segunda coluna.
     * @param col3 Tamanho da terceira coluna.
     * @param col4 Tamanho da quarta coluna.
     * @param col5 Tamanho da quinta coluna.
     * @param col6 Tamanho da sexta coluna.
     */
    private static void printLinha(int col1, int col2, int col3, int col4, int col5, int col6) {
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
        System.out.print("+");
        printRepetido('-', col6 + 2);
        System.out.println("+");
    }

    /**
     * Imprime um caractere repetido várias vezes.
     *
     * @param c O caractere a ser impresso.
     * @param vezes O número de vezes que o caractere será impresso.
     */
    private static void printRepetido(char c, int vezes) {
        for (int i = 0; i < vezes; i++) {
            System.out.print(c);
        }
    }
}
