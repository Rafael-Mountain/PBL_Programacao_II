package com.mountain_vd.controller.util;

import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TitledPane;
import javafx.util.Duration;

/**
 * Utilitário para adicionar uma classe CSS ao TitledPane
 * somente após o colapso ser totalmente concluído (incluindo animação).
 */
public class TitledPaneCollapseListener {

    private static final String COLLAPSED_CLASS = "titled-pane-collapsed";

    public static void attach(TitledPane titledPane) {
        titledPane.expandedProperty().addListener((obs, wasExpanded, isNowExpanded) -> {
            if (!isNowExpanded) {
                // Espera um tempo fixo (animação padrão dura ~200ms)
                PauseTransition wait = new PauseTransition(Duration.millis(350));
                wait.setOnFinished(event -> {
                    if (!titledPane.isExpanded() && !titledPane.getStyleClass().contains(COLLAPSED_CLASS)) {
                        titledPane.getStyleClass().add(COLLAPSED_CLASS);
                    }
                });
                wait.play();
            } else {
                titledPane.getStyleClass().remove(COLLAPSED_CLASS);
            }
        });
    }
}
