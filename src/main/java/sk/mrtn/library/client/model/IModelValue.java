package sk.mrtn.library.client.model;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * @author klaun with courtesy of fishi
 */
public interface IModelValue<T> extends IMutableValue<T> {
    HandlerRegistration addChangeHandler(IModelValueChangeHandler<T> changeHandler);
}
