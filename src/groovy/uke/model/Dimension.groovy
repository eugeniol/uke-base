package uke.model

/**
 * Created with IntelliJ IDEA.
 * User: elattanzio
 * Date: 03/07/12
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
@grails.validation.Validateable
class Dimension {
    int width
    int height

    static           constraints = {

    }

    transient double ratio

    double getRatio() {
        if (width > 0)
            height / width
        else
            0
    }

    Dimension resize(int w, int h) {
        if (w && h)
            return getScaledDimension(this, new Dimension(width: w, height: h))
        else if (w)
            resize(new Dimension(width: w, height: Math.floor(getRatio() * w)))
        else if (h)
            resize(new Dimension(width: Math.floor(h * getRatio()), height: h))
        else
            this
    }

    Dimension resize(Dimension square) {
        if (getRatio() <= 1)
            new Dimension(width: square.width, height: Math.floor(getRatio() * square.width))
        else
            new Dimension(width: Math.floor(square.height * getRatio()), height: square.height)
    }

    public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

        int original_width = imgSize.width;
        int original_height = imgSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(width: new_width, height: new_height);
    }
}
