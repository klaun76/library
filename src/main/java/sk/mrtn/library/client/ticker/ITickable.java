package sk.mrtn.library.client.ticker;

/**
 * Created by martinliptak on 05/09/16.
 * Represents object that can tick when added to {@link sk.mrtn.library.client.ticker.ITicker}
 */
public interface ITickable {

    /**
     * method invoked by ticker on each tick
     * @param ticker
     */
    void update(ITicker ticker);

    /**
     * Information provided for ticker if object should be ticked
     * on next frame
     * @return
     */
    boolean shouldTick();
}
