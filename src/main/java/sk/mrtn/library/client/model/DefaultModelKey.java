package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */
public class DefaultModelKey<T> implements IModelKey<T> {

    public static <T> DefaultModelKey<T> create(final String name, final T defaultValue) {
        return new DefaultModelKey<>(name, defaultValue);
    }

    private String name;
    private T defaultValue;

    protected DefaultModelKey(final String name, final T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T getDefaultValue() {
        return this.defaultValue;
    }
}
