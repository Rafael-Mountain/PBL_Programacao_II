package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.util.StringUtil;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.ObservableList;

/**
 * Controlador responsável por gerenciar a lista de atores (elenco) em formulários.
 * Fornece métodos para adicionar atores à lista evitando duplicatas.
 */
public class ElencoListController {
    private final RootScene rootScene;

    /**
     * Construtor que recebe a instância da RootScene para permitir exibição de mensagens.
     *
     * @param rootScene referência à cena raiz da aplicação
     */
    public ElencoListController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Adiciona um ator à lista de elenco, garantindo que não haja duplicatas.
     * A comparação é feita ignorando espaços e diferenças entre maiúsculas/minúsculas.
     * Caso o ator já exista na lista, exibe uma mensagem de erro na interface.
     *
     * @param ator       nome do ator a ser adicionado
     * @param elencoList lista observável contendo os atores já adicionados
     */
    public void addAtor(String ator, ObservableList<String> elencoList) {
        String atorFormatado = ator.trim().toLowerCase();

        boolean jaExiste = elencoList.stream()
                .anyMatch(a -> a.trim().toLowerCase().equals(atorFormatado));

        if (jaExiste) {
            rootScene.showError("Ator já adicionado.\nO ator \"" + ator + "\" já está na lista.");
        } else {
            elencoList.add(StringUtil.capitalize(ator));
        }
    }
}
