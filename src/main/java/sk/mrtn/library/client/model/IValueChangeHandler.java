package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */
public interface IValueChangeHandler<T> {
    void onValueChanged(IValue<T> value);
}
