package sk.mrtn.library.client.utils.stats;

/**
 * Created by martinliptak on 28/08/16.
 * http://github.com/mrdoob/stats.js
 * javascript Function does not return Type Stats, but
 * new Object. Injection does some checking for type of
 * and at the time of creation i could not find better
 * solution to a problem
 */

import elemental.dom.Node;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public interface Stats {

    @JsProperty(name = "REVISION")
    int getREVISION();

    @JsProperty(name = "dom")
    Node getDom();

    @JsMethod
    void addPanel(Node a);

    @JsMethod
    void showPanel(int type); // 0: fps, 1: ms, 2: mb, 3+: custom

    @JsMethod
    void begin();

    @JsMethod
    void end();

    @JsMethod
    void update();

}
