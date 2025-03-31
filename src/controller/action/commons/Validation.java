package controller.action.commons;

import model.Filme;

public interface Validation<T> {
    boolean isValid(T model);
    String getErrorMessage();
}