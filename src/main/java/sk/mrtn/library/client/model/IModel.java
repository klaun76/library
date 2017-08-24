package sk.mrtn.library.client.model;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * @author klaun with courtesy of fishi
 */
public interface IModel {
    IModel getParent();

    IModel getModel(String name);

    <T> IModelValue<T> set(String key, T value);

    <T> IModelValue<T> set(IModelKey<T> key, T value);

    <T> T get(String key);

    <T> T get(String key, T defaultValue);

    <T> T get(IModelKey<T> key);

    <T> IModelValue<T> getModelValue(String key);

    <T> IModelValue<T> getModelValue(String key, T defaultValue);

    <T> IModelValue<T> getModelValue(IModelKey<T> key);

    <T> HandlerRegistration addHandler(String key, IModelValueChangeHandler<T> handler);

    <T> HandlerRegistration addHandler(IModelKey<T> key, IModelValueChangeHandler<T> handler);
}
