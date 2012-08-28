package io;

import java.io.File;
import java.util.*;

import model.*;

public class DataReader {
	
	/**
	 * Reads the file and generates a graph object from it.
	 * 
	 * @throws Exception if file is invalid
	 * @return generated graph
	 * */
	public static Graph readFile(File f) throws Exception {
		Map<String,Node> nodes = new HashMap<String,Node>();
		
		Scanner reader = new Scanner(f);
		
		while( reader.hasNextLine() ) {
			String line = reader.nextLine();
			String[] split = line.split(":");
			
			// check numer of items
			if( split.length != 3 ) {
				throw new Exception("File format incorrect");
			}
			
			String name = split[0];
			if( ! nodes.keySet().contains(name) ) {
				nodes.put(name, new Node(name) );
			}
			
			Node start = nodes.get( name );
			
			name = split[1];
			if( ! nodes.keySet().contains(name) ) {
				nodes.put(name, new Node(name) );
			}
			
			Node end = nodes.get( name );
			
			double w = Double.parseDouble(split[2]);
			
			if( start.linkTo(end, w) ) {
				throw new Exception("Tie alrady exists");
			}
		}
		
		Graph g = new Graph();
		
		g.addAll( nodes.values() );
		
		return g;
	}

}
