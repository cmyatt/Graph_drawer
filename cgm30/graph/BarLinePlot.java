package cgm30.graph;

import java.awt.Color;
import java.util.HashMap;
import java.awt.Graphics2D;

public class BarLinePlot implements Plot {
	private Color colour;
	private int thickness;
	private HashMap<String, Double> points;
	
	public BarLinePlot(Color c) {
		colour = (c!=null)? c : Color.BLACK;
		thickness = 1;
		points = new HashMap<String, Double>();
	}
	
	/**
	 ** Only add point if label isn't null
	**/
	public boolean addPoint(String label, double height) {
		if (label != null) {
			points.put(label, height);
			return true;
		}
		return false;
	}
	
	public double getHeight(String label) throws Exception {
		if (!points.containsKey(label))
			throw new Exception("No value corresponding to " + label);
		return points.get(label);
	}
	
	public void draw(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		g2d.drawLine(x1, y1, x2, y2);
		int a=1, b=1;
		for (int i=thickness-1; i > 0; --i) {
			if ((i&1) == 1) {
				g2d.drawLine(x1, y1-a, x2, y2-a);
				a++;
			} else {
				g2d.drawLine(x1, y1+b, x2, y2+b);
				b++;
			}
		}
	}
	
	public Color getColour() {
		return colour;
	}
	
	public boolean setThickness(int t) {
		thickness = (t>0)? t : thickness;
		return t>0;
	}
	
	public int getThickness() {
		return thickness;
	}
}
