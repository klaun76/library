package sk.mrtn.library.client.ticker;

import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.Timer;
import jsinterop.annotations.JsFunction;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import sk.mrtn.library.client.utils.stats.Stats;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by martinliptak on 05/09/16.
 */
public class Ticker implements ITicker {

    private final ITick tick;
    private Stats stats;
    private StatsTimer statsTimer;

    private static class StatsTimer extends Timer {

        private Stats stats;

        public StatsTimer(final Stats stats) {
            this.stats = stats;
        }

        @Override
        public void run() {
            this.stats.update();
        }

    }

    @FunctionalInterface
    @JsFunction
    interface ITick {
        void call();
    }

    protected static Logger LOG;
    static {
        if (LogConfiguration.loggingIsEnabled()) {
            LOG = Logger.getLogger(Ticker.class.getSimpleName());
            LOG.setLevel(Level.ALL);
        }
    }

    private State state;
    /**
     * field is package protected so {@link sk.mrtn.library.client.ticker.TickableRegistration}
     * can access method
     */
    List<TickableRegistration> tickables;
    private Date tickerStart;
    private double currentTick;
    private double previousTick;
    private double deltaTick;
    private Date tickerPauseStart;
    private double tickerPausedMS;

    private Integer requestId;
    private boolean tickRequested;
    private boolean ticking;

    @Inject
    protected Ticker(){
        reset();
        this.tickables = new ArrayList<>();
        this.tick = new ITick() {
            @Override
            public void call() {
                onTick();
            }
        };
    }

    @Override
    public void setStats(final Stats stats) {
        this.stats = stats;
        if (this.stats != null) {
            this.statsTimer = new StatsTimer(stats);
            this.statsTimer.scheduleRepeating(1000);
            this.stats.update();
        }
    }

    @Override
    public ITickableRegistration addTickable(ITickable tickable) {
        if (!this.ticking) {
            this.currentTick = getCurrentTick();
            this.previousTick = this.currentTick;
        }
        TickableRegistration tickableRegistration = new TickableRegistration(this, tickable);
        this.tickables.add(tickableRegistration);
        requestAnimationFrame();
        return tickableRegistration;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public void pause() {
        if (this.state == State.PAUSED) {
            return;
        }
        this.state = State.PAUSED;
        this.tickerPauseStart = new Date();
        if (this.requestId != null) {
            cancelAnimationFrame(this.requestId);
            this.requestId = null;
        }
        this.previousTick = this.currentTick;
        updateDeltaTick();
    }

    @Override
    public void start() {
        switch (state) {
            case STOPPED:
                reset();
                break;
            case PAUSED:
                this.tickerPausedMS += new Date().getTime() - this.tickerPauseStart.getTime();
                break;
            case RUNNING:
                return;
        }
        this.state = State.RUNNING;
        this.currentTick = getCurrentTick();
        this.previousTick = this.currentTick;
        updateDeltaTick();
        if (!this.ticking) {
            this.tickRequested = false;
            this.requestId = requestAnimationFrame(this.tick);
        }
        onTick();
    }

    @Override
    public void stop() {
        if (this.state == State.STOPPED) {
            return;
        }
        this.state = State.STOPPED;
        if (this.requestId != null) {
            cancelAnimationFrame(this.requestId);
            this.requestId = null;
        }
        reset();
        updateDeltaTick();
    }

    @Override
    public double getElapsedMS() {
        return getCurrentTick() - this.tickerPausedMS;
    }

    @Override
    public double getDeltaTick() {
        return this.deltaTick;
    }

    private void reset() {
        this.state = State.STOPPED;
        this.tickerStart = new Date();
        this.currentTick = getCurrentTick();
        this.previousTick = this.currentTick;
        this.tickerPausedMS = 0;
    }

    /**
     * method is package protected so {@link sk.mrtn.library.client.ticker.TickableRegistration}
     * can access method
     */
    void requestAnimationFrame() {
        if (this.state != State.RUNNING) {
            return;
        }
        if (this.ticking) {
            return;
        }
        this.ticking = true;
        this.tickRequested = false;
        this.requestId = requestAnimationFrame(this.tick);
    }

    private void onTick() {
        new Timer() {
            @Override
            public void run() {
                doTick();
            }
        }.schedule(0);
    }

    private void doTick() {
        if (this.state == State.RUNNING) {
            if (this.stats != null) {
                this.stats.begin();
            }
            this.currentTick = getCurrentTick();
            updateDeltaTick();
            for (TickableRegistration tickableRegistration : new ArrayList<>(this.tickables)) {
                ITickable tickable = tickableRegistration.getTickable();
                tickable.update(this);
                this.tickRequested = this.tickRequested || tickable.shouldTick();
            }
            this.previousTick = this.currentTick;
            if (this.stats != null) {
                this.stats.end();
            }
            if (shouldTick()) {
                this.tickRequested = false;
                this.requestId = requestAnimationFrame(this.tick);
            } else {
                updateDeltaTick();
                this.ticking = false;
            }
        }

    }

    @Override
    public void requestTick() {
        onTick();
    }

    private boolean shouldTick() {
        return this.tickRequested && this.state == State.RUNNING;
    }

    private double getCurrentTick() {
        final long now = new Date().getTime() - this.tickerStart.getTime();
        return now;
    }

    public void updateDeltaTick() {
        this.deltaTick = this.currentTick - this.previousTick;
    }

    @JsMethod(namespace = JsPackage.GLOBAL)
    private static native int requestAnimationFrame(Object object);

    @JsMethod(namespace = JsPackage.GLOBAL)
    private static native void cancelAnimationFrame(int id);

}
