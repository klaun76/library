package sk.mrtn.library.client.model;

/**
 * Created by martinliptak on 18/08/16.
 */

import com.google.gwt.event.shared.HandlerRegistration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author klaun with courtesy of fishi
 */
public class MutableValue<T> implements IMutableValue<T> {

    private static Logger LOG = Logger.getLogger("MutableValue");
    public static <U> MutableValue<U> create(U value) {
        return new MutableValue<>(value);
    }

    private T value;
    private List<IValueChangeHandler<T>> changeHandlers;

    protected MutableValue(final T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return this.value;
    }

    public void set(T value) {
        if (!this.value.equals(value)) {
            this.value = value;
            notifyChangeHandlers();
        }
    }

    protected void notifyChangeHandlers() {
        if (this.changeHandlers != null) {
            for (IValueChangeHandler<T> changeHandler : this.changeHandlers) {
                changeHandler.onValueChanged(this);
            }
        }
    }

    @Override
    public HandlerRegistration addChangeHandler(final IValueChangeHandler<T> changeHandler) {

        if (this.changeHandlers == null) {
            this.changeHandlers = new ArrayList<>();
        }

        this.changeHandlers.add(changeHandler);
        return new HandlerRegistration() {
            @Override
            public void removeHandler() {
                if (changeHandlers != null) {
                    changeHandlers.remove(changeHandler);
                }
            }
        };
    }

    protected void doDestroy() {
        this.value = null;
        safeClear(this.changeHandlers);
        this.changeHandlers = null;
    }

    public static void safeClear(final Collection<?> collection) {
        if (collection != null) {
            try {
                collection.clear();
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Failed to destroy collection " + collection, e);
            }
        }
    }

    public static void safeClear(final Map<?, ?> map) {
        if (map != null) {
            try {
                map.clear();
            } catch (Exception e) {
                LOG.log(Level.SEVERE, "Failed to destroy map " + map, e);
            }
        }
    }
}