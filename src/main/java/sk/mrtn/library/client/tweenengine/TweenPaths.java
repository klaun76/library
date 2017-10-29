package sk.mrtn.library.client.tweenengine;

import sk.mrtn.library.client.tweenengine.paths.CatmullRom;
import sk.mrtn.library.client.tweenengine.paths.Linear;

/**
 * Collection of built-in paths.
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public interface TweenPaths {
	public static final Linear linear = new Linear();
	public static final CatmullRom catmullRom = new CatmullRom();
}
