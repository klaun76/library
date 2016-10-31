package sk.mrtn.library.client.utils.mobiledetect;

import jsinterop.annotations.*;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by martinliptak on 11/08/16.
 */
@Singleton
@JsType(isNative = true, namespace = JsPackage.GLOBAL )
public class MobileDetect {

    // PUBLIC STATIC FIELDS
    @JsProperty
    public static String version;

    // PUBLIC STATIC METHODS
    @JsMethod
    public static native Boolean isPhoneSized(Double maxPhoneWidth);

    // PUBLIC FIELDS
    @JsProperty
    public String ua;
    @JsProperty
    public int maxPhoneWidth;

    // PUBLIC METHODS

    @Deprecated
    @JsMethod
    public native String mobile();
    @JsMethod
    public native String phone();
    @JsMethod
    public native String tablet();
    @JsMethod
    public native String userAgent();
    @JsMethod
    public native String[] userAgents();
    @JsMethod
    public native String os();
    @JsMethod
    public native Double version(String key);
    @JsMethod
    public native String versionStr(String key);
    @JsMethod
    public native boolean is(String  key);
    @JsMethod
    public native boolean match(String pattern);
    @JsMethod
    public native boolean isPhoneSized(Integer maxPhoneWidth);
    @JsMethod
    public native String mobileGrade();

//    @JsConstructor
//    public MobileDetect(String userAgent, Double maxPhoneWidth){}

    @Inject
    @JsConstructor
    public MobileDetect(String userAgent){}

}
