package gdb.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import gdb.GDBException;
import gdb.GraphImpl;
import gdb.InMemoryGraph;
import gdb.Vertex;
import gdb.entity.AgeGroup;
import gdb.entity.Customer;
import gdb.entity.Region;

/**
 * 
 * @author ayyachamy
 *
 *
 */
public class CustomerGraph extends AbstractGraph implements InMemoryGraph,GraphQuery {

	static Map<Integer,Vertex> ageHash= new HashMap<Integer,Vertex>();
	static Map<Integer,Vertex> regionHash= new HashMap<Integer,Vertex>();
	
	
	static String ageGroupLocation;
	static String regionLocation;
	static String customerLocation;
	
	@Override
	public void execute() throws GDBException{
			init();
			ageGroupLocation = getPropertyValue("age_group");
			regionLocation = getPropertyValue("region");
			customerLocation = getPropertyValue("customer");
			loadGraph();
			printGraph();
	}
	
	public void loadGraph() throws GDBException{
		try {
			loadAgeGroup();
			loadRegion();
			loadCustomer();
			//graphLoadstatus=true;
			//System.out.println("End of Graph Loading . Size of the graph :"+graph.size());
		}catch(Exception e) {
			throw new GDBException("Exception in Loading Graph ",e);
		}
		
		
	}
	
	/*
	 * public void init() throws GDBException { try { graph=GraphImpl.getInstance();
	 * loadProperties();
	 * 
	 * }catch(Exception e) { throw new
	 * GDBException("Exception in initialization ",e); }
	 * 
	 * }
	 */
	
	public void loadAgeGroup() throws GDBException, NumberFormatException, IOException {
		
		BufferedReader br = Util.readFile(ageGroupLocation);
			String line = "";
		    while ((line = br.readLine()) != null) {
		    	if(line.toCharArray()[0]=='#')
		    		continue;
		       String[] data = line.split(delimiter);
		       AgeGroup ageGroup = new AgeGroup(Integer.parseInt(data[0]),data[1],"AgeGroup");
		       ageHash.put(Integer.parseInt(data[0]), ageGroup);
		       graph.addVertex(ageGroup);
		    }
		
		System.out.println("Size of Age Group : "+ageHash.size());
	}
public void loadRegion() throws GDBException, NumberFormatException, IOException {
		
		BufferedReader br = Util.readFile(regionLocation);
			String line = "";
		    while ((line = br.readLine()) != null) {
		    	if(line.toCharArray()[0]=='#')
		    		continue;
		       String[] data = line.split(delimiter);
		       Region region = new Region(Integer.parseInt(data[0]),data[1],"Region");
		       regionHash.put(Integer.parseInt(data[0]), region);
		       graph.addVertex(region);
		    }
		
		System.out.println("Size of Region : "+regionHash.size());
	}
public void loadCustomer() throws GDBException, NumberFormatException, IOException {
	
	BufferedReader br = Util.readFile(customerLocation);
	int i=0;
		String line = "";
	    while ((line = br.readLine()) != null) {
	    	if(line.toCharArray()[0]=='#')
	    		continue;
	       String[] data = line.split(delimiter);
	       Integer ageGroup = Integer.parseInt(data[2]);
	       Integer region = Integer.parseInt(data[3]);
	       // below job should run in a parallel using executor service
	       addCutomer(Integer.parseInt(data[0]),data[1],ageGroup,region);
	       	i++;
	       	//printGraph();
	    }
	
	System.out.println("Size of Customer : "+i);
}

	@Override
	public void addCutomer(int id,String name,int ageGroup,int region) {
		   Vertex customer = new Customer(id,name,"Customer",ageGroup,region);	
		   HashSet<Vertex> edge= new HashSet<Vertex>();
	       
	       Vertex ageVertex = ageHash.get(ageGroup);
	       Vertex regionVertex = regionHash.get(region);
	       
	       if(ageVertex!=null) {
	    	   edge.add(ageVertex);
	    	   graph.addEdge(ageVertex, customer);
	       }
	       if(regionVertex!=null)
	       {
	    	   edge.add(regionVertex);
	    	   graph.addEdge(regionVertex, customer);
	       }
	    	   
	       graph.overrideEdge(customer, edge);
	}

	

	/*
	 * @Override public List<Integer> customerList() { return null; }
	 */

	@Override
	public Set<Integer> customerByRegion(int regionId) {
		Vertex region = new Region(regionId, "", "Region");
		HashSet<Vertex> edge = graph.get(region);
		Set<Integer> customerSet = new HashSet<Integer>();
		edge.forEach(customerId -> customerSet.add(customerId.id));
		return customerSet;
	}

	/*
	 * @Override public List<Integer> customerByAgeGroup(int ageGroupId) { // TODO
	 * Auto-generated method stub return null; }
	 */

	@Override
	public Set<Integer> customerByRegionAgeGroup(int regionId, int ageGroupId) {
		Vertex region = new Region(regionId, "", "Region");
		Vertex ageGroup = new AgeGroup(ageGroupId, "", "AgeGroup");
		HashSet<Vertex> edgeRegion = graph.get(region);
		HashSet<Vertex> edgeAgeGroup = graph.get(ageGroup);
		Set<Integer> customerSet = new HashSet<Integer>();
		if(edgeAgeGroup!=null && edgeRegion!=null) {
			edgeRegion.retainAll(edgeAgeGroup);
			edgeRegion.forEach(customerId -> customerSet.add(customerId.id));
		}
		return customerSet;
	}
	@Override
	public Set<Integer> customerByListOfRegionAgeGroup(Set<Integer> regionIdSet, Set<Integer> ageGroupIdSet) {
		HashSet<Vertex> edgeRegion = new HashSet<Vertex>(); 
		HashSet<Vertex> edgeAgeGroup = new HashSet<Vertex>();
		
		regionIdSet.forEach(regionId -> {
			Vertex region = new Region(regionId, "", "Region");
			edgeRegion.addAll(graph.get(region));
		});
		
		ageGroupIdSet.forEach(ageGroupId -> {
			Vertex ageGroup = new AgeGroup(ageGroupId, "", "AgeGroup");
			edgeAgeGroup.addAll(graph.get(ageGroup));
		});
		
		
		
		Set<Integer> customerSet = new HashSet<Integer>();
		if(edgeAgeGroup!=null && edgeRegion!=null) {
			edgeRegion.retainAll(edgeAgeGroup);
			edgeRegion.forEach(customerId -> customerSet.add(customerId.id));
		}
		return customerSet;
	}
	/*
	 * public void loadProperties() { try { Properties props =
	 * Util.getPropperties(configFileLocation); ageGroupLocation =
	 * props.getProperty("age_group")!=null ?props.getProperty("age_group"):"";
	 * regionLocation = props.getProperty("region")!=null
	 * ?props.getProperty("region"):""; customerLocation =
	 * props.getProperty("customer")!=null ?props.getProperty("customer"):""; }catch
	 * (Exception e) {
	 * System.err.println("Exception While accessing the config file : "
	 * +configFileLocation+e); }
	 * 
	 * } public void printGraph() {
	 * System.out.println("################Graph Start####################");
	 * graph.printGraph();
	 * System.out.println("################Graph End####################"); }
	 */
	
	
}
