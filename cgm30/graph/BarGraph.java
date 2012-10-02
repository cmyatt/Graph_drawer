package cgm30.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import java.awt.geom.AffineTransform;

public class BarGraph extends Graph {
	private BarAxis xAxis;
	private RealAxis yAxis;
	private ArrayList<BarPlot> plots;
	private ArrayList<BarLinePlot> lines;
	
	public BarGraph(String title, BarAxis x, RealAxis y, int width, int height) {
		super(title, width, height);
		xAxis = x;
		yAxis = y;
		plots = new ArrayList<BarPlot>();
		lines = new ArrayList<BarLinePlot>();
	}
	
	public boolean addPlot(BarPlot p) {
		if (p != null) {
			plots.add(p);
			return true;
		}
		return false;
	}
	
	public boolean addLine(BarLinePlot line) {
		if (line != null) {
			lines.add(line);
			return true;
		}
		return false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		// Draw axes
		try {
			xAxis.draw((Graph)this, g2d, getXMargin(), getYMargin(), Axis.HORIZONTAL, showVerticalGridlines());
			yAxis.draw((Graph)this, g2d, getXMargin(), getYMargin(), Axis.VERTICAL, showHorizontalGridlines());
		} catch (GraphTooSmall e) {
			String msg = "Window size too small to display ";
			msg += (e.getAxis() == Axis.HORIZONTAL)? "x-axis.\n" : "y-axis.\n";
			msg += "Please re-size the window accordingly.";
			JOptionPane.showMessageDialog(this, msg, "Error: Window too small", JOptionPane.ERROR_MESSAGE);
			setSize(getInitialWidth(), getInitialHeight());
			repaint();
		}
		
		// Draw plots
		xAxis.drawPlots(plots, this, g2d, yAxis, getXMargin(), getYMargin());
		for (BarLinePlot p : lines) {
			xAxis.drawLine(p, this, g2d, yAxis, getXMargin(), getYMargin());
		}
	}
}
