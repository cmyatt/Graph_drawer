import graph.*;
import java.awt.Color;
import javax.swing.JFrame;

public class GraphTest extends JFrame {
	private BarGraph g;
	
	public static void main(String[] args) {
		GraphTest test = new GraphTest();
	}
	
	public GraphTest() {
		super("Graph Test");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BarAxis bar = new BarAxis("Week");
		bar.setColour(new Color(80, 80, 80));
		bar.addLabel("One");
		bar.addLabel("Two");
		bar.addLabel("Three");
		bar.addLabel("Four");
		bar.addLabel("Five");
		bar.addLabel("Six");
		bar.addLabel("Seven");
		bar.addLabel("Eight");
		bar.addLabel("Nine");
		bar.addLabel("Ten");
		
		RealAxis real = new RealAxis("Expenditure / £", 0, 100, 10);
		real.setColour(bar.getColour());
		
		BarPlot plot1 = new BarPlot();
		plot1.addBar("One", 10, Color.GREEN);
		plot1.addBar("Two", 20, Color.BLUE);
		plot1.addBar("Three", 30, Color.GREEN);
		plot1.addBar("Four", 40, Color.BLUE);
		plot1.addBar("Five", 50, Color.GREEN);
		plot1.addBar("Six", 60, Color.BLUE);
		plot1.addBar("Seven",70, Color.GREEN);
		plot1.addBar("Eight", 80, Color.BLUE);
		plot1.addBar("Nine", 90, Color.GREEN);
		plot1.addBar("Ten", 100, Color.BLUE);
		
		BarPlot plot2 = new BarPlot();
		plot2.addBar("Two", 30, Color.ORANGE);
		plot2.addBar("Four", 10, Color.ORANGE);
		plot2.addBar("Six", 12, Color.ORANGE);
		
		BarPlot plot3 = new BarPlot();
		plot3.addBar("Two", 25, Color.PINK);
		plot3.addBar("Six", 14, Color.PINK);
		
		BarLinePlot line1 = new BarLinePlot(Color.RED);
		line1.addPoint("One", 40);
		line1.addPoint("Five", 60);
		line1.addPoint("Ten", 50);
		line1.setThickness(2);
		
		g = new BarGraph("Test", bar, real, -1, -1);
		g.addPlot(plot1);
		g.addPlot(plot2);
		g.addPlot(plot3);
		g.addLine(line1);
		
		g.setXMargin(70);
		g.setYMargin(75);
		g.setTopMargin(40);
		
		g.setHorizontalGridlines(true);
		g.setVerticalGridlines(false);
		
		getContentPane().add(g);
		setSize(640, 480);
		setVisible(true);
	}

}