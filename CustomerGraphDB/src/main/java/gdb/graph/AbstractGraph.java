package gdb.graph;

import java.util.Properties;

import gdb.GDBException;
import gdb.GraphImpl;

public abstract class AbstractGraph {
	final String delimiter=",";
	public static GraphImpl graph;
	static String configFileLocation;//="D:\\workspace_mapreduce\\CustomerGraphDB\\src\\main\\resource\\conf\\gdb.conf";
	final String configFileName = "conf/gdb.conf";
	static Properties props ;
	ClassLoader classLoader = this.getClass().getClassLoader();
	
	public void init() throws GDBException {
		try {
			graph=GraphImpl.getInstance();
			configFileLocation = classLoader.getResource(configFileName).getPath();
			props = Util.getPropperties(configFileLocation);
			
		}catch(Exception e) {
			throw new GDBException("Exception in initialization ",e);
		}
		
	}
	
	public static String getPropertyValue(String propertyName) {
		return props.getProperty(propertyName)!=null ?props.getProperty(propertyName):"";
	}
	
	public void printGraph() {
		System.out.println("################Graph Start####################");
		graph.printGraph();
		System.out.println("Size of the graph :"+graph.size());
		System.out.println("################Graph End####################");
	}
}
