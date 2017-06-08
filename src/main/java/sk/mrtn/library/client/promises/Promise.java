package sk.mrtn.library.client.promises;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

/**
 * Created by Martin Liptak on 06/04/2017.
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Promise {

    @JsConstructor
    public Promise(IPromiseExecutor executor){}

    public native static Promise all(Promise... promises);

    public native static Promise race(Promise... promises);

    public native static Promise reject(Promise... promises);

    public native static Promise resolve(Promise... promises);

    @JsMethod(name = "catch")
    public native Promise catch42(Object catchCallback);

    public native Promise then(Object resolveCallback, Object rejectCallback);
    public native Promise then(Object resolveCallback);

}
