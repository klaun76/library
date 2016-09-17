package sk.mrtn.library.client.utils.stats;

/**
 * Created by martinliptak on 28/08/16.
 * http://github.com/mrdoob/stats.js
 */

import elemental.dom.Node;
import jsinterop.annotations.*;

import javax.inject.Inject;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Stats {

    // PUBLIC FIELDS
    @JsProperty
    public int REVISION;

    @JsProperty
    public Node dom;

    @JsProperty
    public Node domElement;

    @Inject
    @JsConstructor
    public Stats(){}

    // PUBLIC METHODS
    @JsMethod
    public native void addPanel(Node a);

    @JsMethod
    public native void showPanel( int type ); // 0: fps, 1: ms, 2: mb, 3+: custom

    @JsMethod
    public native void begin();

    @JsMethod
    public native void end();

    @JsMethod
    public native void update();

    @JsMethod
    public native void setMode(int type);
}
