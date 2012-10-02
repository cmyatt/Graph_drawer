package cgm30.graph;

import java.awt.Color;
import java.util.HashMap;

public class RealPlot implements Plot {
	private HashMap<Double, Double> coords;
	private Color colour;
	
	public RealPlot(Color c) {
		coords = new HashMap<Double, Double>();
		colour = (c!=null)? c : Color.BLACK;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public boolean setColour(Color c) {
		colour = (c!=null)? c : colour;
		return c!=null;
	}
	
	/**
	 ** Provide methods to iterate over (x,y) pairs in coords
	 ** and return them for plotting.
	**/
}
