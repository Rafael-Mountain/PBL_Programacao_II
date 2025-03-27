package controller.action.commons;

public interface Validation<T> {
    boolean isValid(T model);
    String getErrorMessage();
}