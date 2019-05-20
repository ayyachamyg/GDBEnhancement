package gdb.graph;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Properties;

import gdb.GDBException;
import gdb.GraphImpl;
import gdb.InMemoryGraph;
import gdb.Vertex;
import gdb.entity.Customer;
import gdb.entity.Product;

public class ProductGraph extends AbstractGraph implements InMemoryGraph{

	
	
	static String productLocation;
	
	@Override
	public void execute() throws GDBException {
		
		init();
		productLocation = getPropertyValue("product");
		loadGraph();
		printGraph();
		
		
	}
	
	private void loadGraph() throws GDBException {
		int i=0;
		try {
			BufferedReader br = Util.readFile(productLocation);
			
			String line = "";
		    while ((line = br.readLine()) != null) {
		    	if(line.toCharArray()[0]=='#')
		    		continue;
		       String[] data = line.split(delimiter);
		       addProduct(Integer.parseInt(data[0]),data[1]);
		       	i++;
		       	//printGraph();
		    }
		} catch (Exception e) {
			throw new GDBException("Exception in loading product ", e); 
		}
		System.out.println("Size of Product Transaction : "+i);

		
	}
	
	public void addProduct(int id,String name) {
		   Vertex product = new Product(id,name,"Product");	
		   
	       graph.addVertex(product);
	}

	

}
