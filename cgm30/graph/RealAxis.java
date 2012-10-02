package cgm30.graph;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import java.awt.geom.AffineTransform;


public class RealAxis extends Axis {
	private double start, end, graduation;
	private final int NUM_GRADS;
	
	public RealAxis(String lab, double s, double e, double grad) {
		super(lab);
		start = s;
		end = e;
		graduation = grad;
		NUM_GRADS = (int)(Math.round((end-start)/graduation));
	}
	
	public void draw(Graph screen, Graphics2D g2d, int xMargin, int yMargin, boolean axis, boolean gridlines) throws GraphTooSmall {
		final int WIDTH = screen.getWidth()-10;
		final int HEIGHT = screen.getHeight();
		
		if (axis == HORIZONTAL) {
			
		} else {
			final int GRAD = (int)(getPixelsPerUnit(HEIGHT-xMargin-screen.getTopMargin())*graduation);
			
			// Draw axis and main label
			g2d.setFont(getMainLabelFont());
			FontMetrics metrics = g2d.getFontMetrics(getMainLabelFont());
			g2d.setColor(getColour());
			g2d.drawLine(yMargin, HEIGHT-xMargin, yMargin, screen.getTopMargin());
			
			// Draw rotated label
			AffineTransform prev = g2d.getTransform();
			g2d.rotate(-Math.PI/2, yMargin, HEIGHT+10-xMargin);
			g2d.drawString(getLabel(), xMargin+(HEIGHT-screen.getTopMargin()-xMargin-metrics.stringWidth(getLabel()))/2, HEIGHT-30-yMargin);
			g2d.setTransform(prev);
			
			// Draw graduations and label them
			String label;
			g2d.setFont(getLabelFont());
			metrics = g2d.getFontMetrics(getLabelFont());
			
			for (int i = 0; i <= NUM_GRADS; ++i) {
				if (gridlines)
					g2d.drawLine(yMargin-5, HEIGHT-xMargin-(i*GRAD), WIDTH, HEIGHT-xMargin-(i*GRAD));
				else
					g2d.drawLine(yMargin-5, HEIGHT-xMargin-(i*GRAD), yMargin+5, HEIGHT-xMargin-(i*GRAD));
				
				label = Double.toString(i*graduation);
				int w = metrics.stringWidth(label);
				g2d.drawString(label, yMargin-w-7, HEIGHT-xMargin-(i*GRAD)+(metrics.getHeight()/4)+1);
			}
		}
	}
	
	public double getStart() {
		return start;
	}
	
	public double getEnd() {
		return end;
	}
	
	public double getGraduation() {
		return graduation;
	}
	
	public double getPixelsPerUnit(int range) {
		return ((double)range)/(end-start);
	}
}
