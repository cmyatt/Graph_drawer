package cgm30.graph;

import cgm30.misc.Pair;
import java.util.Set;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;


public class BarPlot implements Plot {
	private HashMap<String, Pair<Double, Color>> bars;
	
	public BarPlot() {
		bars = new HashMap<String, Pair<Double, Color>>();
	}
	
	public void addBar(String id, double height, Color c) {
		bars.put(id, new Pair<Double, Color>(height, c));
	}
	
	public double getHeight(String s) throws Exception {
		Pair<Double, Color> p = bars.get(s);
		if (p == null)
			throw new Exception("No bar corresponding to " + s);
		return p.first;
	}
	
	public Color getColour(String s) throws Exception {
		Pair<Double, Color> p = bars.get(s);
		if (p == null)
			throw new Exception("No bar corresponding to " + s);
		return p.second;
	}
}
