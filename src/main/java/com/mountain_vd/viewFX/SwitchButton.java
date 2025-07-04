package com.mountain_vd.viewFX;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SwitchButton extends StackPane {

    // https://stackoverflow.com/questions/30593193/creating-sliding-on-off-switch-button-in-javafx

    private final Rectangle back = new Rectangle(30, 10, Color.RED);
    private final Button button = new Button();
    private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
    private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #f67d26;";

    // Usa BooleanProperty para o estado
    private final BooleanProperty stateProperty = new SimpleBooleanProperty(false);

    private void init() {
        getChildren().addAll(back, button);
        setMinSize(30, 15);
        back.setWidth(30);
        back.setHeight(10);
        back.setArcHeight(back.getHeight());
        back.setArcWidth(back.getHeight());
        back.setFill(Color.valueOf("#ced5da"));
        Double r = 2.0;
        button.setShape(new Circle(r));
        setAlignment(button, Pos.CENTER_LEFT);
        button.setMaxSize(15, 15);
        button.setMinSize(15, 15);
        button.setStyle(buttonStyleOff);
    }

    public SwitchButton() {
        init();

        EventHandler<Event> click = e -> {
            boolean currentState = stateProperty.get();
            if (currentState) {
                button.setStyle(buttonStyleOff);
                back.setFill(Color.valueOf("#ced5da"));
                setAlignment(button, Pos.CENTER_LEFT);
                stateProperty.set(false);
            } else {
                button.setStyle(buttonStyleOn);
                back.setFill(Color.valueOf("#8b4316"));
                setAlignment(button, Pos.CENTER_RIGHT);
                stateProperty.set(true);
            }
        };

        button.setFocusTraversable(false);
        setOnMouseClicked(click);
        button.setOnMouseClicked(click);
    }

    /**
     * Retorna a propriedade do estado, que pode ser observada para detectar mudanças.
     * @return BooleanProperty do estado do switch.
     */
    public BooleanProperty stateProperty() {
        return stateProperty;
    }

    /**
     * Retorna o estado atual do switch (true = ligado, false = desligado).
     * @return boolean estado atual.
     */
    public boolean isState() {
        return stateProperty.get();
    }

    /**
     * Define o estado do switch.
     * @param state true para ligado, false para desligado.
     */
    public void setState(boolean state) {
        if (stateProperty.get() != state) {
            stateProperty.set(state);
            // Atualiza visual de acordo
            if (state) {
                button.setStyle(buttonStyleOn);
                back.setFill(Color.valueOf("#8b4316"));
                setAlignment(button, Pos.CENTER_RIGHT);
            } else {
                button.setStyle(buttonStyleOff);
                back.setFill(Color.valueOf("#ced5da"));
                setAlignment(button, Pos.CENTER_LEFT);
            }
        }
    }

    /**
     * Permite adicionar um listener para mudanças do estado do switch.
     * @param listener Listener para mudanças de Boolean (estado).
     */
    public void addStateChangeListener(javafx.beans.value.ChangeListener<? super Boolean> listener) {
        stateProperty.addListener(listener);
    }
}
