package com.mountain_vd.view.commons;

import java.util.Scanner;

/**
 * A interface {@code IScreen} define a estrutura para telas interativas que desenham (ou exibem) conteúdo no terminal.
 *
 * Qualquer classe que implemente esta interface deve fornecer uma implementação do método {@link #draw(Scanner)} para desenhar a tela.
 * O método {@code draw} é responsável por exibir informações e coletar entradas do usuário através de um {@link Scanner}.
 *
 * <p>Exemplo de implementação:</p>
 * <pre>
 * public class ExemploTela implements IScreen {
 *     @Override
 *     public void draw(Scanner terminal) {
 *         System.out.println("Exibindo conteúdo na tela.");
 *         // Lógica para coletar e processar a entrada do usuário.
 *     }
 * }
 * </pre>
 *
 * @author
 */
public interface IScreen {

    /**
     * Método responsável por desenhar (exibir) o conteúdo da tela e coletar entradas do usuário.
     *
     * @param terminal O scanner usado para ler as entradas do usuário.
     */
    void draw(Scanner terminal);
}
