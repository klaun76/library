package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */
public interface IModelValueChangeHandler<T> {
    void onModelValueChanged(IModelValue<T> modelValue);
}
