import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Class for creating the drawing space.
 *
 * @author Hwansu Kim (Billy) and Bill Barry
 * @version 06.16.2022
 */
public class FractalDrawing extends JFrame implements FractalObserver {

    /** the subject to observe */
    private FractalSubject subject;
    /** the drawing space */
    private JPanel drawPanel;
    /** arraylist of objects to be drawn */
    private ArrayList<FractalElement> elements;

    /**
     * Class constructor; calls update() on instantiation.
     *
     * @param subject the subject to observe.
     */
    public FractalDrawing(FractalSubject subject) {
        this.subject = subject;
        subject.attach(this);

        setTitle("Fractal");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Toolkit toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);

        drawPanel = new DrawingArea();
        drawPanel.setLayout(null);
        drawPanel.setBackground(Color.BLACK);
        getContentPane().add(drawPanel);

        elements = new ArrayList<>();
        this.update();

        setVisible(true);
    }


    @Override
    public void update() {
        elements = subject.getData();
        drawPanel.repaint();
    }

    /**
     * Inner-class for painting onto the drawing space when triggered.
     */
    private class DrawingArea extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (FractalElement eachElement : elements) {
                eachElement.draw(g);
            }
        }
    }
}
