package sk.mrtn.library.client.communication;

/**
 * Created by martinliptak on 05/05/16.
 * E – Element (used extensively by the Java Collections Framework, for example ArrayList, Set etc.)
 * K – Key (Used in Map)
 * N – Number
 * T – Type
 * V – Value (Used in Map)
 * S,U,V etc. – 2nd, 3rd, 4th types
 */
public interface IHttpRequest {
    interface IOnLoadeFinishedListener<T> {
        void onSuccess(T response);
        void onError();
    }
    IHttpRequestRegistration sendRequest(IOnLoadeFinishedListener onLoadeFinishedListener, String url);
}
