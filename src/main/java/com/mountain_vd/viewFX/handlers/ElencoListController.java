package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.util.StringUtil;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.ObservableList;

public class ElencoListController {
   RootScene rootScene;

    public ElencoListController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    public void addAtor(String ator, ObservableList<String> elencoList) {
        String atorFormatado = ator.trim().toLowerCase();

        boolean jaExiste = elencoList.stream()
                .anyMatch(a -> a.trim().toLowerCase().equals(atorFormatado));

        if (jaExiste) {
            rootScene.showMessage("Ator já adicionado.\nO ator \"" + ator + "\" já está na lista.");
        } else {
            System.out.println("Adicionando ator: " + ator);

            elencoList.add(StringUtil.capitalize(ator));
        }

    }
}
