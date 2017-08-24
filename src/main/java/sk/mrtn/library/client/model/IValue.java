package sk.mrtn.library.client.model;

/**
 * @author klaun with courtesy of fishi
 */

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents object that can be interpreted as {@code T}.
 *
 * @author Tomas Rybnicky
 */
public interface IValue<T> {
    T get();
    HandlerRegistration addChangeHandler(IValueChangeHandler<T> changeHandler);
}
