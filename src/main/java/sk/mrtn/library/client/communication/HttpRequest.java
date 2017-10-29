package sk.mrtn.library.client.communication;

import com.google.gwt.http.client.*;
import com.google.gwt.logging.client.LogConfiguration;
import sk.mrtn.library.client.promises.IPromiseFulfill;
import sk.mrtn.library.client.promises.IPromiseReject;
import sk.mrtn.library.client.promises.Promise;

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
    private IPromiseFulfill fulfill;
    private IPromiseReject reject;

    @Inject
    HttpRequest(){}

    @Override
    public Promise sendRequest(String url) {
        return new Promise((fulfill, reject) -> {
            this.fulfill = fulfill;
            this.reject = reject;
            this.requestBuilder = new RequestBuilder(RequestBuilder.POST, url);
            this.requestBuilder.setHeader("Content-type", "application/x-www-form-urlencoded");
            this.requestBuilder.setTimeoutMillis(5000);
            try {
                this.request = requestBuilder.sendRequest(null, this);
            } catch (RequestException e) {
                if (LogConfiguration.loggingIsEnabled()) LOG.severe("<< fail");
                reject.exec(null);
            }

        });
    }

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
                if (this.onLoadeFinishedListener != null) {
                    this.onLoadeFinishedListener.onSuccess(httpResponse.getText());
                }
                if (this.fulfill != null) {
                    this.fulfill.exec(httpResponse.getText());
                }
                break;
            default:
                if (LogConfiguration.loggingIsEnabled()) LOG.severe("<< onResponseReceived: "+httpResponse.getStatusCode());
                if (this.onLoadeFinishedListener != null) {
                    this.onLoadeFinishedListener.onError();
                }
                if (this.reject != null) {
                    this.reject.exec(null);
                }
        }
    }

    @Override
    public void onError(Request request, Throwable exception) {
        if (LogConfiguration.loggingIsEnabled()) LOG.severe("<< onError: "+exception.getMessage());
        if (this.onLoadeFinishedListener != null) {
            this.onLoadeFinishedListener.onError();
        }
        if (this.reject != null) {
            this.reject.exec(null);
        }
    }
}
