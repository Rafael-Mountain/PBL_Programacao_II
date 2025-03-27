package controller.action.commons;

public interface Action<T> {
    ActionResult execute(T model);
}