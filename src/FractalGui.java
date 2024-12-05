import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates a GUI with functioning widgets for user interaction and value establishment
 *
 * @author Hwansu Kim (Billy) and Bill Barry
 * @version 06.16.2022
 */
public class FractalGui extends JFrame {

    /** the subject to be observed */
    private FractalSubject subject;
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
     * Class constructor; creates GUI and sends values to subject.
     *
     * @param subject the subject to be observed.
     */
    public FractalGui(FractalSubject subject) {
        this.subject = subject;

        // Frame //
        setTitle("Fractal Settings");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Toolkit toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width - getWidth()) / 5, (screenSize.height - getHeight()) / 2);

        // Panel //
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        // Draw Button (Redundant due to real-time updates)//
        //JButton drawButton = new JButton("Draw");
        //drawButton.setBounds(20, 500, 80, 30);
        //mainPanel.add(drawButton);
        //drawButton.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
        //    }
        //});

        // Recursion Depth Slider //
        JLabel depthLabel = new JLabel("Recursion Depth");
        depthLabel.setBounds(30, 30, 100, 20);
        mainPanel.add(depthLabel);

        JSlider depthSlider = new JSlider(1, 8);
        depthSlider.setBounds(20, 50, 340, 50);

        depthSlider.setPaintTrack(true);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);
        depthSlider.setMajorTickSpacing(1);

        mainPanel.add(depthSlider);
        recursionDepth = depthSlider.getValue();
        depthSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                recursionDepth = depthSlider.getValue();
                subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
            }
        });


        // Child to Parent Ratio Slider //
        JLabel ratioLabel = new JLabel("Child to Parent Ratio");
        ratioLabel.setBounds(30, 130, 200, 20);
        mainPanel.add(ratioLabel);

        JSlider ratioSlider = new JSlider(20, 70);
        ratioSlider.setBounds(20, 150, 340, 50);

        ratioSlider.setPaintTrack(true);
        ratioSlider.setPaintTicks(true);
        ratioSlider.setPaintLabels(true);
        ratioSlider.setMajorTickSpacing(10);
        ratioSlider.setMinorTickSpacing(5);
        setVisible(true);

        mainPanel.add(ratioSlider);
        childRatio = ratioSlider.getValue() / 100.0;
        ratioSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                childRatio = ratioSlider.getValue() / 100.0;
                subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
            }
        });


        // Child Count Slider //
        JLabel countLabel = new JLabel("Child Count");
        countLabel.setBounds(30, 230, 200, 20);
        mainPanel.add(countLabel);

        JSlider countSlider = new JSlider(1, 11);
        countSlider.setBounds(20, 250, 340, 50);


        countSlider.setPaintTrack(true);
        countSlider.setPaintTicks(true);
        countSlider.setPaintLabels(true);
        countSlider.setMajorTickSpacing(2);
        countSlider.setMinorTickSpacing(1);

        mainPanel.add(countSlider);
        childCount = countSlider.getValue();
        countSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                childCount = countSlider.getValue();
                subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
            }
        });


        // Bedlam Level Slider (NOT COMPLETE)//
        JLabel bedlamLabel = new JLabel("Bedlam Level (WIP)");
        bedlamLabel.setBounds(30, 330, 200, 20);
        mainPanel.add(bedlamLabel);

        JSlider bedlamSlider = new JSlider(0, 4);
        bedlamSlider.setBounds(20, 350, 340, 50);

        bedlamSlider.setPaintTrack(true);
        bedlamSlider.setPaintTicks(true);
        bedlamSlider.setPaintLabels(true);
        bedlamSlider.setMajorTickSpacing(1);

        mainPanel.add(bedlamSlider);
        bedlamLevel = bedlamSlider.getValue();
        bedlamSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                bedlamLevel = bedlamSlider.getValue();
                subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
            }
        });


        // Color Button //
        currentColor = Color.BLUE;
        JButton colorButton = new JButton("Fractal Color");
        colorButton.setBounds(20, 410, 200, 20);

        mainPanel.add(colorButton);

        JViewport colorView = new JViewport();
        colorView.setBounds(20, 431, 200, 20);
        colorView.setBackground(currentColor);
        mainPanel.add(colorView);

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color lastColor = currentColor;
                currentColor = JColorChooser.showDialog(null, "Select a Color", Color.BLUE);
                if (currentColor == null) {
                    currentColor = lastColor;
                }
                colorView.setBackground(currentColor);
                subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
            }
        });

        JCheckBox randomCheck = new JCheckBox("Random Pastels");
        randomCheck.setBounds(20, 452, 150, 20);
        mainPanel.add(randomCheck);
        isPastel = randomCheck.isSelected();

        randomCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPastel = randomCheck.isSelected();
                subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);
            }
        });

        subject.setParams(recursionDepth, childRatio, childCount, bedlamLevel, currentColor, isPastel);

        setVisible(true);
    }
}