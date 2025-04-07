package controller.action;

public interface Action<T> {
    ActionResult execute(T model);
}