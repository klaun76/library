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
import jsinterop.annotations.JsIgnore;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsType;

@JsType(isNative = true)
public class Stats {

    public int REVISION;

    public Node dom;

    /**
     *  method was in original code, but compiler threw waring so i desabled it
     *  because it's not used anyway.
     *  Warnings in sk/mrtn/library/client/utils/stats/Stats.java
     *  [WARN] Line 25: [unusable-by-js] Type of parameter 'a' in 'void Stats.addPanel(Node)' is not usable by but exposed to JavaScript.
     */
    // public native void addPanel(Node node);

    public native void showPanel(int type); // 0: fps, 1: ms, 2: mb, 3+: custom

    public native void begin();

    public native void end();

    public native void update();

}
