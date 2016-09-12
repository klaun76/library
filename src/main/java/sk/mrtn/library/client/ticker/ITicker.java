package sk.mrtn.library.client.ticker;

/**
 * Created by martinliptak on 05/09/16.
 */
public interface ITicker {


    Ticker.State getState();

    ITickableRegistration addTickable(ITickable tickable);

    void pause();

    void start();

    void stop();

    /**
     * returns last difference between ticks in milliseconds
     * @return
     */
    double getDeltaTick();

    double getElapsedMS();

    enum State {
        STOPPED,PAUSED,RUNNING
    }
}
