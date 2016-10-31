package sk.mrtn.library.client.device;

import com.google.gwt.core.client.GWT;

/**
 * Created by martinliptak on 31/10/2016.
 */
public class DeviceType {

    private static final IDeviceType TYPE = GWT.create(IDeviceType.class);

    private DeviceType(){}

    public static boolean isPhone() {return TYPE.isPhone();}

    public static boolean isTablet() {return TYPE.isTablet();}

    public static boolean isDesktop() {return TYPE.isDesktop();}


}