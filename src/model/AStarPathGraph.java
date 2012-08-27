package model;

import java.util.List;

public class AStarPathGraph extends ShortestPathGraph {
	
	/**
	 * Provides an heuristic estimation of the path length between from and to.
	 * 
	 * @param a the node from the estimation is started
	 * @param b the node where the estimation is done
	 * 
	 * @return the estimated value of the path length.
	 * */
	private double estimate(Node a, Node b) {
		return -1;
	}

	@Override
	public double distance(Node from, Node to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Node> path(Node from, Node to) {
		// TODO Auto-generated method stub
		return null;
	}

}
