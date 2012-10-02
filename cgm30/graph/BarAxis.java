package cgm30.graph;

import cgm30.misc.Pair;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.FontMetrics;


/**
 ** Assumes will always be x-axis
**/
public class BarAxis extends Axis {
	private ArrayList<String> labels;
	private int index;
	
	public BarAxis(String lab) {
		super(lab);
		labels = new ArrayList<String>();
		index = 0;
	}
	
	public void draw(Graph screen, Graphics2D g2d, int xMargin, int yMargin, boolean axis, boolean gridlines) throws GraphTooSmall {
		final int WIDTH = screen.getWidth()-10;
		final int HEIGHT = screen.getHeight()-10;
		final int GRAD = (WIDTH-yMargin)/labels.size();
		
		// Draw axis and main label
		g2d.setFont(getMainLabelFont());
		FontMetrics metrics = g2d.getFontMetrics(getMainLabelFont());
		g2d.setColor(getColour());
		g2d.drawLine(yMargin-5, HEIGHT-xMargin+10, WIDTH, HEIGHT-xMargin+10);
		g2d.drawString(getLabel(), yMargin+(WIDTH-yMargin-metrics.stringWidth(getLabel()))/2, HEIGHT+metrics.getHeight()-(xMargin/2));
		
		// Draw labels and graduations
		g2d.setFont(getLabelFont());
		metrics = g2d.getFontMetrics(getLabelFont());
		
		for (int i = 0; i < labels.size(); ++i) {
			String s = labels.get(i);
			int count = 0;
			while (metrics.stringWidth(s.substring(0, s.length())) > GRAD) {
				int length = s.length();
				
				// Keep decreasing length of string until it fits
				while (metrics.stringWidth(s.substring(0, length)) > GRAD) {
					length--;
				}
				
				// Stay in an infinite loop if don't exit when length <= 0
				length--;
				if (length <= 0)
					throw new GraphTooSmall(axis);
				
				// Draw the part that fits
				g2d.drawString(s.substring(0, length) + "-", yMargin+1+(i*GRAD), HEIGHT-xMargin+(count*metrics.getHeight())+10+metrics.getHeight());
				s = s.substring(length, s.length());
				count++;
			}
			count++;
			g2d.drawString(s, yMargin+1+(i*GRAD)+((GRAD-metrics.stringWidth(s))/2), HEIGHT-xMargin+(count*metrics.getHeight())+10);
			
			if (gridlines)
				g2d.drawLine(yMargin+(i*GRAD), screen.getTopMargin(), yMargin+(i*GRAD), HEIGHT-xMargin+15);
			else
				g2d.drawLine(yMargin+(i*GRAD), HEIGHT-xMargin+5, yMargin+(i*GRAD), HEIGHT-xMargin+15);
		}
		
		if (gridlines)
			g2d.drawLine(yMargin+(labels.size()*GRAD), screen.getTopMargin(), yMargin+(labels.size()*GRAD), HEIGHT-xMargin+15);
		else
			g2d.drawLine(yMargin+(labels.size()*GRAD), HEIGHT-xMargin+5, yMargin+(labels.size()*GRAD), HEIGHT-xMargin+15);
	}
	
	public void drawPlots(ArrayList<BarPlot> plots, Graph screen, Graphics2D g2d, RealAxis other, int xMargin, int yMargin) {
		final int WIDTH = screen.getWidth()-10;
		final int HEIGHT = screen.getHeight()-10;
		final int GRAD = (WIDTH-yMargin)/labels.size();
		final double PPU = other.getPixelsPerUnit(HEIGHT-xMargin-screen.getTopMargin());
		
		for (int i = 0; i < labels.size(); ++i) {
			int prevHeight = 0;
			for (int k = 0; k < plots.size(); ++k) {
				try {
					int barHeight = (int)Math.ceil(plots.get(k).getHeight(labels.get(i))*PPU);
					g2d.setColor(plots.get(k).getColour(labels.get(i)));
					g2d.fillRect(yMargin+4+(i*GRAD), HEIGHT+10-xMargin-barHeight-prevHeight, GRAD-8, barHeight);
					prevHeight += barHeight;
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public void drawLine(BarLinePlot line, Graph screen, Graphics2D g2d, RealAxis other, int xMargin, int yMargin) {
		final int WIDTH = screen.getWidth()-10;
		final int HEIGHT = screen.getHeight()-10;
		final int GRAD = (WIDTH-yMargin)/labels.size();
		final double PPU = other.getPixelsPerUnit(HEIGHT-xMargin-screen.getTopMargin());
		
		int k = 0;
		double h = 0;
		while (k < labels.size()) {
			try {
				h = line.getHeight(labels.get(k));
				break;
			} catch (Exception e) {}
			k++;
		}
		if (k == labels.size())
			return;
		
		Pair<Integer, Integer> prev = new Pair<Integer, Integer>(yMargin+(GRAD/2), HEIGHT-xMargin+10-(int)(h*PPU));
		g2d.setColor(line.getColour());
		
		for (int i = k+1; i < labels.size(); ++i) {
			try {
				int next_y = HEIGHT-xMargin+10-(int)(line.getHeight(labels.get(i))*PPU);
				line.draw(g2d, prev.first, prev.second, yMargin+(i*GRAD)+(GRAD/2), next_y);
				prev.set(yMargin+(i*GRAD)+(GRAD/2), next_y);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public boolean addLabel(String lab) {
		if (!labels.contains(lab)) {
			labels.add(lab);
			return true;
		}
		return false;
	}
	
	public void startIteration() {
		index = 0;
	}
	
	public boolean hasNext() {
		return index < labels.size();
	}
	
	public String getNext() throws IndexOutOfBoundsException {
		String s = labels.get(index);
		index++;
		return s;
	}
}
