package controller.action;

public interface Validation<T> {
    boolean isValid(T model);
    String  getErrorMessage();
}