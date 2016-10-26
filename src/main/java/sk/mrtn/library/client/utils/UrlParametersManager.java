package sk.mrtn.library.client.utils;

import com.google.gwt.http.client.URL;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.Window;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 24/04/16.
 */
public class UrlParametersManager implements IUrlParametersManager {

    private static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(UrlParametersManager.class.getSimpleName());
            LOG.setLevel(Level.SEVERE);
        }
    }
    private final String title;

    @Inject
    public UrlParametersManager(){
        title = Window.getTitle();
        LOG.info("constructor");
    }

    @Override
    public String getParameter(String parameterName) {
        return Window.Location.getParameter(parameterName);
    }

    @Override
    public void setParameter(String parameter, String value) {
        StringBuilder paramsAsString = new StringBuilder();

        //REVIEW OF EXISTING KEYS with replacement of old values
        Map<String, List<String>> parametersMap = Window.Location.getParameterMap();
        for (String paramKey : parametersMap.keySet()) {
            String oldValue = parametersMap.get(paramKey).get(0);
            addValidSeparator(paramsAsString);
            if (paramKey.equals(parameter)) {
                oldValue = value;
            }
            paramsAsString.append(formatKeyValue(paramKey, oldValue));
        }

        //NEW KEY
        if (parametersMap.get(parameter) == null) {
            addValidSeparator(paramsAsString);
            paramsAsString.append(formatKeyValue(parameter, value));
        }
        updateUrl(paramsAsString.toString());
    }

    @Override
    public void removeParameter(String key) {
        StringBuilder paramsAsString = new StringBuilder();

        //REVIEW OF EXISTING KEYS and skip adding key
        Map<String, List<String>> parametersMap = Window.Location.getParameterMap();
        for (String paramKey : parametersMap.keySet()) {
            String oldValue = parametersMap.get(paramKey).get(0);
            addValidSeparator(paramsAsString);
            if (paramKey.equals(key)) {
                continue;
            }
            paramsAsString.append(formatKeyValue(paramKey, oldValue));
        }

        updateUrl(paramsAsString.toString());
    }

    @Override
    public boolean parameterEquals(String parameterName, String parameterValue) {
        String pValue = getParameter(parameterName);
        if (pValue != null && pValue.equals(parameterValue)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String getEncodedParameter(String parameterName) {
        String parameter = getParameter(parameterName);
        if (parameter == null) {
            return null;
        } else {
            return b64decode0(URL.decode(parameter));
        }
    }

    private native String b64decode0(String ecoded) /*-{
      return window.atob(ecoded);
    }-*/;

    /********************************************************
     /* Private methods
     /********************************************************/

    private StringBuilder addValidSeparator(final StringBuilder parameters) {
        if (parameters.toString().isEmpty()) {
            return !parameters.toString().endsWith("?") ? parameters.append("?") : parameters;
        }
        return !parameters.toString().endsWith("&") ? parameters.append("&") : parameters;
    }
    private String formatKeyValue(String key, String value) {
        StringBuilder paramAsString = new StringBuilder();
        paramAsString.append(key);
        paramAsString.append("=");
        paramAsString.append(value);
        return paramAsString.toString();
    }

    private void updateUrl(final String params) {
        updateUrl0(params);
    }
    protected native void updateUrl0(final String params) /*-{
        var title = this.@sk.mrtn.library.client.utils.UrlParametersManager::title;
        $wnd.history.replaceState('', title, params);
    }-*/;
}