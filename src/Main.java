import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.UnknownKeyException;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Random;

public class Main {
    JFreeChart chart;
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    public static void main(String[] args) {
        Main mn = new Main();
        mn.start();
    }
    public void start() {
        createChart();
        show();
        testRandomInts(100);
    }
    public void incrementValue(int value) {
        try {
            dataset.incrementValue(1,""+value,"");
        } catch(UnknownKeyException ex) {
            dataset.addValue(1,""+value,"");
        }
    }
    public void createChart() {
        chart = ChartFactory.createBarChart("Random Number Visualizer", "", "",  dataset,
                PlotOrientation.VERTICAL, true, true, true);
    }
    public void show() {
        JFrame frame = new JFrame("Random Number Visualizer");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void testRandomInts(int numberOfRandomNumbers) {
        Random rn = new Random();
        for(int i = 0; i< numberOfRandomNumbers;i++) {
            synchronized (this) {
                try {
                    wait(100); // delay for fun
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            incrementValue(rn.nextInt(100));
        }

    }
}
