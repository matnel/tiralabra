package test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import io.DataReader;

import model.Graph;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class DataReaderTest {
	
	private static File createFile(String content) {
		File f = null;;
		try {
			f = File.createTempFile("junit", "txt");
			FileWriter fw = new FileWriter(f);
			fw.write(content);
			fw.flush(); fw.close();
		} catch (IOException e) {
			fail("Can not crate temp file for testing");
		}
		return f;
	}
	
	public static String br = System.getProperty("line.separator");

	@Test
	public void testReadEmpty() throws Exception {
		
		Graph g = DataReader.readFile( createFile("") );
		
		double[][] result = new double[0][0];
		assertArrayEquals("Empty file should return correct object", result, g.matrix() );
	}
	
	@Test(expected=Exception.class)
	public void testReadNegativeValue() throws Exception {
		String test = "a:b:-1";
		Graph g = DataReader.readFile( createFile(test) );
	}
	
	
	@Test
	public void testValidFile() throws Exception {
		
		String test = "a:b:10" + br + "a:c:100" + br + "b:a:5";
		
		Graph g = DataReader.readFile( createFile(test) );
		
		double[][] result = { {0,10,100}, {5,0,-1}, {-1,-1,0} };
		assertArrayEquals("Matrix not generated correctly", result, g.matrix() );
		
		assertEquals("Item has incorrect name", "a", g.edges().get(0).name() );
		assertEquals("Item has incorrect name", "b", g.edges().get(1).name() );
		assertEquals("Item has incorrect name", "c", g.edges().get(2).name() );
	}
	
	

}
