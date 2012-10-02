package cgm30.graph;

public class GraphTooSmall extends Exception {
	private boolean axis;
	
	public GraphTooSmall(boolean ax) {
		super();
		axis = ax;
	}
	
	public boolean getAxis() {
		return axis;
	}
}
