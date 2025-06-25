package com.mountain_vd.controller.util;

import javafx.scene.control.TextFormatter;

public class TextFieldUtil {
    public static TextFormatter<String> numericFormatter(int maxDigits) {
        return new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0," + maxDigits + "}")) {
                return change;
            }
            return null;
        });
    }
}