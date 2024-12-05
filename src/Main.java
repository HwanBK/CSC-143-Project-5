/**
 * Demo for program.
 *
 * @author Bill Barry
 * @version 05.30.2022
 */
public class Main {

    /**
     * main
     *
     * @param args N/A
     */
    public static void main(String[] args) {
        FractalGenerator fGen = new FractalGenerator();
        FractalGui gui = new FractalGui(fGen);
        FractalDrawing draw = new FractalDrawing(fGen);
    }
}
