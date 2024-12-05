import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for drawing a bubble.
 *
 * @author Hwansu Kim (Billy)
 * @version 06.16.2022
 */
public class Bubble implements FractalElement {

    /** the starting x-coordinate */
    private int startX;
    /** the starting y-coordinate */
    private int startY;
    /** the width of the bubble */
    private int width;
    /** the height of the bubble */
    private int height;
    /** the color of the bubble */
    private Color bubbleColor;
    /** pastel flag */
    private boolean isPastel;

    /**
     * Class constructor.
     *
     * @param xDim the starting x-coordinate.
     * @param yDim the starting y-coordinate.
     * @param bWidth the width of the bubble.
     * @param bHeight the height of the bubble.
     * @param bColor the color of the bubble.
     * @param isRandPastel pastel flag.
     */
    public Bubble(int xDim, int yDim, int bWidth, int bHeight, Color bColor, boolean isRandPastel) {
        this.startX = xDim;
        this.startY = yDim;
        this.width = bWidth;
        this.height = bHeight;
        this.bubbleColor = bColor;
        this.isPastel = isRandPastel;
    }

    @Override
    public void draw(Graphics g) {
        Color transparent;
        Color outlineColor = bubbleColor;
        if (isPastel) {
            transparent = pastelColors();
        } else {
            int red = bubbleColor.getRed();
            int green = bubbleColor.getGreen();
            int blue = bubbleColor.getBlue();
            transparent = new Color(red, green, blue, 102);
        }
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        g.setColor(transparent);
        g.fillOval(startX, startY, width, height);
        if (isPastel) {
            int red = transparent.getRed();
            int green = transparent.getGreen();
            int blue = transparent.getBlue();
            outlineColor = new Color(red, green, blue);
        }
        g.setColor(outlineColor);
        g.drawOval(startX, startY, width, height);
    }

    /**
     * Method for creating an arraylist of specific pastel colors and retrieving one at random.
     *
     * @return a pastel color chosen at random from the arraylist.
     */
    private Color pastelColors() {
        ArrayList<Color> pastelList = new ArrayList<>();
        Random rando = new Random();
        pastelList.add(new Color(255, 255, 176, 102));
        pastelList.add(new Color(148, 168, 208, 102));
        pastelList.add(new Color(221, 212, 240, 102));
        pastelList.add(new Color(251, 182, 209, 102));
        pastelList.add(new Color(255, 223, 211, 102));
        int randNum = rando.nextInt(pastelList.size());
        return pastelList.get(randNum);
    }
}
