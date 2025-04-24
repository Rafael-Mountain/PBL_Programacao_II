package controller.action;

public abstract class BaseSetAction<T,M> extends BaseAction<T>{
    protected M superModel;

    public BaseSetAction(Validation<T> validation) {
        super(validation);
    }

    public void setSuperModel(M superModel) {
        this.superModel = superModel;
    }

}
