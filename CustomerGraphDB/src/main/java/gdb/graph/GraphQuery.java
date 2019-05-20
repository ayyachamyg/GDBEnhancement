package gdb.graph;

import java.util.Set;

public interface GraphQuery {

	/**
	 * 
	 * Added few usecases to get and put a record into inmemory graph
	 * 
	 */
	
	//public List<Integer> customerList();
	public Set<Integer> customerByRegion(int regionId);
	//public List<Integer> customerByAgeGroup(int ageGroupId);
	public Set<Integer> customerByRegionAgeGroup(int regionId,int ageGroupId);
	public Set<Integer> customerByListOfRegionAgeGroup(Set<Integer> regionId, Set<Integer> ageGroupId) ;
	public void addCutomer(int id,String name,int ageGroup,int region) ;
	
	
	
	
}
