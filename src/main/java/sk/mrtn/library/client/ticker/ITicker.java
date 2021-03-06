package sk.mrtn.library.client.ticker;

import sk.mrtn.library.client.utils.stats.Stats;

/**
 * Created by martinliptak on 05/09/16.
 */
public interface ITicker {


    Ticker.State getState();

    void setStats(Stats stats);

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

    void requestTick();

    enum State {
        STOPPED,PAUSED,RUNNING
    }
}
