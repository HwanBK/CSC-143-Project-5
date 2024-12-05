import java.awt.*;

/**
 * Class for drawing a bubble's reflection arc
 *
 * @author Hwansu Kim (Billy)
 * @version 06.16.2022
 */
public class Arc implements FractalElement {

    /** the starting angle of the arc */
    private static final int START_ANGLE = 40;
    /** the arc angle */
    private static final int ARC_ANGLE = 10;
    /** the color of the arc */
    private static final Color arcColor = Color.LIGHT_GRAY;

    /** the starting x-coordinate */
    private int startX;
    /** the starting y-coordinate */
    private int startY;
    /** the width of the arc */
    private int width;
    /** the height of the arc */
    private int height;
    /** the stroke width of the arc */
    private int strokeWidth;

    /**
     * Class constructor.
     *
     * @param xDim the starting x-coordinate.
     * @param yDim the starting y-coordinate.
     * @param aWidth the width of the arc.
     * @param aHeight the height of the arc.
     * @param strWidth the stroke width of the arc.
     */
    public Arc(int xDim, int yDim, int aWidth, int aHeight, int strWidth) {
        this.startX = xDim;
        this.startY = yDim;
        this.width = aWidth;
        this.height = aHeight;
        this.strokeWidth = strWidth;
    }

    @Override
    public void draw(Graphics g) {
        int red = arcColor.getRed();
        int green = arcColor.getGreen();
        int blue = arcColor.getBlue();
        g.setColor(new Color(red, green, blue, 102));
        ((Graphics2D) g).setStroke(new BasicStroke(strokeWidth));
        g.drawArc(startX, startY, width, height, START_ANGLE, ARC_ANGLE);
    }
}
