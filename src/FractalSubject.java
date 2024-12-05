import java.awt.*;
import java.util.ArrayList;

/**
 * Interface for FractalSubjects
 *
 * @author Hwansu Kim (Billy) and Bill Barry
 * @version 05.30.2022
 */
public interface FractalSubject {
    /**
     * Method for attaching new observers
     *
     * @param observer an observer to be subscribed.
     */
    public void attach(FractalObserver observer);

    /**
     * Method for detaching subscribed observer
     *
     * @param observer an observer to be unsubscribed.
     */
    public void detach(FractalObserver observer);

    /**
     * Method for notifying and potentially triggering all subscribed observers
     */
    public void notifyObservers();

    /**
     * Method for retrieving an arraylist of FractalElements
     *
     * @return an arraylist of FractalElement objects
     */
    public ArrayList<FractalElement> getData();

    /**
     * Method for setting generator parameters after retrieving values from user interaction
     *
     * @param depth the total recursion depth.
     * @param ratio the ratio of parent to child drawings.
     * @param count the count of children per parent.
     * @param bedlam the bedlam level modifier.
     * @param curColor the color of the drawing.
     * @param isPastel pastel flag of the drawing.
     */
    public void setParams(int depth, double ratio, int count, int bedlam, Color curColor, boolean isPastel);
}
