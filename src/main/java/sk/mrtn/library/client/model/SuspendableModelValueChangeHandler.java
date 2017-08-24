package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */
public class SuspendableModelValueChangeHandler<T> implements IModelValueChangeHandler<T> {

    private boolean suspended;
    private IModelValueChangeHandler<T> wrappedHandler;

    public SuspendableModelValueChangeHandler(final IModelValueChangeHandler<T> wrappedHandler) {
        this.suspended = false;
        this.wrappedHandler = wrappedHandler;
    }

    public void setSuspended(final boolean suspended) {
        this.suspended = suspended;
    }

    @Override
    public void onModelValueChanged(final IModelValue<T> modelValue) {
        if (!this.suspended) {
            this.wrappedHandler.onModelValueChanged(modelValue);
        }
    }
}
