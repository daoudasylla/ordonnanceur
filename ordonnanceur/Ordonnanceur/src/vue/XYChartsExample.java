package vue;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.*;


public class XYChartsExample extends JDialog{
    public static void main(String[] args) {
    	
    	XYChartsExample inst = new XYChartsExample();
    	inst.setVisible(true);
    }
    
    public XYChartsExample(){
		getContentPane().setLayout(null);
    	this.setSize(319, 199);
    	
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
                "Tache", // x-axis Label
                "Temps", // y-axis Label
                dataset, // Dataset
                PlotOrientation.HORIZONTAL, // Plot Orientation
                true, // Show Legend
                true, // Use tooltips
                false // Configure chart to generate URLs?
            );
        
        ChartPanel chartPanel = new ChartPanel(chart, true);

        Dimension d = new Dimension(350,250);

		chartPanel.setMaximumSize(d); 
		chartPanel.setPreferredSize(d); 
		chartPanel.setMinimumSize(d);

		p.add(chartPanel);
		this.getContentPane().add(p);		

		this.setVisible(true);
       
    }
}
