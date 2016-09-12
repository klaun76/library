package sk.mrtn.library.client.ticker;

/**
 * Created by martinliptak on 05/09/16.
 * Registration of the {@link sk.mrtn.library.client.ticker.ITickable}
 */
public interface ITickableRegistration {
    /**
     * Removes {@link sk.mrtn.library.client.ticker.ITickable} from {@link sk.mrtn.library.client.ticker.ITicker}.
     */
    void removeFromTicker();

    /**
     * Reqest a tick from {@link sk.mrtn.library.client.ticker.ITicker}.
     */
    void requestAnimationFrame();

}
