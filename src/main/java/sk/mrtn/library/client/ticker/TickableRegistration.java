package sk.mrtn.library.client.ticker;

/**
 * Created by martinliptak on 05/09/16.
 */
class TickableRegistration implements ITickableRegistration {

    private ITickable tickable;
    private Ticker ticker;

    public TickableRegistration(final Ticker ticker, final ITickable tickable) {
        this.tickable = tickable;
        this.ticker = ticker;
    }

    public ITickable getTickable() {
        return this.tickable;
    }

    @Override
    public void removeFromTicker() {
        this.ticker.tickables.remove(this);
    }

    @Override
    public void requestAnimationFrame() {
        this.ticker.requestAnimationFrame();
    }

}