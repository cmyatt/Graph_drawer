package cgm30.graph;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics2D;

public abstract class Axis {
	public static boolean HORIZONTAL = true;
	public static boolean VERTICAL	 = false;
	
	private String label;
	private Color colour;
	private Font mainLabelFont, labelFont;
	
	public abstract void draw(Graph screen, Graphics2D g2d, int xMargin, int yMargin, boolean axis, boolean gridlines) throws GraphTooSmall;
	
	public Axis(String lab) {
		label = lab;
		colour = Color.BLACK;
		mainLabelFont = new Font("Arial", Font.BOLD, 14);
		labelFont = new Font("Arial", Font.PLAIN, 12);
	}
	
	public String getLabel() {
		return label;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public boolean setColour(Color c) {
		colour = (c!=null)? c : colour;
		return c!=null;
	}
	
	public boolean setMainLabelFont(Font f) {
		mainLabelFont = (f != null)? f : mainLabelFont;
		return f != null;
	}
	
	public boolean setLabelFont(Font f) {
		labelFont = (f != null)? f : labelFont;
		return f != null;
	}
	
	public Font getMainLabelFont() {
		return mainLabelFont;
	}
	
	public Font getLabelFont() {
		return labelFont;
	}
}
