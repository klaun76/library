package sk.mrtn.library.client.tweenengine.equations;

import sk.mrtn.library.client.tweenengine.TweenEquation;

/**
 * Easing equation based on Robert Penner's work:
 * http://robertpenner.com/easing/
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public abstract class Linear extends TweenEquation {
	public static final Linear INOUT = new Linear() {
		@Override
		public float compute(float t) {
			return t;
		}

		@Override
		public String toString() {
			return "Linear.INOUT";
		}
	};
}
