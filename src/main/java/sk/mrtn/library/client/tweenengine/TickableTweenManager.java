package sk.mrtn.library.client.tweenengine;

import sk.mrtn.library.client.ticker.ITickable;
import sk.mrtn.library.client.ticker.ITickableRegistration;
import sk.mrtn.library.client.ticker.ITicker;

import javax.inject.Inject;
import java.util.logging.Logger;

public class TickableTweenManager extends TweenManager implements ITickable {

    protected double timeDilation = 1.0;
    private ITicker ticker;
    private ITickableRegistration tickableRegistration;

    @Inject
    public TickableTweenManager(
            final ITicker ticker
    ) {
        this.ticker = ticker;
    }

    @Override
    public void update(ITicker ticker) {
        super.update((float) (ticker.getDeltaTick() / 1000.0 * timeDilation));
        boolean tweensActive = false;
        for (BaseTween<?> baseTween : getObjects()) {
            if (baseTween.isStarted() && !baseTween.isFinished()) {
                tweensActive = true;
                break;
            }
        }
        if (!tweensActive) {
            pause();
            this.tickableRegistration.removeFromTicker();
            this.tickableRegistration = null;
            Logger.getLogger(TickableTweenManager.class.getSimpleName()).severe("removing");
        }
    }

    @Override
    public boolean shouldTick() {
        return true;
    }

    public void start() {
        if (this.ticker.getState() != ITicker.State.RUNNING) {
            this.ticker.start();
        }
        if (this.tickableRegistration != null) {
            return;
        }

        resume();
        Logger.getLogger(TickableTweenManager.class.getSimpleName()).severe("Adding");
        if (this.tickableRegistration == null) {
            this.tickableRegistration = this.ticker.addTickable(this);
        }

        this.tickableRegistration.requestAnimationFrame();
    }
}
