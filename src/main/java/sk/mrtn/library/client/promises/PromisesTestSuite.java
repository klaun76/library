package sk.mrtn.library.client.promises;

import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.Timer;
import jsinterop.annotations.JsMethod;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Martin Liptak on 06/04/2017.
 */
public class PromisesTestSuite {
     static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(PromisesTestSuite.class.getSimpleName());
            LOG.setLevel(Level.ALL);
        }
    }

    private final int timedelay = 1000;

    @Inject
    PromisesTestSuite(){
        LOG.fine("constructor");
    }

    public void test() {
        LOG.fine("test started");

        getPromise(timedelay,true, 0,"c0")
                .then(new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        return getPromise(timedelay, true, 1, o + "c1");
                    }
                })
                .then(new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        return getPromise(timedelay, true, 2, o + "c2");
                    }
                })
                .then(new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        return o+"42";
                    }
                })
                .then(new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        return getPromise(timedelay, true, 3, o + "c3");
                    }
                })
                .then(new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        reportSuccess(o);
                        return o;
                    }
                }
                , new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        reportReject(o);
                        return o;
                    }
                }
                )
                .catch42(new IPromiseCallback() {
                    @Override
                    public Object exec(Object o) {
                        reportCatch(o);
                        return o;
                    }
                })
        ;

    }

    private Promise getPromise(int time, boolean shoulFulfill, int id, Object data) {
        return new Promise(
                new IPromiseExecutor() {
                    @Override
                    public void exec(IPromiseFulfill fulfill, IPromiseReject reject) {
                        if (shoulFulfill) {
                        log("IExecutor ["+id+"] which will fullfill started..... timeout: " + time + " ms");
                        new Timer() {
                            @Override
                            public void run() {
                                log("resolveCallback ["+id+"] " + data);
                                fulfill.exec(data);
                            }
                        }.schedule(time);
                    } else {
                        log("IExecutor ["+id+"] which will fail started..... timeout: " + time + " ms");
                        new Timer() {
                            @Override
                            public void run() {
                                log("rejectCallback ["+id+"] " + data);
                                reject.exec(data);
//                                throw new NullPointerException("testThrow");
                            }
                        }.schedule(time);
                    }
                    }
                }

        );
    }

    private void reportCatch(Object report) {
        log("C.A.T.C.H: ", report);
    }

    private void reportSuccess(Object report) {
        log("S.U.C.C.E.S.S: ", report);
    }

    private void reportReject(Object report) {
        log("R.E.J.E.C.T: ", report);
    }

    @JsMethod(namespace = "console")
    public static native void log(Object... object);
    @JsMethod(namespace = "console")
    public static native void info(Object... object);
}