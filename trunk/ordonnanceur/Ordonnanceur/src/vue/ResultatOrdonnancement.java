package vue;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ResultatOrdonnancement extends JFrame{
    public static void main(String[] args) {
    
    	ResultatOrdonnancement inst = new ResultatOrdonnancement();
    }
    	public ResultatOrdonnancement(){
    	JPanel p = new JPanel();

        //         Create a simple XY chart
        XYSeries series = new XYSeries("XYGraph");
        series.add(1, 1);
        series.add(1, 2);
        series.add(2, 1);
        series.add(3, 9);
        series.add(4, 10);
        //         Add the series to your data set
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        //         Generate the graph
        JFreeChart chart = ChartFactory.createXYLineChart("XY Chart", // Title
                "Temps", // x-axis Label
                "Taches", // y-axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
            );
        
        ChartPanel chartPanel = new ChartPanel(chart, true);
        p.add(chartPanel);
        this.getContentPane().add(p);

        this.setVisible(true);

      
    }
}
