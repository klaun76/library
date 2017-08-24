package sk.mrtn.library.client.model;

import com.google.gwt.event.shared.HandlerRegistration;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/**
 * @author klaun with courtesy of fishi
 */
public class Model implements IModel {
    private Map<String, Model> models;
    private Map<String, IModelValue<?>> values;
    private Provider<Model> modelProvider;
    private IModel parent;

    @Inject
    Model(Provider<Model> modelProvider) {
        this.modelProvider = modelProvider;
        this.models = new HashMap<>();
        this.values = new HashMap<>();
    }

    @Override
    public IModel getModel(final String name) {
        if (name.contains(".")) {
            return getModelRecursively(name);
        }
        Model model = this.models.get(name);
        if (model == null) {
            model = this.modelProvider.get();
            model.setParent(this);
            this.models.put(name, model);
        }
        return model;
    }

    IModel getRoot() {
        IModel root = this;
        while (root.getParent() != null) {
            root = root.getParent();
        }

        return root;
    }

    @Override
    public IModel getParent() {
        return this.parent;
    }

    void setParent(final IModel parent) {
        if (this.parent != null) {
            throw new RuntimeException("Changing model's parent is not allowed!");
        }
        this.parent = parent;
    }

    IModel getModelRecursively(final String name) {
        final String[] split = name.split("\\.");
        IModel model = this;
        for (String n : split) {
            model = model.getModel(n);
        }
        return model;
    }

    @Override
    public <T> IModelValue<T> set(final IModelKey<T> key, final T value) {
        return set(key.getName(), value);
    }

    @Override
    public <T> IModelValue<T> set(final String key, final T value) {
        IModelValue<T> modelValue = getModelValue(key);
        modelValue.set(value);
        return modelValue;
    }

    @Override
    public <T> T get(final String key) {
        return get(key, null);
    }

    @Override
    public <T> T get(final IModelKey<T> key) {
        return get(key.getName(), key.getDefaultValue());
    }

    @Override
    public <T> T get(final String key, final T defaultValue) {
        return getModelValue(key, defaultValue).get();
    }

    @Override
    public <T> IModelValue<T> getModelValue(final String key) {
        return getModelValue(key, null);
    }

    @Override
    public <T> IModelValue<T> getModelValue(final IModelKey<T> key) {
        return getModelValue(key.getName(), key.getDefaultValue());
    }

    @Override
    public <T> IModelValue<T> getModelValue(final String key, final T defaultValue) {
        if (key.contains(".")) {
            return getModelValueRecursively(key, defaultValue);
        }
        IModelValue<T> modelValue = (IModelValue<T>) this.values.get(key);
        if (modelValue == null) {
            modelValue = ModelValue.create(this, key, defaultValue);
            this.values.put(key, modelValue);
        }
        return modelValue;
    }

    <T> IModelValue<T> getModelValueRecursively(final String key, final T defaultValue) {
        final int lastIndexOf = key.lastIndexOf(".");
        final IModel model = getModel(key.substring(0, lastIndexOf));
        return model.getModelValue(key.substring(lastIndexOf + 1), defaultValue);
    }

    @Override
    public <T> HandlerRegistration addHandler(final IModelKey<T> key, final IModelValueChangeHandler<T> handler) {
        return addHandler(key.getName(), key.getDefaultValue(), handler);
    }

    @Override
    public <T> HandlerRegistration addHandler(final String key, final IModelValueChangeHandler<T> handler) {
        return this.<T>getModelValue(key).addChangeHandler(handler);
    }

    protected <T> HandlerRegistration addHandler(final String key, final T defaultValue, final IModelValueChangeHandler<T> handler) {
        return getModelValue(key, defaultValue).addChangeHandler(handler);
    }
}