package model;

import java.util.Collection;
/**
 * Class used only in PerformanceTest, to implement the <em>heuristic 1</em> for trials.
 * **/
public class AStarGraphH1 extends AStarPathGraph {

	
	protected double estimate(Node a, Node b) {
		int networkSize = this.size();
		// calculate number of same nodes
		Collection<Node> neighbors = a.neighbors().keySet();
		Collection<Node> bNeighbors = b.neighbors().keySet();
		neighbors.retainAll( bNeighbors );
		int similarity = networkSize - neighbors.size();
		return similarity; // first heuristic
		// return weight * similarity;
		// return 0; // use this when testing algorithm!
	}
}
