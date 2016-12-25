package sk.mrtn.library.client.ui;

import javax.inject.Inject;

/**
 * Created by martinliptak on 25/12/2016.
 * Object should calculate rectangle for fixed ratio sized
 * Rectangle (graphic object). For correct calculations,
 * 4 fields must be specified:
 *
 * #maxWidth, #maxHeight in absolute units to set max overscale
 * #minAspectRatio, #maxAspectRation in relative units. min/max
 * should allow to have variable aspect ratio in specified range.
 *
 * I did not want to create another rectangle object, so after calculation
 * object returns itself, with getters as in regular rectangle
 */
public class StretchCalculator {

    public static double FOUR_THIRD_SCREEN = 4/3.0;
    public static double WIDE_SCREEN = 16/9.0;

    private Double maxWidth;
    private Double maxHeight;
    private Double minAspectRatio;
    private Double maxAspectRatio;

    private double width;
    private double height;
    private double x;
    private double y;

    @Inject
    StretchCalculator(){}

    public StretchCalculator setComputedObjectSize(double maxWidth, double maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        return this;
    }
    /**
     * sets aspect ratio for calculations
     * @param minAspectRatio
     * @param maxAspectRatio
     * @return
     */
    public StretchCalculator aspectRatio(double minAspectRatio, double maxAspectRatio) {
        this.minAspectRatio = minAspectRatio;
        this.maxAspectRatio = maxAspectRatio;
        return this;
    }

    /**
     * sets aspect ration for calculations (min == max)
     * @param aspectRatio
     * @return
     */
    public StretchCalculator aspectRatio(double aspectRatio) {
        this.minAspectRatio = aspectRatio;
        this.maxAspectRatio = aspectRatio;
        return this;
    }

    public StretchCalculator calculate(double width, double height) {
        if (this.minAspectRatio == null || this.maxAspectRatio == null || this.maxWidth == null || this.maxHeight == null) {
            String message = "";
            if (this.minAspectRatio == null) message += "minAspectRatio ";
            if (this.maxAspectRatio == null) message += "maxAspectRatio ";
            if (this.maxWidth == null) message += "maxWidth ";
            if (this.maxHeight == null) message += "maxHeight ";
            throw new NullPointerException("Parameters : " + message + " not specified for" + this.getClass().getSimpleName()+".");
        }

        double calculatedWidth = width;
        double calculatedHeight = width / this.maxAspectRatio;

        double deltaY = calculatedHeight - height;
        if (deltaY > 1) {
            calculatedHeight = height;
            calculatedWidth = calculatedHeight * this.maxAspectRatio;
        } else if (deltaY < 1) {
            calculatedHeight = calculatedWidth / this.minAspectRatio;
            if (calculatedHeight > height) calculatedHeight = height;
        }

        double rWidth = Math.round(calculatedWidth);
        double rHeight = Math.round(calculatedHeight);

        if (rWidth > this.maxWidth) {
            rWidth = this.maxWidth;
            rHeight = this.maxHeight;
        }

        this.width = rWidth;
        this.height = rHeight;
        this.x = (width-rWidth)/2;
        this.y = (height-rHeight)/2;
        return this;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
