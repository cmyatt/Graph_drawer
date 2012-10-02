package cgm30.graph;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.FontMetrics;
import javax.swing.JOptionPane;


public class Graph extends JPanel {
	private String title;
	private Font titleFont;
	private int xMargin, yMargin, topMargin;
	private int initialWidth, initialHeight;
	private boolean horizontalGridlines, verticalGridlines;
	
	public Graph(String t, int width, int height) {
		super(true);
		title = t;
		titleFont = new Font("Arial", Font.BOLD, 18);
		xMargin = yMargin = 50;
		topMargin = 35;
		initialWidth = (width<=0)? 640 : width;
		initialHeight = (height<=0)? 480 : height;
		setSize(initialWidth, initialHeight);
		horizontalGridlines = verticalGridlines = false;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		// Draw title
		FontMetrics metrics = g2d.getFontMetrics(titleFont);
		int width = metrics.stringWidth(title);
		g2d.setFont(titleFont);
		g2d.drawString(title, (getWidth()-width)/2, 25);
	}
	
	public boolean setTitleFont(Font f) {
		titleFont = (f != null)? f : titleFont;
		return f != null;
	}
	
	public boolean setXMargin(int x) {
		xMargin = (x > 0)? x : xMargin;
		return x>0;
	}
	
	public boolean setYMargin(int y) {
		yMargin = (y>0)? y : yMargin;
		return y>0;
	}
	
	public boolean setTopMargin(int t) {
		topMargin = (t>0)? t : topMargin;
		return t>0;
	}
	
	public void setHorizontalGridlines(boolean b) {
		horizontalGridlines = b;
	}
	
	public void setVerticalGridlines(boolean b) {
		verticalGridlines = b;
	}
	
	public int getXMargin() {
		return xMargin;
	}
	
	public int getYMargin() {
		return yMargin;
	}
	
	public int getTopMargin() {
		return topMargin;
	}
	
	public int getInitialWidth() {
		return initialWidth;
	}
	
	public int getInitialHeight() {
		return initialHeight;
	}
	
	public boolean showHorizontalGridlines() {
		return horizontalGridlines;
	}
	
	public boolean showVerticalGridlines() {
		return verticalGridlines;
	}
}
