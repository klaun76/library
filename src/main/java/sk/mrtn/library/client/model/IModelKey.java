package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */
public interface IModelKey<T> {
    String getName();
    T getDefaultValue();
}
