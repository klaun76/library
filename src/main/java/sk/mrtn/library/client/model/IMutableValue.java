package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */
public interface IMutableValue<T> extends IValue<T> {
    void set(T value);
}
