package gdp;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import gdb.GDBException;
import gdb.GraphImpl;
import gdb.graph.CustomerGraph;
import junit.framework.TestCase;

class CustomerGraphDBTest extends TestCase{

	static GraphImpl graph;
	CustomerGraph cg;
	Integer actual[] = new Integer[100]; 
	Integer expected[] = new Integer[100];
	public int i=0;
	
	
	@Before
	public void initializeGraph() throws GDBException {
		cg = new CustomerGraph();
		cg.execute();
		
	}
	@Test
	void testCustomerByRegion() throws GDBException {
		initializeGraph();
		Set<Integer> cId = cg.customerByRegion(2);
		expected[0]=2;expected[1]=4;expected[2]=5;
		i=0;
		System.out.print("testcustomerByRegion -> ");
		cId.forEach(id -> {System.out.print(id+","); actual[i]=id;i++;});
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void testCustomerByAgeRegion() throws GDBException {
		initializeGraph();
		Set<Integer> cId = cg.customerByRegionAgeGroup(2, 3);
		expected[0]=2;expected[1]=5;
		i=0;
		System.out.print("testCustomerByAgeRegion -> ");
		cId.forEach(id -> {System.out.print(id+",");actual[i]=id;i++;});
		assertArrayEquals(expected, actual);
		
	}
	
	@Test
	void testCustomerByListOfAgeRegion() throws GDBException {
		initializeGraph();
		Set<Integer> region = new HashSet<Integer>(); region.add(2);region.add(3);
		Set<Integer> ageGroup = new HashSet<Integer>(); ageGroup.add(2);ageGroup.add(3);
		Set<Integer> cId = cg.customerByListOfRegionAgeGroup(region, ageGroup);
		expected[0]=2;expected[1]=3;expected[2]=4;expected[3]=5;
		i=0;
		System.out.print("testCustomerByListOfAgeRegion -> ");
		cId.forEach(id -> {System.out.print(id+",");actual[i]=id;i++;});
		assertArrayEquals(expected, actual);
		
	}
	
	

}
