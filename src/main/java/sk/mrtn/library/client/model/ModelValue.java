package sk.mrtn.library.client.model;

import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author klaun with courtesy of fishi
 */
public class ModelValue<T> extends MutableValue<T> implements IModelValue<T> {

    static <T> ModelValue<T> create(final Model model, final String key, final T value) {
        return new ModelValue<>(model, key, value);
    }

    private Model model;
    private String key;
    private List<IModelValueChangeHandler<T>> modelValueChangeHandlers;

    protected ModelValue(final Model model, final String key, final T value) {
        super(value);
        this.model = model;
        this.key = key;
    }

    protected void notifyChangeHandlers() {
        super.notifyChangeHandlers();

        if (this.modelValueChangeHandlers != null) {
            for (IModelValueChangeHandler<T> changeHandler : this.modelValueChangeHandlers) {
                changeHandler.onModelValueChanged(this);
            }
        }
    }

    @Override
    public HandlerRegistration addChangeHandler(final IModelValueChangeHandler<T> changeHandler) {
        if (this.modelValueChangeHandlers == null) {
            this.modelValueChangeHandlers = new ArrayList<>();
        }
        this.modelValueChangeHandlers.add(changeHandler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                if (modelValueChangeHandlers != null) {
                    modelValueChangeHandlers.remove(changeHandler);
                }
            }
        };
    }
}
