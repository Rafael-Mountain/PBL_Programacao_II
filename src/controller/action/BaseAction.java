package controller.action;

public abstract class BaseAction<T> implements Action<T> {
    protected final Validation<T> validation;

    public BaseAction(Validation<T> validation) {
        this.validation = validation;
    }

    protected boolean isValid(T model) {
        return validation.isValid(model);
    }

    protected String getErrorMessage() {
        return validation.getErrorMessage();
    }


}

