import java.awt.*;
import java.util.ArrayList;

/**
 * Generates FractalElement objects based on values from FractalGui.
 *
 * @author Hwansu Kim (Billy) and Bill Barry
 * @version 06.16.2022
 */
public class FractalGenerator implements FractalSubject {

    /** arraylist of FractalObservers */
    ArrayList<FractalObserver> observers;
    /** the initial parent bubble's radius */
    private static final int START_RADIUS = 100;
    /** the width of the drawing space */
    private static final int PANEL_WIDTH = 784;
    /** the height of the drawing space */
    private static final int PANEL_HEIGHT = 760;

    /** the number of times to recurse */
    private int recursionDepth;
    /** the size ratio of child FractalElements */
    private double childRatio;
    /** the number of children */
    private int childCount;
    /** the bedlam modifier number */
    private int bedlamLevel;
    /** the bubble's color */
    private Color currentColor;
    /** pastel flag */
    private boolean isPastel;

    /**
     * Class constructor.
     */
    public FractalGenerator() {
        observers = new ArrayList<>();
    }

    @Override
    public void attach(FractalObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(FractalObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (FractalObserver obs : observers) {
            obs.update();
        }
    }

    @Override
    public ArrayList<FractalElement> getData() {
        ArrayList<FractalElement> elementList = new ArrayList<>();

        double startPos =  Math.PI / 2.0;
        double posShift = 2.0 * Math.PI / childCount;
        int xCenter = PANEL_WIDTH / 2;
        int yCenter = PANEL_HEIGHT / 2;
        int xPos = xCenter - START_RADIUS;
        int yPos = yCenter - START_RADIUS;
        int arcPos = (int) (START_RADIUS * .75);

        elementList.add(new Bubble(xPos, yPos, 200, 200, currentColor, isPastel));
        elementList.add(new Arc((int) (arcPos + xPos + START_RADIUS * .5),
                        (int) (arcPos + yCenter - START_RADIUS * 2 + START_RADIUS * .5),
                        (int) (START_RADIUS * .5), START_RADIUS, START_RADIUS / 10));

        elementList = getChildData(elementList, xPos + START_RADIUS, yPos + START_RADIUS, START_RADIUS,
                                    0, startPos, posShift);

        return elementList;
    }

    /**
     * Recursion method for generating FractalElement objects using retrieved values.
     *
     * @param list the arraylist of FractalElements to add onto.
     * @param startX the starting x-coordinate of the child bubble to be generated.
     * @param startY the starting y-coordinate of the child bubble to be generated.
     * @param radius the radius of the child bubble's parent.
     * @param recurCount the current number of times of recursion.
     * @param firstPos the cartesian coordinate of the child bubble relative to the parent.
     * @param posChange the increment of the cartesian coordinate.
     *
     * @return the arraylist of FractalElements after having been filled with FractalElement objects.
     */
    private ArrayList<FractalElement> getChildData(ArrayList<FractalElement> list, int startX, int startY, int radius,
                                                   int recurCount, double firstPos, double posChange) {
        if (recurCount != recursionDepth) {
            int newRadius = (int) (radius * childRatio);
            int arcPos = (int) (newRadius * .75);

            for (int num = 0; num < childCount; num++) {
                int newX = startX - (int) (Math.cos(firstPos + (posChange * num)) * (radius + newRadius));
                int newY = startY - (int) (Math.sin(firstPos + (posChange * num)) * (radius + newRadius));

                list.add(new Bubble(newX - newRadius, newY - newRadius, newRadius * 2,
                        newRadius * 2, currentColor, isPastel));

                list.add(new Arc((int) (arcPos + newX - newRadius + newRadius * .5),
                        (int) (arcPos + newY - newRadius * 2 + newRadius * .5), (int) (newRadius * .5), newRadius,
                        newRadius / 10));

                list = getChildData(list, newX, newY, newRadius, recurCount + 1, firstPos, posChange);
            }
        }
        return list;
    }

    @Override
    public void setParams(int depth, double ratio, int count, int bedlam, Color curColor, boolean isRandPastel) {
        recursionDepth = depth;
        childRatio = ratio;
        childCount = count;
        bedlamLevel = bedlam;
        currentColor = curColor;
        isPastel = isRandPastel;

        notifyObservers();
    }
}
