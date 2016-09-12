package sk.mrtn.library.client.communication;

import com.google.gwt.http.client.*;
import com.google.gwt.logging.client.LogConfiguration;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 05/05/16.
 */
public class HttpRequest implements IHttpRequest, IHttpRequestRegistration, RequestCallback {

    protected static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(HttpRequest.class.getSimpleName());
            LOG.setLevel(Level.ALL);
        }
    }

    private IOnLoadeFinishedListener onLoadeFinishedListener;
    private RequestBuilder requestBuilder;
    private Request request;

    @Inject
    HttpRequest(){}

    @Override
    public IHttpRequestRegistration sendRequest(IOnLoadeFinishedListener onLoadeFinishedListener, String url) {
        this.onLoadeFinishedListener = onLoadeFinishedListener;
        this.requestBuilder = new RequestBuilder(RequestBuilder.POST, url);
        this.requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded");
        this.requestBuilder.setTimeoutMillis(5000);
        try {
            this.request = requestBuilder.sendRequest(null, this);
        } catch (RequestException e) {
            if (LogConfiguration.loggingIsEnabled()) LOG.severe("<< fail");

        }

        return this;
    }

    @Override
    public void cancel() {
        if (this.request != null && this.request.isPending()) {
            this.request.cancel();
        }
    }

    @Override
    public void onResponseReceived(Request request, Response httpResponse) {
        switch (httpResponse.getStatusCode()) {
            case 200:
                this.onLoadeFinishedListener.onSuccess(httpResponse.getText());
                break;
            default:
                if (LogConfiguration.loggingIsEnabled()) LOG.severe("<< onResponseReceived: "+httpResponse.getStatusCode());
                this.onLoadeFinishedListener.onError();
        }
    }

    @Override
    public void onError(Request request, Throwable exception) {
        if (LogConfiguration.loggingIsEnabled()) LOG.severe("<< onError: "+exception.getMessage());
        this.onLoadeFinishedListener.onError();
    }
}
