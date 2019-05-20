package gdp;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import gdb.GDBException;
import gdb.GraphImpl;
import gdb.graph.CustomerGraph;
import gdb.graph.ProductGraph;
import junit.framework.TestCase;

class MultipleGraph extends TestCase{

	static GraphImpl graph;
	CustomerGraph cg;
	ProductGraph pd;
	Integer actual[] = new Integer[100]; 
	Integer expected[] = new Integer[100];
	public int i=0;
	
	
	@Test
	public void testMultipleGraphImpl() throws GDBException {
		cg = new CustomerGraph();
		cg.execute();
		
		pd = new ProductGraph();
		pd.execute();
		
	}
	
	
}
