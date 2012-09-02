package test;

import java.util.*;
import model.*;

public class PerformanceTest {
	
	public static Random ran = new Random();
	
	public static List<Node> random(int nodeCount, double edgeProbability) {
		List<Node> nodes = new ArrayList<Node>();
		for(int i = 0; i < nodeCount; i++ ) {
			nodes.add( new Node("" + i ) );
		}
		double prob = 1 - edgeProbability;
		// enter enge between two nodes with propability of edgeProbability
		for( Node n1 : nodes ) {
			List<Node> nodes2 = new ArrayList<Node>();
			nodes2.addAll( nodes );
			nodes2.remove( n1 );
			for( Node n2 : nodes2 ) {
				if( Math.random() > prob ) {
					n1.linkTo( n2 , Math.random() * 100 );
				}
			}
		}
		return nodes;
	}

	public static void main(String[] args) {
		for( double i = 0; i < 1; i += 0.1) {
			System.out.println( "!!!" +  i );
			test( 100 , i );
		}
	}
	
	public static void test(int size, double edgePropability) {
		List<Node> testSet = random( size, edgePropability );
		Node from = testSet.get( ran.nextInt( size )  );
		Node to = testSet.get( ran.nextInt( size )  );
		
		
		ShortestPathGraph dikstra = new DikstraPathGraph();
		dikstra.addAll( testSet );
		ShortestPathGraph h2 = new AStarPathGraph();
		h2.addAll( testSet );
		ShortestPathGraph h1 = new AStarGraphH1();
		h1.addAll( testSet );
		
		test( dikstra, from, to);
		test( h1, from, to);
		test( h2, from, to);

	}
	
	public static void test(ShortestPathGraph g, Node from, Node to) {
		System.out.println();
		long[] times = new long[10];
		long startTime, endTime;
		double sum = 0;
		
		for (int i = 0; i < times.length; i++) {
			System.out.print('*');
			startTime = System.nanoTime();
			g.distance(from, to);
			endTime = System.nanoTime();
			times[i] = endTime - startTime;
		}
		System.out.println();
		// Lasketaan keskimääräinen aika per testauskerta sum = 0;
		for (int i = 0; i < times.length; i++) {
			sum += times[i];
		}
		sum = sum / times.length;
		System.out.printf("Time " + sum);
	}

}
