package sk.mrtn.library.client.utils;

/**
 * Created by martinliptak on 24/04/16.
 * can get and set url parameters, parameters
 * are always directly read from url and written to url
 * reader does not store values locally
 */
public interface IUrlParametersManager {
    String getParameter(String parameterName);

    void setParameter(String parameterName, String parameterValue);

    void removeParameter(String key);

    /**
     * in most cases we need just to verify url parameter
     * status and if parameter is not set there is need to
     * check null and then comparison, this method will
     * do the doublecheck and provide if parameter is desired
     * value or not
     * @param parameterName
     * @param parameterValue
     * @return
     */
    boolean parameterEquals(String parameterName, String parameterValue);

    /**
     * url encoded and base 64 encoded parameter
     * @param parameterName
     * @return
     */
    String getEncodedParameter(String parameterName);
}

