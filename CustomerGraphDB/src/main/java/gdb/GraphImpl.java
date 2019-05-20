package gdb;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class GraphImpl  implements Graph
{ 
	private static GraphImpl instance;
	private static ConcurrentHashMap<Vertex,HashSet<Vertex>> adjacencyList;
	
	private GraphImpl() {};
	
	static {
		
	}
	
	public static GraphImpl getInstance(){
		if(instance==null){
			System.out.println("Initializing GraphHandler ###");
			instance = new GraphImpl();
			adjacencyList= new ConcurrentHashMap<Vertex,HashSet<Vertex>>();
		}
		return instance;
	}
	
	@Override
	public void addVertex(Vertex src) {
		if(!isVertexExists(src)) {
			adjacencyList.put(src, new HashSet<Vertex>());
		}
	}
	
	@Override
	public void removeVertex(Vertex src) {
		if(isVertexExists(src)) {
			adjacencyList.remove(src);
		}
	}
	
	@Override
	public boolean isVertexExists(Vertex src) {
		return adjacencyList.containsKey(src);
	}
	
	@Override
	public void addEdge(Vertex src, Vertex tgt) {
		HashSet<Vertex> value ;
		if(isVertexExists(src)) {
			value = adjacencyList.get(src);
			value.add(tgt);
			adjacencyList.replace(src, value);
		}else
		{
			value = new HashSet<Vertex>();
			value.add(tgt);
			adjacencyList.put(src, value);
		}
		
	}
	
	@Override
	public void overrideEdge(Vertex src, HashSet<Vertex> tgt) {
		adjacencyList.put(src, tgt);
	}

	@Override
	public Integer size() {
		return adjacencyList.size();
	}
	
	@Override
	public void removeEdge(Vertex src, Vertex tgt) {
		HashSet<Vertex> value ;
		if(isVertexExists(src)) {
			value = adjacencyList.get(src);
			value.remove(tgt);
			adjacencyList.replace(src, value);
		}
	}

	@Override
	public HashSet<Vertex> get(Vertex v) {
		return adjacencyList.get(v);
		
	} 
	
	@Override
	public void printGraph() {
		adjacencyList.forEach((key, value) -> {
			System.out.print(key.type+":"+key.id+"-->");
			value.forEach(edgeKey -> {
				System.out.print(edgeKey.type+":"+edgeKey.id+",");
			});
			System.out.println();
		});
	}

	@Override
	public void clear() {
		instance =null;
	}
}
