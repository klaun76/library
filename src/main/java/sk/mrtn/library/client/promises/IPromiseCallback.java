package sk.mrtn.library.client.promises;

import jsinterop.annotations.JsFunction;

/**
 * Created by Martin Liptak on 07/06/2017.
 */
@JsFunction
public interface IPromiseCallback {
    Object exec(Object o);
}
